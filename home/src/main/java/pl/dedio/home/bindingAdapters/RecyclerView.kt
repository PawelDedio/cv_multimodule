package pl.dedio.home.bindingAdapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.dedio.cvmultimodule.dataBinding.DataBindingAdapter

@BindingAdapter("items")
fun <T> RecyclerView.submitItems(items: List<T>?) {
    val bindingAdapter = adapter as? DataBindingAdapter<T>

    bindingAdapter?.submitList(items)
}

@BindingAdapter("adapter")
fun <VH : RecyclerView.ViewHolder> RecyclerView.submitAdapter(recyclerAdapter: RecyclerView.Adapter<VH>?) {
    adapter = recyclerAdapter
}