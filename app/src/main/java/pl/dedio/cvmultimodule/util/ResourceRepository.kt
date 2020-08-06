package pl.dedio.cvmultimodule.util

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourceRepository @Inject constructor(private val context: Context) {

    fun getString(@StringRes stringResId: Int) = context.getString(stringResId)

    @Suppress("SpreadOperator")
    fun getString(@StringRes stringResId: Int, vararg formatArgs: Any): String {
        return context.getString(stringResId, *formatArgs)
    }
}