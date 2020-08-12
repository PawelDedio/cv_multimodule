package pl.dedio.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_home.*
import pl.dedio.cvmultimodule.base.BaseFragment
import pl.dedio.cvmultimodule.di.components.ActivityComponent
import pl.dedio.cvmultimodule.extension.getActivityScopedViewModel
import pl.dedio.cvmultimodule.extension.getBinding
import pl.dedio.home.databinding.FragmentHomeBinding
import pl.dedio.home.di.DaggerHomeComponent
import javax.inject.Inject
import javax.inject.Provider
import kotlin.math.abs

class HomeFragment : BaseFragment() {

    lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var adapterProvider: Provider<HomeAdapter>
    lateinit var adapter: HomeAdapter

    override fun makeInject(component: ActivityComponent) {
        DaggerHomeComponent.factory().create(component).inject(this)
    }

    override fun initViewModel() {
        viewModel = getActivityScopedViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = getBinding<FragmentHomeBinding>(
            viewModel = viewModel,
            bindingVariable = BR.viewModel, layoutId = R.layout.fragment_home,
            inflater = inflater, container = container
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeAppBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBar, offset ->
            val alpha = abs(offset / appBar.totalScrollRange.toFloat())
            homeToolbar.alpha = alpha
        })

        viewModel.cvBlocks.observe {
            if (homeRecycler.adapter == null) {
                adapter = adapterProvider.get().apply {
                    homeAdapterProvider = adapterProvider
                }

                homeRecycler.adapter = adapter
            }

            adapter.submitList(it)
        }

        viewModel.error.observe {
            showSnackBar(it.message, it.buttonText, it.buttonAction, homeRootLayout)
        }
    }
}