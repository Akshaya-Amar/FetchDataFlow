package com.amar.fetchdataflow.data.repository

import android.util.Log
import com.amar.fetchdataflow.common.Result
import com.amar.fetchdataflow.data.api.ApiService
import com.amar.fetchdataflow.data.model.User
import com.amar.fetchdataflow.data.model.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
     private val apiService: ApiService
) : UserRepository {
     override fun getUsers(): Flow<Result<UserData>> = flow {
          val response = apiService.fetchUsers()
          if (response.isSuccessful) {
               val data = response.body()
               data?.let {
                    emit(Result.Success(it))
               } ?: emit(Result.Failure("Something went wrong"))
          } else {
               emit(Result.Failure(response.message().takeIf { it.isNotBlank() } ?: "Something went wrong"))
          }
     }.catch { exception ->
          emit(Result.Failure(exception.message ?: "Something went wrong"))
     }.flowOn(Dispatchers.IO)
}