package com.wacmob.inker.remoteservice

import com.wacmob.inker.models.TestApiResponseModel
import retrofit2.Response

interface ApiHelper {
    suspend fun getPosts(): Response<List<TestApiResponseModel>>


}