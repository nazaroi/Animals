package com.sample.app.ui.search

import androidx.lifecycle.*
import com.sample.app.model.ArticleId
import com.sample.app.model.SearchResult
import com.sample.app.shared.Event

class SearchViewModel(private val loadSearchResultsUseCase: SearchUseCase) : ViewModel(),
    SearchResultHandler {

    private val _navigateToArticleAction = MutableLiveData<Event<ArticleId>>()
    val navigateToArticleAction: LiveData<Event<ArticleId>>
        get() = _navigateToArticleAction

    private val loadSearchResults = MutableLiveData<Result<List<SearchResult>>>()

    private val _searchResults = MediatorLiveData<List<SearchResult>>()
    val searchResults: LiveData<List<SearchResult>>
        get() = _searchResults

    init {
        _searchResults.addSource(loadSearchResults) {
            _searchResults.value = it.getOrDefault(emptyList())
        }
    }

    override fun open(searchResult: SearchResult) {
        _navigateToArticleAction.value = Event(searchResult.id)
    }

    fun onSearchQueryChanged(newQuery: String) {
        if (newQuery.length < 1) {
            onQueryCleared()
            return
        }
        executeSearch(newQuery)
    }

    private fun executeSearch(query: String) {
        loadSearchResultsUseCase.invoke(query, loadSearchResults)
    }

    private fun onQueryCleared() {
        _searchResults.value = emptyList()
    }

    class SearchViewModelFactory(private val loadSearchResultsUseCase: SearchUseCase) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SearchViewModel(loadSearchResultsUseCase) as T
        }
    }
}