package com.fkhan.simplenews.api.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitApiFactory {
    private const val API_KEY = "ee8cb583c73e439faade9115309bf5c2"
    private const val BASE_URL = "https://newsapi.org/"

    //creating Auth interceptor common to all api requests
    private val authInterceptor = Interceptor {chain ->
        val requestUrl = chain.request()
            .url
            .newBuilder().addQueryParameter("api_key", API_KEY)
            .build()

        val request = chain.request()
            .newBuilder()
            //.url(requestUrl)
            .build()
        chain.proceed(request)
    }

    private var loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.HEADERS
        level = HttpLoggingInterceptor.Level.BODY
    }

    //Okhttp client for http request url
    private val requestClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    //creating retrofit provider
    fun retrofit(): Retrofit {

        return Retrofit.Builder()
            .client(requestClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val retrofitApi: RetrofitApi = retrofit().create(RetrofitApi::class.java)
}
