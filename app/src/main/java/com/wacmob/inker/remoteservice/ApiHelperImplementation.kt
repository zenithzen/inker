package com.wacmob.inker.remoteservice

import com.wacmob.inker.models.TestApiResponseModel
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImplementation @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {
    override suspend fun getPosts(): Response<List<TestApiResponseModel>> = apiService.getPosts()
}