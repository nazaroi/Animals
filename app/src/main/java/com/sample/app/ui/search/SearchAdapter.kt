package com.sample.app.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.app.databinding.ItemSearchResultBinding
import com.sample.app.model.SearchResult

class SearchAdapter(val searchResultActionHandler: SearchResultActionHandler) :
    ListAdapter<SearchResult, SearchAdapter.SearchViewHolder>(SearchDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchResultBinding.inflate(inflater, parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int): Unit =
        holder.bind(getItem(position))

    inner class SearchViewHolder(private val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(searchResult: SearchResult) {
            binding.searchResult = searchResult
            binding.eventListener = searchResultActionHandler
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