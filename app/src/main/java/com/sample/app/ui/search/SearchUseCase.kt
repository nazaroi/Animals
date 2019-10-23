package com.sample.app.ui.search

import android.content.Context
import com.sample.app.data.SearchRepository
import com.sample.app.model.SearchResult
import com.sample.app.shared.UseCase

class SearchUseCase(val context: Context) : UseCase<String, List<SearchResult>>() {

    override fun execute(parameters: String): List<SearchResult> {
        val query = parameters.toLowerCase()

        return SearchRepository.getSearchResults(context)
            .filter { it.name.toLowerCase().contains(query) }
    }
}