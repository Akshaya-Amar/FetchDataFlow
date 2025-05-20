package com.amar.fetchdataflow.data.api

import com.amar.fetchdataflow.data.model.User
import com.amar.fetchdataflow.data.model.UserData
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
     @GET("users")
     suspend fun fetchUsers(): Response<UserData>
}