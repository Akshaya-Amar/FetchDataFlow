package com.amar.fetchdataflow.data.repository

import com.amar.fetchdataflow.common.Result
import com.amar.fetchdataflow.data.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {
     fun getUsers(): Flow<Result<UserData>>
}