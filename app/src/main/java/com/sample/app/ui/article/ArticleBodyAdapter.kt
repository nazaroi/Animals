package com.sample.app.ui.article

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.app.model.*
import com.sample.app.R
import com.sample.app.databinding.*

class ArticleBodyAdapter : RecyclerView.Adapter<ArticleBodyAdapter.BaseViewHolder<*>>() {

    private var articleBodyItems: List<ArticleBodyItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            R.layout.item_article_body_heading_1 -> {
                val binding = ItemArticleBodyHeading1Binding.inflate(inflater, parent, false)
                Heading1ViewHolder(binding)
            }

            R.layout.item_article_body_heading_2 -> {
                val binding = ItemArticleBodyHeading2Binding.inflate(inflater, parent, false)
                Heading2ViewHolder(binding)
            }

            R.layout.item_article_body_heading_3 -> {
                val binding = ItemArticleBodyHeading3Binding.inflate(inflater, parent, false)
                Heading3ViewHolder(binding)
            }

            R.layout.item_article_body_heading_4 -> {
                val binding = ItemArticleBodyHeading4Binding.inflate(inflater, parent, false)
                Heading4ViewHolder(binding)
            }

            R.layout.item_article_body_paragraph -> {
                val binding = ItemArticleBodyParagraphBinding.inflate(inflater, parent, false)
                ParagraphViewHolder(binding)
            }

            R.layout.item_article_body_section -> {
                val binding = ItemArticleBodySectionBinding.inflate(inflater, parent, false)
                SectionViewHolder(binding)
            }

            R.layout.item_article_body_image -> {
                val binding = ItemArticleBodyImageBinding.inflate(inflater, parent, false)
                ImageViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is Heading1ViewHolder -> holder.bind(articleBodyItems[position] as Heading1ArticleBodyItem)
            is Heading2ViewHolder -> holder.bind(articleBodyItems[position] as Heading2ArticleBodyItem)
            is Heading3ViewHolder -> holder.bind(articleBodyItems[position] as Heading3ArticleBodyItem)
            is Heading4ViewHolder -> holder.bind(articleBodyItems[position] as Heading4ArticleBodyItem)
            is ParagraphViewHolder -> holder.bind(articleBodyItems[position] as ParagraphArticleBodyItem)
            is SectionViewHolder -> holder.bind(articleBodyItems[position] as SectionArticleBodyItem)
            is ImageViewHolder -> holder.bind(articleBodyItems[position] as ImageArticleBodyItem)
        }
    }

    override fun getItemCount() = articleBodyItems.size

    fun updateData(articleBodyItems: List<ArticleBodyItem>) {
        this.articleBodyItems = articleBodyItems
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (articleBodyItems[position]) {
            is Heading1ArticleBodyItem -> R.layout.item_article_body_heading_1
            is Heading2ArticleBodyItem -> R.layout.item_article_body_heading_2
            is Heading3ArticleBodyItem -> R.layout.item_article_body_heading_3
            is Heading4ArticleBodyItem -> R.layout.item_article_body_heading_4
            is ParagraphArticleBodyItem -> R.layout.item_article_body_paragraph
            is SectionArticleBodyItem -> R.layout.item_article_body_section
            is ImageArticleBodyItem -> R.layout.item_article_body_image
        }
    }

    inner class Heading1ViewHolder(private val binding: ItemArticleBodyHeading1Binding) :
        BaseViewHolder<Heading1ArticleBodyItem>(binding.root) {
        override fun bind(item: Heading1ArticleBodyItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    inner class Heading2ViewHolder(private val binding: ItemArticleBodyHeading2Binding) :
        BaseViewHolder<Heading2ArticleBodyItem>(binding.root) {
        override fun bind(item: Heading2ArticleBodyItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    inner class Heading3ViewHolder(private val binding: ItemArticleBodyHeading3Binding) :
        BaseViewHolder<Heading3ArticleBodyItem>(binding.root) {
        override fun bind(item: Heading3ArticleBodyItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    inner class Heading4ViewHolder(private val binding: ItemArticleBodyHeading4Binding) :
        BaseViewHolder<Heading4ArticleBodyItem>(binding.root) {
        override fun bind(item: Heading4ArticleBodyItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    inner class ParagraphViewHolder(private val binding: ItemArticleBodyParagraphBinding) :
        BaseViewHolder<ParagraphArticleBodyItem>(binding.root) {
        override fun bind(item: ParagraphArticleBodyItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    inner class SectionViewHolder(private val binding: ItemArticleBodySectionBinding) :
        BaseViewHolder<SectionArticleBodyItem>(binding.root) {
        override fun bind(item: SectionArticleBodyItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    inner class ImageViewHolder(private val binding: ItemArticleBodyImageBinding) :
        BaseViewHolder<ImageArticleBodyItem>(binding.root) {
        override fun bind(item: ImageArticleBodyItem) {
            binding.item = item
            binding.executePendingBindings()
        }

    }

    abstract inner class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }
}