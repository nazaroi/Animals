package com.sample.app.ui.article

import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.app.model.ArticleBodyItem

@BindingAdapter("articleBodyItems")
fun articleBodyItems(recyclerView: RecyclerView, list: List<ArticleBodyItem>?) {
    if (recyclerView.adapter == null) {
        recyclerView.adapter = ArticleBodyAdapter()
    }

    if (list.isNullOrEmpty()) {
        recyclerView.isVisible = false
    } else {
        recyclerView.isVisible = true
        (recyclerView.adapter as ArticleBodyAdapter).updateData(list)
    }
}