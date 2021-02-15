package br.com.vicentec12.eventtest.data.source.retrofit_api

import br.com.vicentec12.eventtest.data.model.Event
import retrofit2.Response
import retrofit2.http.*

interface EventsService {

    @GET(value = "/events")
    suspend fun listEvents(): Response<List<Event>>

    @GET(value = "/events/{id}")
    suspend fun getEvent(
        @Path("id") mId: Int
    ): Response<Event>

    @FormUrlEncoded
    @POST(value = "/checkin")
    suspend fun checkinEvent(
        @Field("eventId") mEventId: Int,
        @Field("name") mName: String,
        @Field("email") mEmail: String
    ): Response<CheckinResponse>

}