package com.amar.fetchdataflow.di

import com.amar.fetchdataflow.common.Constants
import com.amar.fetchdataflow.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
     @Provides
     @Singleton
     fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
          level = HttpLoggingInterceptor.Level.BODY
     }

     @Provides
     @Singleton
     fun provideOkHttpClient(
          httpLoggingInterceptor: HttpLoggingInterceptor
     ): OkHttpClient = OkHttpClient.Builder()
          .addInterceptor(httpLoggingInterceptor)
          .build()

     @Provides
     @Singleton
     fun provideRetrofit(
          okHttpClient: OkHttpClient
     ): Retrofit = Retrofit.Builder()
          .baseUrl(Constants.BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .client(okHttpClient)
          .build()

     @Provides
     @Singleton
     fun provideApiService(
          retrofit: Retrofit
     ): ApiService = retrofit.create(ApiService::class.java)
}