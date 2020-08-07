package pl.dedio.cvmultimodule.dataBinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class DataBindingAdapter<T >(
    itemCallback: DiffUtil.ItemCallback<T>) : ListAdapter<T, DataBindingViewHolder>(itemCallback) {

    var lifecycleOwner: LifecycleOwner? = null

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        holder.bind(provideDataBindingVariable(), getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, viewType, parent, false)
        lifecycleOwner?.let {
            binding.lifecycleOwner = lifecycleOwner
        }

        return DataBindingViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return provideLayoutResForViewType(position)
    }

    abstract fun provideLayoutResForViewType(position: Int): Int

    open fun provideDataBindingVariable() = View.NO_ID
}