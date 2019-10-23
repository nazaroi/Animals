package com.sample.app.ui.article

import android.content.Context
import com.sample.app.data.ArticleRepository
import com.sample.app.model.Article
import com.sample.app.shared.UseCase

class ArticleUseCase(val context: Context) : UseCase<Int, Article>() {
    override fun execute(parameters: Int): Article {
        return ArticleRepository.getArticle(context, parameters)
    }
}