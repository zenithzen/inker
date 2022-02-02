package com.wacmob.inker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wacmob.inker.baseresult.BaseResult
import com.wacmob.inker.baseresult.ResultWrapper
import com.wacmob.inker.models.DashBoardResponse
import com.wacmob.inker.repository.AppRepository
import com.wacmob.inker.utils.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(val appRepository: AppRepository) : ViewModel() {

    private var _getDashBordData = SingleLiveData<BaseResult<DashBoardResponse>>()
    val getDashBordData: LiveData<BaseResult<DashBoardResponse>>
        get() = _getDashBordData

    fun getDashBordData(profile_id: String) = viewModelScope.launch {
        println("@SUCEENTE$profile_id")
        _getDashBordData.postValue(BaseResult.loading(null))
        when (val response = appRepository.getDashBordData(profile_id)) {
            is ResultWrapper.Success -> {
                println("@SUCE"+"YES")
                _getDashBordData.postValue(BaseResult.success(response.data))
            }

            is ResultWrapper.Failure -> {
                println("@SUCE"+"FAILED"+response.message)
                _getDashBordData.postValue(BaseResult.error(response.message))
            }

        }
    }


}