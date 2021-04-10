package com.fkhan.simplenews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fkhan.simplenews.api.news.NewsApi
import com.fkhan.simplenews.api.news.NewsApiImp
import com.fkhan.simplenews.api.retrofit.RetrofitApi
import com.fkhan.simplenews.api.retrofit.RetrofitApiFactory.retrofitApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //testinf news api response here
        GlobalScope.launch {
            NewsApiImp(retrofitApi).getTopHeadlines("us")
        }

    }
}