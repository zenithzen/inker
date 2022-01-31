package com.wacmob.inker.remoteservice

import com.wacmob.inker.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): Response<List<TestApiResponseModel>>

    @POST("auth/login")
    suspend fun getLogin(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("auth/login/otp")
    suspend fun submitOtp(@Body otpSubmitRequest: OtpSubmitRequest): Response<OtpResponse>
}