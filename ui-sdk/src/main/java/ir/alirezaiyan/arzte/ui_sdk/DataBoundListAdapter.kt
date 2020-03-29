package ir.alirezaiyan.arzte.ui_sdk

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView.Adapter

class DataBoundListAdapter<T : Any, V : ViewDataBinding>(
        private var factory: (ViewGroup) -> DataBoundViewHolder<T, V>
) : Adapter<DataBoundViewHolder<T, V>>() {

    private var items: MutableList<T> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder<T, V> = factory(parent)

    override fun onBindViewHolder(holder: DataBoundViewHolder<T, V>, position: Int) {
        val item = items[position]
        holder.item = item
        holder.bind(item)
        holder.binding.executePendingBindings()
    }

    fun update(update: List<T>) {
        this.items.addAll(update)
        notifyItemInserted(0)
    }

    override fun getItemCount(): Int = items.size
}
