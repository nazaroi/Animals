package com.sample.app.ui.search

import com.sample.app.model.SearchResult

/**
 * Actions that can be performed on a [SearchResult]
 */
interface SearchResultActionHandler {
    fun openSearchResult(searchResult: SearchResult)
}