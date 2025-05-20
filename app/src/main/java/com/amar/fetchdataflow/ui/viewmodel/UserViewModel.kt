package com.amar.fetchdataflow.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amar.fetchdataflow.common.Result
import com.amar.fetchdataflow.data.model.UserData
import com.amar.fetchdataflow.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
     private val userRepository: UserRepository
) : ViewModel() {
     private var _users = MutableStateFlow<Result<UserData>>(Result.Loading)
     val users: StateFlow<Result<UserData>> get() = _users

     fun getUsers() {
          viewModelScope.launch {
               val result = userRepository.getUsers()
               result.collect {
                    _users.value = it
               }
          }
     }
}