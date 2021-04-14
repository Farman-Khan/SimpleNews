package com.fkhan.simplenews.api.news

import com.fkhan.simplenews.api.retrofit.RetrofitApiFactory.retrofitApi
import com.fkhan.simplenews.model.NewsResponse
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class NewsApiImp @Inject constructor(): NewsApi {

    override suspend fun getTopHeadlines(country: String): Deferred<NewsResponse> {
        return retrofitApi.getTopHeadlines(country)
    }
    //more methods for news api
}
