package com.sample.app.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.app.databinding.ItemSearchResultBinding
import com.sample.app.model.SearchResult

class SearchAdapter : ListAdapter<SearchResult, SearchAdapter.SearchViewHolder>(SearchDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchResultBinding.inflate(inflater, parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        getItem(position).let { articles ->
            with(holder) {
                bind(createOnClickListener(articles.id), articles)
            }
        }
    }

    private fun createOnClickListener(articleId: Int): View.OnClickListener {
        return View.OnClickListener {
            val direction = SearchFragmentDirections.toArticleDetail(articleId)
            it.findNavController().navigate(direction)
        }
    }

    inner class SearchViewHolder(private val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: View.OnClickListener, searchResult: SearchResult) {
            binding.searchResult = searchResult
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
}

object SearchDiff : DiffUtil.ItemCallback<SearchResult>() {
    override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean =
        oldItem == newItem
}