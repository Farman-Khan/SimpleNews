package com.fkhan.simplenews.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.fkhan.simplenews.databinding.ActivityHomeBinding
import com.fkhan.simplenews.viewmodel.NewsViewModel
import com.fkhan.simplenews.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this,
            NewsViewModelFactory())[NewsViewModel::class.java]
    }
}