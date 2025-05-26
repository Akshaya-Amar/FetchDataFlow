package com.amar.fetchdataflow.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amar.fetchdataflow.common.Result
import com.amar.fetchdataflow.data.model.UserData
import com.amar.fetchdataflow.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
     private val userRepository: UserRepository
) : ViewModel() {
     private var _users = MutableStateFlow<Result<UserData>>(Result.Loading)
     val users get() = _users.asStateFlow()

     init {
          getUsers()
     }

     private fun getUsers() {
          viewModelScope.launch {
               val result = userRepository.getUsers()
               result.collectLatest {
                    Log.d("wao...", "getUsers: $it")
                    _users.value = it
               }
          }
     }

     fun retry() {
          _users.value = Result.Loading
          getUsers()
     }
}