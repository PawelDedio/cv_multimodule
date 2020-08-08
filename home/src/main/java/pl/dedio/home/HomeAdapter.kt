package pl.dedio.home

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import pl.dedio.cvmultimodule.dataBinding.DataBindingAdapter
import pl.dedio.cvmultimodule.dataBinding.DataBindingViewHolder
import pl.dedio.home.model.CvBlockListElement
import javax.inject.Inject
import javax.inject.Provider

class HomeAdapter @Inject constructor() : DataBindingAdapter<CvBlockListElement>(BaseBlockItemCallback) {

    var homeAdapterProvider: Provider<HomeAdapter>? = null

    override fun provideLayoutResForViewType(position: Int) = getItem(position).viewType

    override fun provideDataBindingVariable() = BR.item

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder {
        val holder =  super.onCreateViewHolder(parent, viewType)
        holder.bind(BR.adapter, homeAdapterProvider?.get())
    }
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