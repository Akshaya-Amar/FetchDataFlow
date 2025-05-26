package com.amar.fetchdataflow.data.repository

import com.amar.fetchdataflow.common.Result
import com.amar.fetchdataflow.data.api.ApiService
import com.amar.fetchdataflow.data.model.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retryWhen
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import kotlin.random.Random

class UserRepositoryImpl @Inject constructor(
     private val apiService: ApiService
) : UserRepository {
     override fun getUsers(): Flow<Result<UserData>> = flow {
          while (true) {
               val response = apiService.fetchUsers()
               if (response.isSuccessful) {
                    response.body()?.let { userData ->
                         val modifiedUserData = getModifiedUserData(userData)
                         emit(Result.Success(modifiedUserData))
                    } ?: emit(Result.Failure("Data is empty"))
               } else {
                    emit(Result.Failure(response.message().takeIf { it.isNotBlank() } ?: "Something went wrong"))
               }
               delay(2000)
          }
     }.retryWhen { cause, attempt ->
          if (attempt < 3 && (cause is TimeoutException || cause is UnknownHostException)) {
               delay(2000)
               true
          } else {
               false
          }
     }.catch { exception ->
          emit(Result.Failure(exception.message ?: "Something went wrong"))
     }.flowOn(Dispatchers.IO)
}

private fun getModifiedUserData(userData: UserData): UserData {
     val modifiedUsers = userData.users?.map { user ->
          user.takeIf { user.id != null && user.id % 2 == 0 }?.copy(phone = Random.nextInt(10000000, 999999999).toString()) ?: user
     }
     return UserData(users = modifiedUsers)
}