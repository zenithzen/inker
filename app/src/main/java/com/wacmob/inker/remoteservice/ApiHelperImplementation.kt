package com.wacmob.inker.remoteservice

import com.wacmob.inker.models.*
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImplementation @Inject constructor(
    private val apiService: ApiService,
) : ApiHelper {
    override suspend fun getPosts(): Response<List<TestApiResponseModel>> = apiService.getPosts()
    override suspend fun getLogin(loginRequest: LoginRequest): Response<LoginResponse> =
        apiService.getLogin(loginRequest)

    override suspend fun submitOtp(otpSubmitRequest: OtpSubmitRequest): Response<OtpResponse> =
        apiService.submitOtp(otpSubmitRequest)

    override suspend fun getDashBordData(profile_id: String): Response<DashBoardResponse> =
        apiService.getDashBordData(profile_id)

    override suspend fun getLeaderBoardData(profile_id: String): Response<LeaderBoardResponse> =
        apiService.getLeaderBoardData(profile_id)

    override suspend fun getClubList(): Response<ClubListResponse> = apiService.getClubList()
}