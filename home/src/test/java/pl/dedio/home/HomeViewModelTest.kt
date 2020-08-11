package pl.dedio.home

import androidx.lifecycle.Observer
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.dedio.cvmodels.CvUiModel
import pl.dedio.cvmodels.MockedCvModels
import pl.dedio.cvmultimodule.BuildConfig
import pl.dedio.cvmultimodule.MainApplication
import pl.dedio.cvmultimodule.util.ErrorFormatter
import pl.dedio.cvmultimodule.util.ResourceRepository
import pl.dedio.home.extension.CoroutineTestExtension
import pl.dedio.home.extension.InstantTaskExecutorExtension
import pl.dedio.home.extension.ThreeTenTestExtension
import pl.dedio.home.model.CvBlockListElement
import pl.dedio.home.model.CvBlockListElementMapper

@ExperimentalCoroutinesApi
@ExtendWith(
    MockKExtension::class,
    CoroutineTestExtension::class,
    InstantTaskExecutorExtension::class,
    ThreeTenTestExtension::class
)
class HomeViewModelTest {

    @RelaxedMockK
    lateinit var application: MainApplication

    @RelaxedMockK
    lateinit var loadCvDetailsUseCase: LoadCvDetailsUseCase

    @RelaxedMockK
    lateinit var cvBlockListElementMapper: CvBlockListElementMapper

    @RelaxedMockK
    lateinit var errorFormatter: ErrorFormatter

    @RelaxedMockK
    lateinit var resourceRepository: ResourceRepository

    @RelaxedMockK
    lateinit var cvDataObserver: Observer<CvUiModel>

    @RelaxedMockK
    lateinit var cvBlocksObserver: Observer<List<CvBlockListElement>>

    @RelaxedMockK
    lateinit var errorObserver: Observer<HomeViewModel.RequestError>

    @RelaxedMockK
    lateinit var isLoadingObserver: Observer<Boolean>

    private val dispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: HomeViewModel

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(dispatcher)
        dispatcher.pauseDispatcher()
        viewModel = initViewModel()
        dispatcher.resumeDispatcher()
    }

    private fun initViewModel() = HomeViewModel(
        application,
        loadCvDetailsUseCase,
        cvBlockListElementMapper,
        errorFormatter,
        resourceRepository
    ).apply {
        cvData.observeForever(cvDataObserver)
        cvBlocks.observeForever(cvBlocksObserver)
        error.observeForever(errorObserver)
        isLoading.observeForever(isLoadingObserver)
    }

    private inline fun executeOnPausedDispatcher(crossinline block: () -> Unit) {
        Dispatchers.setMain(dispatcher)

        dispatcher.pauseDispatcher()
        block()
        dispatcher.resumeDispatcher()
    }

    @Nested
    @DisplayName("init")
    inner class Init {

        private fun callMethod(): HomeViewModel {

            clearAllMocks(answers = false)
            return initViewModel()
        }

        @Test
        fun `should show loading`() {
            executeOnPausedDispatcher {
                callMethod()
                verify { isLoadingObserver.onChanged(true) }
            }
        }

        @Test
        fun `should call use case with correct params`() {
            val expectedParams = LoadCvDetailsUseCase.Params(
                BuildConfig.GITHUB_USER_NAME,
                BuildConfig.GIST_ID,
                BuildConfig.GIST_NAME
            )

            executeOnPausedDispatcher { callMethod() }

            coVerify { loadCvDetailsUseCase.execute(expectedParams) }
        }

        @Nested
        @DisplayName("Loading success")
        inner class LoadingSuccess {

            private val cvUiModel = MockedCvModels.cvUiModel
            private val cvBlocks =
                listOf(CvBlockListElement.BasicInformation("01.01.2001", "555666777", "Test city"))

            @BeforeEach
            fun setup() {
                coEvery { loadCvDetailsUseCase.execute(any()) } returns cvUiModel
                every { cvBlockListElementMapper.mapBlocks(cvUiModel.blocks) } returns cvBlocks
            }

            @Test
            fun `should map blocks to ui list elements`() {
                executeOnPausedDispatcher { callMethod() }

                verify { cvBlockListElementMapper.mapBlocks(cvUiModel.blocks) }
            }

            @Test
            fun `should update blocks on ui`() {
                executeOnPausedDispatcher { callMethod() }

                verify { cvBlocksObserver.onChanged(cvBlocks) }
            }

            @Test
            fun `should hide loading`() {
                executeOnPausedDispatcher { callMethod() }

                verify { isLoadingObserver.onChanged(false) }
            }
        }

        @Nested
        @DisplayName("Loading failure")
        inner class LoadingFailure {
            private val error = IllegalStateException("Test exception")
            private val errorMessage = "Test exception"
            private val errorButton = "Try again"

            @BeforeEach
            fun setup() {
                coEvery { loadCvDetailsUseCase.execute(any()) } throws error
                every { errorFormatter.getMessage(error) } returns errorMessage
                every { resourceRepository.getString(R.string.cv_request_error_try_again_button) } returns errorButton
            }

            @Test
            fun `should format exception`() {
                executeOnPausedDispatcher { callMethod() }

                verify { errorFormatter.getMessage(error) }
            }

            @Test
            fun `should show error with correct data`() {
                executeOnPausedDispatcher { callMethod() }

                val errorDataSlot = slot<HomeViewModel.RequestError>()

                verify { errorObserver.onChanged(capture(errorDataSlot)) }

                with(errorDataSlot.captured) {
                    assertEquals(errorMessage, message)
                    assertEquals(errorButton, buttonText)
                }
            }

            @Nested
            @DisplayName("Error button click")
            inner class ErrorButtonClick {

                private fun callMethod() {
                    this@Init.callMethod()

                    every { errorObserver.onChanged(any()) } answers {
                        val requestError = firstArg<HomeViewModel.RequestError>()
                        clearAllMocks()
                        requestError.buttonAction.invoke()
                    }
                }

                @Test
                fun `should retry request`() {
                    executeOnPausedDispatcher { callMethod() }

                    coVerify { loadCvDetailsUseCase.execute(any()) }
                }
            }
        }
    }
}