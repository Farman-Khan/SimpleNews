package com.fkhan.simplenews.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.fkhan.simplenews.databinding.FragmentDetailBinding
import com.fkhan.simplenews.utils.MediaHelper
import com.fkhan.simplenews.viewmodel.NewsViewModel

class DetailFragment : Fragment() {
    val args: DetailFragmentArgs by navArgs()
    private lateinit var viewModel: NewsViewModel
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()

        val articleId = "args.article_Id"
        getItemDetails(articleId)
    }

    private fun getItemDetails(articleId: String) {
       val selectedArticle = viewModel.getSelectedArticle()

        //reading seleteced article
        selectedArticle?.let {
            binding.title.text = it.title
            binding.author.text = it.author

            MediaHelper.loadImage(binding.thumbnail.context,
                    it?.urlToImage, binding.thumbnail )
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
    }
}
