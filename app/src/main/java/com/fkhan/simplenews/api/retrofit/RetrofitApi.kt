package com.fkhan.simplenews.api.retrofit

import com.fkhan.simplenews.model.NewsResponse
import com.fkhan.simplenews.utils.Constant.Companion.API_KEY
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("v2/top-headlines")
    fun getTopHeadlines(
        @Query("country") countryCode: String = "us",
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY
    ): Deferred<NewsResponse>
}