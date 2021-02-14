package br.com.vicentec12.eventtest.data.source.retrofit_api

import br.com.vicentec12.eventtest.data.model.Event
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EventsService {

    @GET(value = "/events")
    suspend fun listEvents(): Response<List<Event>>

    @GET(value = "/events/{id}")
    suspend fun getEvent(
        @Path("id") mId: Int
    ): Response<Event>

}