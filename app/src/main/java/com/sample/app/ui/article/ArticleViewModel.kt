package com.sample.app.ui.article

import androidx.lifecycle.*
import com.sample.app.model.Article
import com.sample.app.shared.result.Event

class ArticleViewModel(private val articleUseCase: ArticleUseCase) : ViewModel(),
    OpenGalleryActionHandler {

    private val loadArticle = MutableLiveData<Result<Article>>()

    private val _navigateToGallery = MutableLiveData<Event<List<String>>>()
    val navigateToGallery: LiveData<Event<List<String>>>
        get() = _navigateToGallery

    private val _article = MediatorLiveData<Article>()
    val article: LiveData<Article> = _article

    init {
        _article.addSource(loadArticle) {
            _article.value = it.getOrNull()
        }
    }

    fun loadArticle(id: Int) {
        articleUseCase.invoke(id, loadArticle)
    }

    override fun openGallery(galleryUrls: List<String>) {
        _navigateToGallery.value = Event(galleryUrls)
    }

    class ArticleViewModelFactory(private val articleUseCase: ArticleUseCase) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ArticleViewModel(articleUseCase) as T
        }
    }
}