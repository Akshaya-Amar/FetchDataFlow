package com.amar.fetchdataflow.di

import com.amar.fetchdataflow.data.repository.UserRepository
import com.amar.fetchdataflow.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
     @Binds
     @Singleton
     abstract fun bindUserRepository(
          userRepositoryImpl: UserRepositoryImpl
     ) :UserRepository
}