package com.amar.fetchdataflow.util

import com.amar.fetchdataflow.common.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retryWhen
import retrofit2.Response
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

fun <T> safeApiCall(
     hitApi: suspend () -> Response<T>
): Flow<Result<T>> = flow {
     val response = hitApi()
     if (response.isSuccessful) {
          response.body()?.let {
               emit(Result.Success(it))
          } ?: emit(Result.Failure("Something went wrong"))
     } else {
          emit(Result.Failure(response.message().takeIf { it.isNotBlank() } ?: "Something went wrong"))
     }
}.retryWhen { cause: Throwable, attempt: Long ->
     if (cause is UnknownHostException || cause is TimeoutException) {
          if (attempt < 3) {
               delay(2000)
               true
          } else false
     } else false
}.catch { exception ->
     emit(Result.Failure(exception.message ?: "Something went wrong"))
}.flowOn(Dispatchers.IO)

/*
Flow Emission:
You initiate the flow by calling apiService.fetchUsers().
If the network request fails (e.g., due to no internet connection), it throws an exception (e.g., UnknownHostException).

Retry Block:
The exception is caught by retry().
The retry() operator checks if the exception is of a type you want to retry (e.g., UnknownHostException or TimeoutException).
If the exception matches the retry condition, it retries the flow up to 3 times (or the number you set).

Catch Block:
If all retry attempts fail, the exception is then caught by the catch() block.
In catch(), you handle the final failure by emitting a Result.Failure and logging the error.
*/