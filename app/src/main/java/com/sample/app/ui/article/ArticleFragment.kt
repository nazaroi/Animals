package com.sample.app.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sample.app.R
import com.sample.app.databinding.FragmentArticleBinding
import com.sample.app.shared.EventObserver


class ArticleFragment : Fragment() {

    private lateinit var binding: FragmentArticleBinding

    private lateinit var viewModel: ArticleViewModel
    private lateinit var viewModelFactory: ArticleViewModel.ArticleViewModelFactory

    private val args: ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModelFactory = ArticleViewModel.ArticleViewModelFactory(ArticleUseCase(context!!))
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(ArticleViewModel::class.java)

        binding.let {
            it.viewModel = viewModel
            it.lifecycleOwner = this
            it.toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }

        viewModel.loadArticle(args.articleId)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.navigateToGallery.observe(this,
            EventObserver { galleryUrls ->
                findNavController().navigate(
                    R.id.to_gallery,
                    bundleOf("gallery_urls" to galleryUrls.toTypedArray())
                )
            })
    }
}

