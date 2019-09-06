package com.sample.app.model

typealias ArticleId = Int

data class Article(
    var id: ArticleId,
    var name: String? = null,
    var description: String? = null,
    var icon: String? = null,
    var galleryUrls: MutableList<String> = mutableListOf(),
    var articleBodyItems: MutableList<ArticleBodyItem> = mutableListOf()
)
