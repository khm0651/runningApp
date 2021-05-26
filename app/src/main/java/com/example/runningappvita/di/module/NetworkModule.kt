package com.example.runningappvita.di.module

import com.example.runningappvita.di.annotation.AuthInterceptorOkHttpClient
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

//    @AuthInterceptorOkHttpClient
//    @Provides
//    @Singleton
//    fun provideApiRetrofit(okHttpClient: AuthInterceptorOkHttpClient,converter: Converter.Factory) : Retrofit =

//    @Provides
//    @Singleton
//    fun provideMeasurersApiService()

//    @Provides
//    fun provideConverterFactory(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)
}