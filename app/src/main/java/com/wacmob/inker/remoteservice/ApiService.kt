package com.wacmob.inker.remoteservice

import com.wacmob.inker.models.*
import com.wacmob.inker.utils.AuthorizationInterceptor
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): Response<List<TestApiResponseModel>>

    @POST("auth/login")
    suspend fun getLogin(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("auth/login/otp")
    suspend fun submitOtp(@Body otpSubmitRequest: OtpSubmitRequest): Response<OtpResponse>

   /* @GET("user/profiles/{profile_id}/dashboard")
    suspend fun getDashBordData(
        @Path("profile_id") profile_id: String,
        @Tag authorization: AuthorizationInterceptor.AuthorizationType =
            AuthorizationInterceptor.AuthorizationType.USER_TOKEN,
    ): Response<DashBoardResponse>
*/
    @GET("user/profiles/{profile_id}/dashboard")
    suspend fun getDashBordData(
        @Path("profile_id") profile_id: String,
        @Tag authorization: AuthorizationInterceptor.AuthorizationType =
            AuthorizationInterceptor.AuthorizationType.USER_TOKEN
    ): Response<DashBoardResponse>

   /* @GET("user/profiles/{profile_id}/dashboard")
    suspend fun getDashBordData(
        @Path("profile_id") profile_id: String,
       @Header("Authorization") tocken:String
    ): Response<DashBoardResponse>*/
}