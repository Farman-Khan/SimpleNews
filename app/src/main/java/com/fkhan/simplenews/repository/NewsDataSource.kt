package com.fkhan.simplenews.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fkhan.simplenews.api.news.NewsApi
import com.fkhan.simplenews.api.retrofit.RetrofitApi
import com.fkhan.simplenews.model.Article
import javax.inject.Inject

class NewsDataSource @Inject constructor(private val api: NewsApi) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        try {
            val nextPage = params.key ?: 0
            val response = api.getTopHeadlines().await()
            Log.d("panda", "load: Response -> $response")

            return LoadResult.Page(
                    data = response.articles,
                    prevKey = if (nextPage > 0) nextPage - 1 else null,
                    nextKey = if (nextPage < response.totalResults) nextPage + 1 else null
            )

        } catch (e: Exception) {
            Log.d("panda", "load: Exception: $e")
            return LoadResult.Error(e)
        }
    }

    @ExperimentalPagingApi
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
