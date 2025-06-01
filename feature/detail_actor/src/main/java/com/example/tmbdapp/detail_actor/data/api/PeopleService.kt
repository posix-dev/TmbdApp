package com.example.tmbdapp.detail_actor.data.api

import com.example.tmbdapp.detail_actor.data.DetailPersonResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleService {
    @GET("person/{person_id}")
    fun getPersonDetails(
        @Path("person_id")
        id: Int,
        @Query("language")
        language: String = "ru"
    ): Flow<DetailPersonResponse>
}