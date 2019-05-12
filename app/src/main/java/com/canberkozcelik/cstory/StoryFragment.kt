package com.canberkozcelik.cstory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.canberkozcelik.cstory.event.*
import com.canberkozcelik.cstory.model.Story
import com.canberkozcelik.cstory.ui.StoryView
import kotlinx.android.synthetic.main.fragment_story.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class StoryFragment : Fragment(), StoryView.OnStoryInteractionListener {
    private var contentReady: Boolean = false
    private var position: Int = -1
    private lateinit var story: Story
    private lateinit var rootView: View

    companion object {
        private const val ARG_STORY = "ARG_STORY"
        private const val ARG_POSITION = "ARG_POSITION"

        fun newInstance(story: Story, position: Int) = StoryFragment().withArgs {
            putParcelable(ARG_STORY, story)
            putInt(ARG_POSITION, position)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_story, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {
            story = arguments!![ARG_STORY] as Story
            position = arguments!![ARG_POSITION] as Int
            setStoryView()
        }
    }

    private fun setStoryView() {
        story_view.setStory(story)
        story_view.setOnStoryInteractionListener(this)
    }

    override fun onComplete(story: Story) {
        EventBus.getDefault().post(StoryCompleteEvent(story, position))
    }

    override fun onContentReady() {
        if (story_view != null) {
            contentReady = true
            story_view.playStory()
        }
    }

    override fun onPreviousStory() {
        if (position == 0) {
            if (story_view != null) {
                story_view.playStory()
            }
            return
        }
        EventBus.getDefault().post(PreviousStoryEvent(position))
    }

    override fun onNextStory() {
        EventBus.getDefault().post(NextStoryEvent(position))
    }

    override fun onClose(story: Story) {
        EventBus.getDefault().post(CloseStoryEvent(story, position))
    }

    override fun onSwipeUp(story: Story) {
        if (story_view != null) {
            story_view.pauseStory()
            EventBus.getDefault().post(SwipeUpStoryEvent(story, position))
        }
    }

    @Subscribe
    fun onPlayStoryEvent(event: PlayStoryEvent) {
        if (event.position == position) {
            if (contentReady) {
                story_view.playStory()
                return
            }
            story_view.loadContentOffline()
        }
    }

    @Subscribe
    fun onPauseStoryEvent(event: PauseStoryEvent) {
        story_view.pauseStory()
    }

    @Subscribe
    fun onResetStoryEvent(event: ResetStoryEvent) {
        if (event.position == position) {
            story_view.resetStory()
        }
    }

    override fun onResume() {
        super.onResume()
        if (contentReady) {
            story_view.playStory()
        }
    }

    override fun onPause() {
        super.onPause()
        story_view.pauseStory()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        contentReady = false
        if (story_view != null) {
            story_view.destroy()
        }
    }
}

inline fun <T : Fragment> T.withArgs(argsBuilder: Bundle.() -> Unit): T = this.apply {
    arguments = Bundle().apply(argsBuilder)
}
