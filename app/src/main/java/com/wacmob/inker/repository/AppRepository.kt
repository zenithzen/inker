package com.wacmob.inker.repository

import com.wacmob.inker.baseresult.safeApiCall
import com.wacmob.inker.localdatabaseservice.AppLocalRoomDatabaseDao
import com.wacmob.inker.localdatabaseservice.entities.StudentEntity
import com.wacmob.inker.models.ClubListResponse
import com.wacmob.inker.models.LoginRequest
import com.wacmob.inker.models.OtpSubmitRequest
import com.wacmob.inker.remoteservice.ApiHelper
import retrofit2.Response
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val appLocalRoomDatabaseDao: AppLocalRoomDatabaseDao,
) {

    suspend fun getPosts() = safeApiCall { apiHelper.getPosts() }
    suspend fun getLogin(loginRequest: LoginRequest) =
        safeApiCall { apiHelper.getLogin(loginRequest) }

    suspend fun submitOtp(submitRequest: OtpSubmitRequest) = safeApiCall {
        apiHelper.submitOtp(submitRequest)
    }
    //  suspend fun getNestedPosts()= safeApiCall { apiHelper.getNestedPosts() }

    //for room DataBase
    suspend fun insertStudentData(student: StudentEntity) = appLocalRoomDatabaseDao.insert(student)
    suspend fun fetchStudents() = appLocalRoomDatabaseDao.fetch()
    suspend fun getDashBordData(profile_id: String) =
        safeApiCall { apiHelper.getDashBordData(profile_id) }


    suspend fun getClubList() = safeApiCall { apiHelper.getClubList() }

}