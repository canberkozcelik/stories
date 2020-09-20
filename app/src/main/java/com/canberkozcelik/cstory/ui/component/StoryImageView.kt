/*
 * Created by canberkozcelik at 2020
 * Last modified 9/19/20 7:26 PM
 */

package com.canberkozcelik.cstory.ui.component

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.canberkozcelik.cstory.data.model.Story
import com.canberkozcelik.cstory.databinding.ViewStoryImageBinding
import com.canberkozcelik.cstory.event.StoryEvent
import com.canberkozcelik.cstory.event.StoryEventType
import com.canberkozcelik.cstory.helper.OnSwipeListener
import org.greenrobot.eventbus.EventBus

class StoryImageView : ConstraintLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    private var binding: ViewStoryImageBinding =
        ViewStoryImageBinding.inflate(LayoutInflater.from(context), this, true)
    private var leftOfScreenPixels: Int = 0
    private var adapterPosition: Int = -1

    init {
        calculateTouch()
    }

    private fun calculateTouch() {
        val displayMetrics = context.resources.displayMetrics
        leftOfScreenPixels = displayMetrics.widthPixels * 40 / 100
    }

    fun bind(item: Story, position: Int) {
        Glide.with(context).load(item.imageUrl).into(binding.contentImage)
        adapterPosition = position
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event)
        if (event?.actionMasked == MotionEvent.ACTION_UP) {
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
            EventBus.getDefault().post(
                StoryEvent(
                    StoryEventType.PAUSE,
                    adapterPosition
                )
            )
        }

        override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
            if (event.rawX.toInt() <= leftOfScreenPixels) {
                EventBus.getDefault().post(
                    StoryEvent(
                        StoryEventType.PREVIOUS,
                        adapterPosition
                    )
                )
            } else {
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