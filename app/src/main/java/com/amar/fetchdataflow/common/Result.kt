package com.amar.fetchdataflow.common

sealed class Result<out T> {
     data class Success<T>(val data: T) : Result<T>()
     data class Failure(val message: String) : Result<Nothing>()
     data object Loading : Result<Nothing>()
}