package com.canberkozcelik.cstory.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.canberkozcelik.cstory.ui.adapter.StoryGroupPagerAdapter
import com.canberkozcelik.cstory.data.model.Story
import com.canberkozcelik.cstory.data.model.StoryGroupModel
import com.canberkozcelik.cstory.databinding.ActivityMainBinding
import com.canberkozcelik.cstory.event.StoryEvent
import com.canberkozcelik.cstory.event.StoryGroupEvent
import com.canberkozcelik.cstory.event.StoryGroupEventType
import com.canberkozcelik.cstory.event.StoryEventType
import com.canberkozcelik.cstory.helper.CubeTransformer
import com.canberkozcelik.cstory.ui.custom.StoryViewPager
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by canberkozcelik on 12.05.2019.
 * Last modified 9/19/20 3:15 PM
 */

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.viewPagerStories.apply {
            setOnSwipeListener(object : StoryViewPager.OnSwipeListener {
                override fun onSwipeOutAtStart() {
                    onBackPressed()
                }

                override fun onSwipeOutAtEnd() {
                    onBackPressed()
                }

            })
            offscreenPageLimit = 0
            setPageTransformer(false, CubeTransformer())
            adapter = StoryGroupPagerAdapter(supportFragmentManager, dummyResponse.storyGroups)
            currentItem = 0
            addOnPageChangeListener(this@MainActivity)
            post { onPageScrollStateChanged(ViewPager.SCROLL_STATE_IDLE) }
        }
    }

    @Subscribe
    fun onStoryGroupEvent(event: StoryGroupEvent) {
        when (event.type) {
            StoryGroupEventType.COMPLETE_NEXT -> {
                val currentItem = binding.viewPagerStories.currentItem
                if (currentItem + 1 > dummyResponse.storyGroups.size - 1) {
                    onBackPressed()
                    return
                }
                binding.viewPagerStories.currentItem = currentItem + 1
            }
            StoryGroupEventType.COMPLETE_PREVIOUS -> {
                val currentItem = binding.viewPagerStories.currentItem
                if (currentItem - 1 < 0) {
                    onBackPressed()
                    return
                }
                binding.viewPagerStories.currentItem = currentItem - 1
            }
        }
    }

    @Subscribe
    fun onStoryEvent(event: StoryEvent) {
        when (event.type) {
            StoryEventType.CLOSE -> {
                onBackPressed()
            }
            else -> return
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
        when (state) {
            ViewPager.SCROLL_STATE_IDLE -> {
                EventBus.getDefault().post(StoryEvent(StoryEventType.RESUME, 0))
            }
            ViewPager.SCROLL_STATE_DRAGGING -> {
                EventBus.getDefault().post(StoryEvent(StoryEventType.PAUSE, 0))
            }
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private val dummyResponse = StoryGroupModel(
        arrayListOf(
            StoryGroupModel.StoryGroup(
                username = "canberkozcelik",
                profileImageUrl = "https://lh6.googleusercontent.com/-fp8yaDt1VjU/AAAAAAAAAAI/AAAAAAAAAFI/gP1wzB6SnaQ/photo.jpg?sz=64",
                stories = arrayListOf(
                    Story(
                        "1",
                        "IMAGE",
                        "",
                        "https://r1.ilikewallpaper.net/iphone-8-wallpapers/download/35756/Sunset-Nature-Mountain-Wood-Red-Sky-Lake-iphone-8-wallpaper-ilikewallpaper_com.jpg"
                    ), Story(
                        "5",
                        "VIDEO",
                        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
                        ""
                    ), Story(
                        "2",
                        "IMAGE",
                        "",
                        "https://farm2.staticflickr.com/4038/4261909997_d3bca8acb1_o.jpg"
                    ), Story(
                        "3",
                        "IMAGE",
                        "",
                        "https://c4.staticflickr.com/9/8244/8662127492_cc4bbc48ba_o.jpg"
                    ), Story(
                        "4",
                        "IMAGE",
                        "",
                        "https://farm4.staticflickr.com/43/81492549_0483d217c9_o.jpg"
                    ),
                    Story(
                        "6",
                        "IMAGE",
                        "",
                        "https://c4.staticflickr.com/9/8244/8662127492_cc4bbc48ba_o.jpg"
                    )
                )
            ),
            StoryGroupModel.StoryGroup(
                username = "user2",
                profileImageUrl = "https://lh6.googleusercontent.com/-fp8yaDt1VjU/AAAAAAAAAAI/AAAAAAAAAFI/gP1wzB6SnaQ/photo.jpg?sz=64",
                stories = arrayListOf(
                    Story(
                        "2",
                        "IMAGE",
                        "",
                        "https://farm2.staticflickr.com/4038/4261909997_d3bca8acb1_o.jpg"
                    ), Story(
                        "3",
                        "IMAGE",
                        "",
                        "https://c4.staticflickr.com/9/8244/8662127492_cc4bbc48ba_o.jpg"
                    ), Story(
                        "1",
                        "IMAGE",
                        "",
                        "https://r1.ilikewallpaper.net/iphone-8-wallpapers/download/35756/Sunset-Nature-Mountain-Wood-Red-Sky-Lake-iphone-8-wallpaper-ilikewallpaper_com.jpg"
                    ), Story(
                        "4",
                        "IMAGE",
                        "",
                        "https://farm4.staticflickr.com/43/81492549_0483d217c9_o.jpg"
                    ),
                    Story(
                        "5",
                        "VIDEO",
                        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4\n",
                        ""
                    )
                )
            ),
        )
    )

}
