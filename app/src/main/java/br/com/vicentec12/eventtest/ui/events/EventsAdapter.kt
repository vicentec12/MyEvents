package br.com.vicentec12.eventtest.ui.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import br.com.vicentec12.eventtest.data.model.Event
import br.com.vicentec12.eventtest.databinding.ItemEventBinding
import br.com.vicentec12.eventtest.interfaces.OnItemClickListener

class EventsAdapter : ListAdapter<Event, EventsViewHolder>(Event.DIFF_UTIL) {

    var mOnItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val mBinding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventsViewHolder(mBinding, mOnItemClickListener)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}