package br.com.vicentec12.eventtest.data.source.event

import br.com.vicentec12.eventtest.R
import br.com.vicentec12.eventtest.data.model.Event
import br.com.vicentec12.eventtest.data.source.Result
import br.com.vicentec12.eventtest.data.source.retrofit_api.EventsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRemoteDataSource @Inject constructor(
    private val mEventsService: EventsService
) : EventDataSource {

    override suspend fun listEvents(): Result<List<Event>> = withContext(Dispatchers.IO) {
        try {
            val mResponse = mEventsService.listEvents()
            if (mResponse.isSuccessful)
                Result.Success(mResponse.body() ?: listOf(), R.string.message_list_events)
            else
                Result.Error(R.string.message_error_list_events)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(R.string.message_error_server)
        }
    }

    override suspend fun getEvent(mId: Int): Result<Event> = withContext(Dispatchers.IO) {
        try {
            val mResponse = mEventsService.getEvent(mId)
            if (mResponse.isSuccessful)
                Result.Success(mResponse.body()!!, R.string.message_get_event_details)
            else
                Result.Error(R.string.message_error_get_event_details)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(R.string.message_error_server)
        }
    }

    override suspend fun checkinEvent(mEventId: Int, mName: String, mEmail: String): Result<Int> =
        withContext(Dispatchers.IO) {
            try {
                val mResponse = mEventsService.checkinEvent(mEventId, mName, mEmail)
                if (mResponse.isSuccessful)
                    Result.Success(mResponse.body()!!.code, R.string.message_checkin_event)
                else
                    Result.Error(R.string.message_error_checkin_event)
            } catch (e: Exception) {
                e.printStackTrace()
                Result.Error(R.string.message_error_server)
            }
        }

}