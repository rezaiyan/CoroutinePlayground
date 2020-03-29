package ir.alirezaiyan.arzte.ui_sdk

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

abstract class DataBoundViewHolder<T : Any, out V : ViewDataBinding>
private constructor(val binding: V) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

    constructor(parent: ViewGroup, factory: (LayoutInflater, ViewGroup, Boolean) -> V) :
            this(factory(LayoutInflater.from(parent.context), parent, false))

    lateinit var item: T

    abstract fun bind(t: T)
}
