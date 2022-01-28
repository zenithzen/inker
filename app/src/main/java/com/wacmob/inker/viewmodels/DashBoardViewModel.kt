package com.wacmob.inker.viewmodels

import androidx.lifecycle.ViewModel
import com.wacmob.inker.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(appRepository: AppRepository) : ViewModel() {
}