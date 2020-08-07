package pl.dedio.cvmultimodule.extension

import pl.dedio.cvmultimodule.exception.ApiException
import retrofit2.Response
import java.io.IOException

fun <T : Any> Response<T>.extractModel(): T = if (isSuccessful) {
    val body = body() ?: throw IOException("Body can't be null")
    body
} else {
    throw ApiException(code())
}