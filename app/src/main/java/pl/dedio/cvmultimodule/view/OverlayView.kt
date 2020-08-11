package pl.dedio.cvmultimodule.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

@SuppressLint("ClickableViewAccessibility")
class OverlayView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        elevation = OVERLAY_ELEVATION
        setOnTouchListener { _, _ -> true }
    }

    companion object {
        const val OVERLAY_ELEVATION = 1000.0F
    }
}