package pl.dedio.cvmultimodule.dataBinding

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class DataBindingViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    open fun bind(dataBindingVariable: Int, item: Any?) {
        binding.setVariable(dataBindingVariable, item)
        binding.executePendingBindings()
    }

    open fun bind(vararg bindingPairs: Pair<Int, Any?>) {
        bindingPairs.forEach {
            binding.setVariable(it.first, it.second)
        }

        binding.executePendingBindings()
    }
}