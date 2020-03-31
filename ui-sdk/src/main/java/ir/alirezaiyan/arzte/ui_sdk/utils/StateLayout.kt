package ir.alirezaiyan.arzte.ui_sdk.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.children
import ir.alirezaiyan.arzte.core.utils.State
import ir.alirezaiyan.arzte.ui_sdk.R
import ir.alirezaiyan.arzte.ui_sdk.ext.visibleOrGone

class StateLayout<T> : FrameLayout {

    private var loading: View
    private var error: View
    private var errorMessage: TextView
    private var retry: View

    var state: State<T>? = null
        set(value) {
            when (value) {
                is State.Loading -> children.forEach { it.visibleOrGone = it == loading }
                is State.Success -> {
                    children.forEach { it.visibleOrGone = it != loading && it != error }
                    updateListener?.invoke(value.data)
                }
                is State.Error -> {
                    children.forEach { it.visibleOrGone = it == error }
                    errorMessage.text = value.message
                }
            }
            field = value
        }

    private var updateListener: ((T) -> Unit)? = null

    fun setRetryAction(retryAction: Runnable?) {
        retry.setOnClickListener { retryAction?.run() }
    }

    fun setUpdateListener(listener: ((T) -> Unit)) {
        updateListener = listener
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, retryAction: () -> Unit) : super(context) {
        retry.setOnClickListener { retryAction() }
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0)

    init {
        val inflater = LayoutInflater.from(context)
        loading = inflater.inflate(R.layout.loading, this, false).also { addView(it) }
        error = inflater.inflate(R.layout.error, this, false).also { addView(it) }
        errorMessage = error.findViewById(R.id.error_message)
        retry = error.findViewById(R.id.retry)
    }
}