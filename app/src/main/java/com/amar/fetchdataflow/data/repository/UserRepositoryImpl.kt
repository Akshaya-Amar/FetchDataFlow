package com.amar.fetchdataflow.data.repository

import com.amar.fetchdataflow.common.Result
import com.amar.fetchdataflow.data.api.ApiService
import com.amar.fetchdataflow.data.model.UserData
import com.amar.fetchdataflow.util.safeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
     private val apiService: ApiService
) : UserRepository {
     override fun getUsers(): Flow<Result<UserData>> {
          return safeApiCall { apiService.fetchUsers() }
     }
}