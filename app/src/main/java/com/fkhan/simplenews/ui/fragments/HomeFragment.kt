package com.fkhan.simplenews.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fkhan.simplenews.databinding.FragmentHomeBinding
import com.fkhan.simplenews.model.Article
import com.fkhan.simplenews.ui.adapter.NewsArticleAdapter
import com.fkhan.simplenews.utils.Constant
import com.fkhan.simplenews.utils.setFont
import com.fkhan.simplenews.viewmodel.NewsViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    private lateinit var viewModel: NewsViewModel
    private lateinit var articleAdapter: NewsArticleAdapter

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupViewModel()
        setupViewWithPagingData()
        binding.headerTitle.setFont(Constant.ROBOTO_SLAB_BOLD)
    }

    private fun setupViewWithPagingData() {
        lifecycleScope.launch {
            viewModel.newsData.collect { article ->
                articleAdapter.submitData(article)
            }
        }
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        articleAdapter = NewsArticleAdapter(ItemClickListener())
        binding.recyclerView.adapter = articleAdapter
    }

    inner class ItemClickListener {
        fun onItemClick(view: View, position: Int, item: Article) {

            //update selected item in view model
            viewModel.setSelectedArticle(item)

            Log.d("panda", "onItemClick:-------- ${item.title}")
            //passing title as id as of now
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(item.title)
            navController.navigate(action)
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
