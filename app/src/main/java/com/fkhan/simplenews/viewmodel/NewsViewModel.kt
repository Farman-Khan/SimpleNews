package com.fkhan.simplenews.viewmodel
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.fkhan.simplenews.api.news.NewsApiImp
import com.fkhan.simplenews.api.retrofit.RetrofitApi
import com.fkhan.simplenews.model.Article
import com.fkhan.simplenews.repository.NewsDataSource

class NewsViewModel @ViewModelInject constructor(private val api: NewsApiImp) : ViewModel() {
    private var _selectedArticle: Article? = null

    private val pager = Pager(PagingConfig(pageSize = 20)) {
        NewsDataSource(api)
    }

    fun setSelectedArticle(article: Article) {
        _selectedArticle = article
    }

    fun getSelectedArticle() = _selectedArticle
    var newsData = pager.flow.cachedIn(viewModelScope)
}
