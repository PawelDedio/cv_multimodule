package pl.dedio.cvmultimodule.util

import pl.dedio.cvmultimodule.R
import pl.dedio.cvmultimodule.exception.ApiException
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorFormatter @Inject constructor(private val resourceRepository: ResourceRepository) {

    fun getMessage(throwable: Throwable): String {
        return when (throwable) {
            is ApiException -> resourceRepository.getString(
                R.string.error_message_api_exception,
                throwable.code
            )
            is UnknownHostException -> resourceRepository.getString(R.string.error_message_no_internet)
            else -> resourceRepository.getString(
                R.string.error_message_unknown,
                throwable.toString()
            )
        }
    }
}