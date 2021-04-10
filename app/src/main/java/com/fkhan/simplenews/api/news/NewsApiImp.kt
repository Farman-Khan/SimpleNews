package com.fkhan.simplenews.api.news

import com.fkhan.simplenews.api.retrofit.RetrofitApi
import com.fkhan.simplenews.model.NewsResponse
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class NewsApiImp @Inject constructor(private val api: RetrofitApi): NewsApi {

    override suspend fun getTopHeadlines(country: String): Deferred<NewsResponse> {
        return api.getTopHeadlines(country)
    }

    //more methods for news api
}
