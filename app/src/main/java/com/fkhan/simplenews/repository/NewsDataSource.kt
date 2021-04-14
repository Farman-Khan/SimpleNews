package com.fkhan.simplenews.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fkhan.simplenews.api.news.NewsApi
import com.fkhan.simplenews.api.retrofit.RetrofitApi
import com.fkhan.simplenews.model.Article
import javax.inject.Inject

class NewsDataSource @Inject constructor(private val api: NewsApi) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        try {
            val nextPage = params.key ?: 1
            val response = api.getTopHeadlines("us").await()
            Log.d("panda", "load: Response -> $response")
            val data = response.articles

            //to test
            data.let { list ->
                list.forEach {
                    print("panda,   Title:  " + it.title)
                }
            }
            return LoadResult.Page(
                    data = data,
                    prevKey = null,
                    nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
