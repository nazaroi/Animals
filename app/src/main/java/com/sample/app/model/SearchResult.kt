package com.sample.app.model

data class SearchResult(
    var id: Int,
    var name: String = "",
    var description: String = "",
    var icon: String = ""
)