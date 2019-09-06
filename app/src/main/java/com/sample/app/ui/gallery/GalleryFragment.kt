package com.sample.app.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sample.app.R
import com.sample.app.databinding.ItemTabGalleryBinding
import kotlinx.android.synthetic.main.fragment_gallery.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class GalleryFragment : Fragment() {

    private val args: GalleryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragment_gallery_back.onClick {
            findNavController().navigateUp()
        }

        fragment_gallery_vp.apply {
            adapter = fragmentManager?.let {
                GalleryAdapter(it, args.galleryUrls)
            }
        }

        val galleryTabs = fragment_gallery_tabs.apply {
            setupWithViewPager(fragment_gallery_vp)
        }

        for (i in 0..galleryTabs.tabCount) {

            galleryTabs.getTabAt(i)?.customView =
                getGalleryCustomView(galleryTabs, args.galleryUrls[i])
        }
    }

    private fun getGalleryCustomView(tabLayout: ViewGroup, url: String): View {
        val inflater = LayoutInflater.from(context)
        val binding = ItemTabGalleryBinding.inflate(inflater, tabLayout, false);
        binding.tabUrl = url
        return binding.root
    }
}