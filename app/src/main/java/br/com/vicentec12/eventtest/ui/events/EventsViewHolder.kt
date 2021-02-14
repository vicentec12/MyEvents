package br.com.vicentec12.eventtest.ui.events

import androidx.recyclerview.widget.RecyclerView
import br.com.vicentec12.eventtest.data.model.Event
import br.com.vicentec12.eventtest.databinding.ItemEventBinding
import br.com.vicentec12.eventtest.interfaces.OnItemClickListener

class EventsViewHolder(
    private val mBinding: ItemEventBinding,
    mOnItemClickListener: OnItemClickListener?
) : RecyclerView.ViewHolder(mBinding.root) {

    private lateinit var mEvent: Event

    init {
        mBinding.btnEventDetails.setOnClickListener {
            mOnItemClickListener?.onItemClick(mBinding.ivwEventImage, mEvent, adapterPosition)
        }
    }

    fun bind(mEvent: Event) {
        this.mEvent = mEvent
        mBinding.event = mEvent
    }

}