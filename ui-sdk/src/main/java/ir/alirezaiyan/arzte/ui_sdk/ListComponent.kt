package ir.alirezaiyan.arzte.ui_sdk

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.component.view.*

/**
 * @author Ali (alirezaiyann@gmail.com)
 * @since 3/6/2020 12:44 PM.
 */
class ListComponent<T : Any, V : ViewDataBinding>(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : LinearLayout(context) {

    constructor(context: Context) : this(context, null, 0, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(
        context: Context,
        title: String?,
        layoutManager: RecyclerView.LayoutManager,
        scrollListener: EndlessOnScrollListener,
        adapter: DataBoundListAdapter<T,V>
    ) : this(context){
        LayoutInflater.from(context)
            .inflate(R.layout.component, this, true)
        orientation = VERTICAL
        prepare(title, layoutManager, scrollListener, adapter)
    }

    private fun prepare(
        title: String?,
        layoutManager: RecyclerView.LayoutManager,
        scrollListener: EndlessOnScrollListener,
        adapter: DataBoundListAdapter<T,V>
    ) {
        componentTitle.text = title
        componentList.apply {
            this.layoutManager = layoutManager
            this.adapter = adapter
            this.addOnScrollListener(scrollListener)
            this.setHasFixedSize(true)
            this.setItemViewCacheSize(20)
        }

    }

    data class Builder<T : Any, V : ViewDataBinding>(var context: Context) {

        var title: String? = ""
        lateinit var scrollListener: EndlessOnScrollListener
        lateinit var layoutManager: RecyclerView.LayoutManager
        lateinit var adapter: DataBoundListAdapter<T,V>

        fun build() = ListComponent(context, title, layoutManager, scrollListener, adapter)
    }

}