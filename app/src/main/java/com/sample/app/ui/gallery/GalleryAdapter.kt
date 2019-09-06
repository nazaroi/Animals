package com.sample.app.ui.gallery

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class GalleryAdapter(fm: FragmentManager, private val galleryUrls: Array<String>) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(i: Int): Fragment {
        return GalleryPagerFragment.newInstance(galleryUrls[i])
    }

    override fun getCount(): Int {
        return galleryUrls.size
    }
}
