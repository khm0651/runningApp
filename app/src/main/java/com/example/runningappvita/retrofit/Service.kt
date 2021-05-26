package com.example.runningappvita.retrofit

import com.example.runningappvita.BuildConfig
import com.example.runningappvita.retrofit.api.MeasurersAPI
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Service {

    companion object{
        const val BASE_URL = BuildConfig.BASE_VITA_ENDPOINT
        const val BASE_URL_ASSETS = BuildConfig.BASE_VITA_ASSETS

        const val CONNECT_TIMEOUT_SECOND = 20L
        const val WRITE_TIMEOUT_SECOND = 20L
        const val READ_TIMEOUT_SECOND = 20L
        private val convertDateTime = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create()
        private val retrofitnoneInterceptor = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(convertDateTime))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getOkHttpClient(getHttpLogging()).build())
            .build()

        private fun getHttpLogging(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BODY)

        fun getOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder = getOkHttpClient(null,loggingInterceptor)

        fun getOkHttpClient(tokenInterceptor: TokenInterceptor?, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
            val builder = OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_SECOND * 1000, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIMEOUT_SECOND * 1000,  TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIMEOUT_SECOND * 1000,  TimeUnit.MILLISECONDS)

            tokenInterceptor.let {
                builder.addInterceptor(tokenInterceptor)
                if(loggingInterceptor.level != HttpLoggingInterceptor.Level.NONE) builder.addInterceptor(loggingInterceptor)
            }

            return builder
        }

        fun <T> createServiceNoneInterceptor(serviceClass: Class<T>): T = retrofitnoneInterceptor.create(serviceClass)
    }
}