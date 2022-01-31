package com.wacmob.inker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wacmob.inker.baseresult.BaseResult
import com.wacmob.inker.baseresult.ResultWrapper
import com.wacmob.inker.models.LoginRequest
import com.wacmob.inker.models.LoginResponse
import com.wacmob.inker.repository.AppRepository
import com.wacmob.inker.utils.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(val appRepository: AppRepository) : ViewModel() {

  /*  private var _getLoginData = MutableLiveData<BaseResult<LoginResponse>>()
     val getLoginData: LiveData<BaseResult<LoginResponse>>
        get() = _getLoginData*/

    private val _getLoginData = SingleLiveData<BaseResult<LoginResponse>>()
    val getLoginData: LiveData<BaseResult<LoginResponse>>
        get() = _getLoginData



    fun getLogin(loginRequest: LoginRequest) = viewModelScope.launch {

        println("@ENTERRERERE"+"YES")

        _getLoginData.postValue(BaseResult.loading(null))

        when (val response = appRepository.getLogin(loginRequest)) {
            is ResultWrapper.Success -> _getLoginData.postValue(
                BaseResult.success(
                    response.data
                )
            )
            is ResultWrapper.Failure ->
                _getLoginData.postValue(
                    BaseResult.error(
                        response.message
                    )
                )
        }
    }

}