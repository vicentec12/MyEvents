package br.com.vicentec12.eventtest.adapters

import android.widget.ViewFlipper
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.vicentec12.eventtest.data.model.Event
import br.com.vicentec12.eventtest.extensions.Extensions.loadUrl
import br.com.vicentec12.eventtest.ui.events.EventsAdapter


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("items")
    fun setItemsEvents(mView: RecyclerView, mList: List<Event>?) {
        mView.adapter?.let {
            (it as EventsAdapter).submitList(mList ?: listOf())
        }
    }

    @JvmStatic
    @BindingAdapter("displayedChild")
    fun setDisplayedChild(mView: ViewFlipper, mChild: Int) {
        if (mView.displayedChild != mChild)
            mView.displayedChild = mChild
    }

    @JvmStatic
    @BindingAdapter("url")
    fun getImageUrl(mView: AppCompatImageView, mUrl: String?) {
        mView.loadUrl(mUrl)
    }

}