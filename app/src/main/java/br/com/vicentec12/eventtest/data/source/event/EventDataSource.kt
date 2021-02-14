package br.com.vicentec12.eventtest.data.source.event

import br.com.vicentec12.eventtest.data.model.Event
import br.com.vicentec12.eventtest.data.source.Result

interface EventDataSource {

    suspend fun listEvents(): Result<List<Event>>

    suspend fun getEvent(mId: Int): Result<Event>

}