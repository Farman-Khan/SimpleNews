package com.fkhan.simplenews.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fkhan.simplenews.api.news.NewsApiImp

class NewsViewModelFactory() :
     ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(NewsApiImp()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
