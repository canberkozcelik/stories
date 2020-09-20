package com.canberkozcelik.cstory.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.canberkozcelik.cstory.ui.StoryGroupFragment
import com.canberkozcelik.cstory.data.model.StoryGroupModel

/**
 * Created by canberkozcelik on 12.05.2019.
 * Last modified 9/20/20 4:51 PM
 */
class StoryGroupPagerAdapter(
    supportFragmentManager: FragmentManager,
    private val storyGroups: ArrayList<StoryGroupModel.StoryGroup>
) : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return StoryGroupFragment.newInstance(storyGroups[position], position)
    }

    override fun getCount(): Int {
        return storyGroups.size
    }

}
