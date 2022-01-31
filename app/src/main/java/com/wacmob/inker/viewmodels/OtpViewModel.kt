package com.wacmob.inker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wacmob.inker.baseresult.BaseResult
import com.wacmob.inker.baseresult.ResultWrapper
import com.wacmob.inker.models.OtpResponse
import com.wacmob.inker.models.OtpSubmitRequest
import com.wacmob.inker.repository.AppRepository
import com.wacmob.inker.utils.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(val appRepository: AppRepository) : ViewModel() {

    private var _getOtpResponse = SingleLiveData<BaseResult<OtpResponse>>()
    val getOtpResponse: LiveData<BaseResult<OtpResponse>>
        get() = _getOtpResponse


    fun submitOtp(submitRequest: OtpSubmitRequest) = viewModelScope.launch {
        _getOtpResponse.postValue(BaseResult.loading(null))
        when (val response = appRepository.submitOtp(submitRequest)) {
            is ResultWrapper.Success -> {

                println("@SUX"+"YES")
                _getOtpResponse.postValue(BaseResult.success(response.data))

            }

            is ResultWrapper.Failure -> {
                println("@SUX"+"NO"+response.message)
                _getOtpResponse.postValue(BaseResult.error(response.message))

            }
        }

    }


}