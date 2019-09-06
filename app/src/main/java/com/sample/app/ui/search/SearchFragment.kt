package com.sample.app.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.sample.app.R
import com.sample.app.databinding.FragmentSearchBinding
import com.sample.app.shared.result.EventObserver
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private lateinit var viewModel: SearchViewModel
    private lateinit var viewModelFactory: SearchViewModel.SearchViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelFactory = SearchViewModel.SearchViewModelFactory(SearchUseCase(context!!))

        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(SearchViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.toolbar.fragment_search_view.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    dismissKeyboard(this@apply)
                    return true
                }

                override fun onQueryTextChange(query: String): Boolean {
                    viewModel.onSearchQueryChanged(query)
                    return true
                }
            })

            requestFocus()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.navigateToArticleAction.observe(this, EventObserver { speakerId ->
            findNavController().navigate(
                R.id.to_article_detail,
                bundleOf("article_id" to speakerId)
            )
        })
    }

    private fun showKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }

    private fun dismissKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}