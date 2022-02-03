package com.wacmob.inker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wacmob.inker.baseresult.BaseResult
import com.wacmob.inker.baseresult.ResultWrapper
import com.wacmob.inker.models.ClubListResponse
import com.wacmob.inker.repository.AppRepository
import com.wacmob.inker.utils.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderBordViewModel @Inject constructor(val appRepository: AppRepository) : ViewModel() {

    private var _getClubListLiveData = SingleLiveData<BaseResult<ClubListResponse>>()
    val getClubListLiveData: LiveData<BaseResult<ClubListResponse>>
        get() = _getClubListLiveData


    fun getClubList() = viewModelScope.launch {
        _getClubListLiveData.postValue(BaseResult.loading(null))
        when (val response = appRepository.getClubList()) {
            is ResultWrapper.Success -> {
                _getClubListLiveData.postValue(BaseResult.success(response.data))

            }

            is ResultWrapper.Failure -> {
                _getClubListLiveData.postValue(BaseResult.error(response.message))
            }
        }

    }

}