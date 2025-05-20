package com.amar.fetchdataflow.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amar.fetchdataflow.common.Result
import com.amar.fetchdataflow.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
     userRepository: UserRepository
) : ViewModel() {
     val users = userRepository.getUsers().stateIn(
          scope = viewModelScope,
          initialValue = Result.Loading,
          started = SharingStarted.WhileSubscribed(5000)
     )
}