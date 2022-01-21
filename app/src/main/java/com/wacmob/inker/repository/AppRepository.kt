package com.wacmob.inker.repository

import com.wacmob.inker.baseresult.safeApiCall
import com.wacmob.inker.localdatabaseservice.AppLocalRoomDatabaseDao
import com.wacmob.inker.localdatabaseservice.entities.StudentEntity
import com.wacmob.inker.remoteservice.ApiHelper
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val appLocalRoomDatabaseDao: AppLocalRoomDatabaseDao
) {

    suspend fun getPosts() = safeApiCall { apiHelper.getPosts() }
  //  suspend fun getNestedPosts()= safeApiCall { apiHelper.getNestedPosts() }

    //for room DataBase
    suspend fun insertStudentData(student: StudentEntity) = appLocalRoomDatabaseDao.insert(student)
    suspend fun fetchStudents() = appLocalRoomDatabaseDao.fetch()

}