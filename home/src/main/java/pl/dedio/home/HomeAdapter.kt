package pl.dedio.home

import androidx.recyclerview.widget.DiffUtil
import pl.dedio.cvmultimodule.dataBinding.DataBindingAdapter
import pl.dedio.home.model.CvBlockListElement
import javax.inject.Inject

class HomeAdapter @Inject constructor() : DataBindingAdapter<CvBlockListElement>(BaseBlockItemCallback) {

    override fun provideLayoutResForViewType(position: Int) = getItem(position).viewType

    override fun provideDataBindingVariable() = BR.item
}

private object BaseBlockItemCallback : DiffUtil.ItemCallback<CvBlockListElement>() {
    override fun areItemsTheSame(
        oldItem: CvBlockListElement,
        newItem: CvBlockListElement
    ) = oldItem.viewType == newItem.viewType

    override fun areContentsTheSame(
        oldItem: CvBlockListElement,
        newItem: CvBlockListElement
    ) = oldItem == newItem
}