package com.canberkozcelik.cstory.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.canberkozcelik.cstory.StoryFragment
import com.canberkozcelik.cstory.model.Story

class StoriesPagerAdapter(supportFragmentManager: FragmentManager?, private val stories: List<Story>) : FragmentPagerAdapter(supportFragmentManager) {
    override fun getItem(position: Int): Fragment {
        return StoryFragment.newInstance(stories[position], position)
    }

    override fun getCount(): Int {
        return stories.size
    }

}
