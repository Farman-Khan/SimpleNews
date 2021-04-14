package com.fkhan.simplenews.api.news

import com.fkhan.simplenews.model.NewsResponse
import kotlinx.coroutines.Deferred

interface NewsApi {
    suspend fun getTopHeadlines(country: String): Deferred<NewsResponse>
    //more methods for news api
}
