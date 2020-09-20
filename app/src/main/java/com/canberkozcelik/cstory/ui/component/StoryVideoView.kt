/*
 * Created by canberkozcelik at 2020
 * Last modified 9/19/20 7:28 PM
 */

package com.canberkozcelik.cstory.ui.component

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout
import com.canberkozcelik.cstory.R
import com.canberkozcelik.cstory.data.model.Story
import com.canberkozcelik.cstory.databinding.ViewStoryVideoBinding
import com.canberkozcelik.cstory.event.DurationChangedEvent
import com.canberkozcelik.cstory.event.StoryEvent
import com.canberkozcelik.cstory.event.StoryEventType
import com.canberkozcelik.cstory.helper.OnSwipeListener
import com.github.aakira.playermanager.DataSourceCreator
import com.github.aakira.playermanager.ExoPlayerManager
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.util.Util
import org.greenrobot.eventbus.EventBus
import timber.log.Timber

class StoryVideoView : ConstraintLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    private var binding: ViewStoryVideoBinding =
        ViewStoryVideoBinding.inflate(LayoutInflater.from(context), this, true)
    private var leftOfScreenPixels: Int = 0
    private var adapterPosition: Int = -1
    private lateinit var playerManager: ExoPlayerManager

    init {
        calculateTouch()
    }

    private fun calculateTouch() {
        val displayMetrics = context.resources.displayMetrics
        leftOfScreenPixels = displayMetrics.widthPixels * 40 / 100
    }

    fun bind(item: Story, position: Int) {
        adapterPosition = position
        EventBus.getDefault().post(StoryEvent(StoryEventType.PAUSE, position))
        playerManager = ExoPlayerManager.Builder(context).run {
            build(
                renderersFactory = createRenderersFactory(),
                loadControl = createDefaultLoadControl(),
                drmSessionManager = null
            )
        }
        playerManager.injectView(binding.contentVideoPlayerView)
        val dataSourceUrlBuilder = DataSourceCreator.UrlBuilder(
            url = item.videoUrl,
            userAgent = Util.getUserAgent(context, context.getString(R.string.app_name))
        )
        playerManager.setExtractorMediaSource(dataSourceUrlBuilder.build())
        playerManager.addOnStateChangedListener { playWhenReady, playbackState ->
            when (playbackState) {
                Player.STATE_IDLE -> {
                    Timber.d("idle")
                }
                Player.STATE_BUFFERING -> {
                    Timber.d("buffering")
                    binding.loadingProgressBar.show()
                }
                Player.STATE_READY -> {
                    EventBus.getDefault().post(DurationChangedEvent(playerManager.player?.duration, position))
                    binding.loadingProgressBar.hide()
                    EventBus.getDefault().post(StoryEvent(StoryEventType.RESUME, position))
                }
            }
        }
        playerManager.play()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event)
        if (event?.actionMasked == MotionEvent.ACTION_UP) {
            playerManager.play()
            EventBus.getDefault()
                .post(StoryEvent(StoryEventType.RESUME, adapterPosition))
        }
        return true
    }

    private val gestureDetector = GestureDetector(context, object : OnSwipeListener() {

        override fun onSwipe(direction: Direction): Boolean {
            handleSwipe(direction)
            return true
        }

        override fun onShowPress(e: MotionEvent) {
            super.onShowPress(e)
            playerManager.pause()
            EventBus.getDefault().post(
                StoryEvent(
                    StoryEventType.PAUSE,
                    adapterPosition
                )
            )
        }

        override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
            if (event.rawX.toInt() <= leftOfScreenPixels) {
                playerManager.stop()
                EventBus.getDefault().post(
                    StoryEvent(
                        StoryEventType.PREVIOUS,
                        adapterPosition
                    )
                )
            } else {
                playerManager.stop()
                EventBus.getDefault().post(
                    StoryEvent(
                        StoryEventType.NEXT,
                        adapterPosition
                    )
                )
            }
            return true
        }
    })

    private fun handleSwipe(direction: OnSwipeListener.Direction?) {
        if (direction != null) {
            if (direction === OnSwipeListener.Direction.DOWN) {
                playerManager.release()
                EventBus.getDefault().post(
                    StoryEvent(
                        StoryEventType.CLOSE,
                        adapterPosition
                    )
                )
            }
        }
    }
}