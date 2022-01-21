package com.wacmob.inker.remoteservice

import com.wacmob.inker.models.TestApiResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): Response<List<TestApiResponseModel>>
}