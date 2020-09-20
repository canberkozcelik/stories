package com.canberkozcelik.cstory.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.annotation.Nullable
import androidx.viewpager.widget.ViewPager

/**
 * Created by canberkozcelik on 2019-05-05.
 */
class StoryViewPager : ViewPager {
    private var mStartDragX: Float = 0.toFloat()
    private lateinit var onSwipeListener: OnSwipeListener
    private var mStartDragY: Float = 0.toFloat()

    constructor(context: Context) : super(context)

    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs)

    fun setOnSwipeListener(onSwipeListener: OnSwipeListener) {
        this.onSwipeListener = onSwipeListener
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val x = ev.x
        val y = ev.y
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                mStartDragX = x
                mStartDragY = y
            }
            MotionEvent.ACTION_MOVE -> if (mStartDragY < y + 100 && mStartDragX + 100 < x && currentItem == 0) {
                onSwipeListener.onSwipeOutAtStart()
            } else if (mStartDragX - 100 > x && currentItem == adapter!!.count - 1) {
                onSwipeListener.onSwipeOutAtEnd()
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    interface OnSwipeListener {
        fun onSwipeOutAtStart()
        fun onSwipeOutAtEnd()
    }
}