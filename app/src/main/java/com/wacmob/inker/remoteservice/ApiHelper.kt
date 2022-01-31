package com.wacmob.inker.remoteservice

import com.wacmob.inker.models.*
import retrofit2.Response
import retrofit2.http.Body

interface ApiHelper {
    suspend fun getPosts(): Response<List<TestApiResponseModel>>

    suspend fun getLogin(loginRequest: LoginRequest): Response<LoginResponse>

    suspend fun submitOtp( otpSubmitRequest: OtpSubmitRequest): Response<OtpResponse>

}