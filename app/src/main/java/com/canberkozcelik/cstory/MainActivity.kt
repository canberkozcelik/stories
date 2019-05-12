package com.canberkozcelik.cstory

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.canberkozcelik.cstory.adapter.StoriesPagerAdapter
import com.canberkozcelik.cstory.event.*
import com.canberkozcelik.cstory.helper.CubeTransformer
import com.canberkozcelik.cstory.model.DummyResponse
import com.canberkozcelik.cstory.model.Story
import com.canberkozcelik.cstory.ui.StoryViewPager
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    private var currentPagePosition: Int = 0
    private lateinit var stories: List<Story>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stories = DummyResponse.dummy().stories
        setupViewPager()
    }

    private fun setupViewPager() {
        val adapter = StoriesPagerAdapter(supportFragmentManager, stories)
        view_pager_stories.setOnSwipeListener(object : StoryViewPager.OnSwipeListener {
            override fun onSwipeOutAtStart() {
                finish()
            }

            override fun onSwipeOutAtEnd() {
                finish()
            }
        })
        view_pager_stories.offscreenPageLimit = 3
        view_pager_stories.setPageTransformer(false, CubeTransformer())
        view_pager_stories.adapter = adapter
        view_pager_stories.setCurrentItem(currentPagePosition, true)
        view_pager_stories.addOnPageChangeListener(this)
        view_pager_stories.post { onPageScrollStateChanged(ViewPager.SCROLL_STATE_IDLE) }
    }

    override fun onPageScrollStateChanged(state: Int) {
        when (state) {
            ViewPager.SCROLL_STATE_IDLE -> {
                EventBus.getDefault().post(PlayStoryEvent(currentPagePosition))
                EventBus.getDefault().post(StoryShownEvent(currentPagePosition))
            }
            ViewPager.SCROLL_STATE_DRAGGING -> {
                EventBus.getDefault().post(PauseStoryEvent())
            }
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        
    }

    override fun onPageSelected(position: Int) {
        if (currentPagePosition != position) {
            EventBus.getDefault().post(ResetStoryEvent(currentPagePosition))
        }
        currentPagePosition = position
    }

    @Subscribe
    fun onStoryCompleteEvent(event: StoryCompleteEvent) {
        if (event.position == stories.size - 1) {
            finish()
            return
        }
        view_pager_stories.setCurrentItem(event.position + 1, true)
    }

    @Subscribe
    fun onPreviousStoryEvent(event: PreviousStoryEvent) {
        if (event.position != 0) {
            view_pager_stories.setCurrentItem(event.position - 1, true)
        }
    }

    @Subscribe
    fun onNextStoryEvent(event: NextStoryEvent) {
        if (event.position == stories.size - 1) {
            finish()
            return
        }
        view_pager_stories.setCurrentItem(event.position + 1, true)
    }

    @Subscribe
    fun onCloseStoryEvent(event: CloseStoryEvent) {
        finish()
    }

    @Subscribe
    fun onSwipeUpStoryEvent(event: SwipeUpStoryEvent) {
        Toast.makeText(this, event.story.btnLink, Toast.LENGTH_SHORT).show()
    }
}
