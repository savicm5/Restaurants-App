package com.example.restaurants.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

object ApiUtiities {
    val BASE_URL = "https://www.triposo.com/"
//
    fun getInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}