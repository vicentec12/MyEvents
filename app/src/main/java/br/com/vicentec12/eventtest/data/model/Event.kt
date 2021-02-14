package br.com.vicentec12.eventtest.data.model

import androidx.recyclerview.widget.DiffUtil
import br.com.vicentec12.eventtest.extensions.Extensions.toCurrency
import br.com.vicentec12.eventtest.extensions.Extensions.toDate

data class Event(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val latitude: Double,
    val longitude: Double,
    val price: Double,
    val date: Long
) {

    val priceCurrency: String
        get() = price.toCurrency()

    val dateString: String
        get() = date.toDate()

    companion object {

        val DIFF_UTIL = object : DiffUtil.ItemCallback<Event>() {
            override fun areItemsTheSame(oldItem: Event, newItem: Event) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Event, newItem: Event) =
                oldItem == newItem

        }

    }

}