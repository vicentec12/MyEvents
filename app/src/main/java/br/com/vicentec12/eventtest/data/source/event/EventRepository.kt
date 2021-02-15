package br.com.vicentec12.eventtest.data.source.event

import br.com.vicentec12.eventtest.data.model.Event
import br.com.vicentec12.eventtest.data.source.Remote
import br.com.vicentec12.eventtest.data.source.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepository @Inject constructor(
    @Remote val mEventeRemoteDataSource: EventDataSource
) : EventDataSource {

    override suspend fun listEvents(): Result<List<Event>> {
        return mEventeRemoteDataSource.listEvents()
    }

    override suspend fun getEvent(mId: Int): Result<Event> {
        return mEventeRemoteDataSource.getEvent(mId)
    }

    override suspend fun checkinEvent(mEventId: Int, mName: String, mEmail: String): Result<Int> {
        return mEventeRemoteDataSource.checkinEvent(mEventId, mName, mEmail)
    }

}