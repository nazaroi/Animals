package com.sample.app.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.sample.app.R
import kotlinx.android.synthetic.main.fragment_gallery_pager.*


class GalleryPagerFragment : Fragment() {

    private lateinit var mUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mUrl = arguments?.getString(ARG_GALLERY_PAGER_URL) ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gallery_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Glide.with(this)
            .load(mUrl)
            .into(fragment_gallery_pager_image)
    }

    companion object {
        const val ARG_GALLERY_PAGER_URL = "gallery_pager_url"

        fun newInstance(url: String): GalleryPagerFragment {
            return GalleryPagerFragment().apply {
                arguments = bundleOf(ARG_GALLERY_PAGER_URL to url)
            }
        }
    }
}
