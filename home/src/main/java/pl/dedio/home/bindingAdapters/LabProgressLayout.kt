package pl.dedio.home.bindingAdapters

import androidx.databinding.BindingAdapter
import com.durranilab.labprogresslayout.LabProgressLayout

@BindingAdapter("progress")
fun LabProgressLayout.setProgress(progress: Int) {
    setCurrentProgress(progress)
}