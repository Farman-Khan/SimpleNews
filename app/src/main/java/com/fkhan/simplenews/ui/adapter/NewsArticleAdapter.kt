package com.fkhan.simplenews.ui.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.TextureView
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fkhan.simplenews.databinding.ItemLayoutBinding
import com.fkhan.simplenews.model.Article
import com.fkhan.simplenews.ui.fragments.HomeFragment
import com.fkhan.simplenews.utils.Constant
import com.fkhan.simplenews.utils.MediaHelper


class NewsArticleAdapter(var itemClickListener: HomeFragment.ItemClickListener) :
    PagingDataAdapter<Article, NewsArticleAdapter.DataViewHolder>(DataDifferntiator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        getItem(position)?.let { article ->
            holder.bind(article)

            //to test item click
            holder.itemView.setOnClickListener {
                itemClickListener.onItemClick(it, position, article)
            }
        }
    }

    object DataDifferntiator : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    class DataViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.title.text = article.title
            binding.source.text = article.source?.name
            binding.date.text = article.publishedAt?.let {
                it.subSequence(0, it.indexOf("T") )
            }

            //can be written ad extension function
            applyFont(binding.title, Constant.ROBOTO_SLAB_REGULAR)
            applyFont(binding.source, Constant.ROBOTO_SLAB_BOLD)

            MediaHelper.loadImage(binding.thumbnail.context,
                article.urlToImage, binding.thumbnail)
        }

        private fun applyFont(text: TextView?, fontFamily: String) {
            text?.apply {
                typeface = Typeface.createFromAsset(this.context.assets, fontFamily)
            }
        }
    }
}
