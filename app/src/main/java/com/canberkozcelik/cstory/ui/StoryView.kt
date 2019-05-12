package com.canberkozcelik.cstory.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.text.TextUtils.isEmpty
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.canberkozcelik.cstory.R
import com.canberkozcelik.cstory.helper.OnSwipeListener
import com.canberkozcelik.cstory.model.Story
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.view_story.view.*

/**
 * Created by canberkozcelik on 2019-05-05.
 */
class StoryView : ConstraintLayout, StoryProgressView.OnStoryProgressListener {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_story, this, true)
        story_progress_view.setOnStoryProgressListener(this)
        calculateTouch()
    }

    private lateinit var onStoryInteractionListener: OnStoryInteractionListener
    private lateinit var contentBitmap: Bitmap
    private lateinit var contentUri: String
    private lateinit var story: Story
    private var leftOfScreenPixels: Int = 0

    private val contentTarget = object : Target {
        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            content_image.visibility = View.GONE
        }

        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            onStoryInteractionListener.onContentReady()
            showContent(bitmap)
        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            loadContentOnline()
        }
    }

    private fun showContent(bitmap: Bitmap?) {
        contentBitmap = bitmap!!
        content_image.setImageBitmap(bitmap)
        content_image.visibility = View.VISIBLE
    }

    private fun calculateTouch() {
        val displayMetrics = context.resources.displayMetrics
        leftOfScreenPixels = displayMetrics.widthPixels * 40 / 100
    }

    fun setStory(story: Story) {
        this.story = story
        setContent(story.image)
    }

    private fun setContent(contentUri: String) {
        if (isEmpty(contentUri)) {
            return
        }
        this.contentUri = contentUri
    }

    fun loadContentOffline() {
        Picasso.get()
            .load(contentUri)
            .networkPolicy(NetworkPolicy.OFFLINE)
            .into(contentTarget)
    }

    fun loadContentOnline() {
        Picasso.get()
            .load(contentUri)
            .into(contentTarget)
    }

    fun playStory() {
        story_progress_view.play()
    }

    fun pauseStory() {
        story_progress_view.pause()
    }

    fun resetStory() {
        story_progress_view.reset()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event)
        if (event?.actionMasked == MotionEvent.ACTION_UP) {
            story_progress_view.play()
        }
        return true
    }

    fun destroy() {
        story_progress_view.destroy()
    }

    private val gestureDetector = GestureDetector(getContext(), object : OnSwipeListener() {

        override fun onSwipe(direction: Direction): Boolean {
            handleSwipe(direction)
            return true
        }

        override fun onShowPress(e: MotionEvent) {
            super.onShowPress(e)
            story_progress_view.pause()
        }

        override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
            if (event.rawX.toInt() <= leftOfScreenPixels) {
                onStoryInteractionListener.onPreviousStory()
            } else {
                onStoryInteractionListener.onNextStory()
            }
            return true
        }
    })

    private fun handleSwipe(direction: OnSwipeListener.Direction?) {
        if (direction != null) {
            if (direction === OnSwipeListener.Direction.DOWN) {
                onStoryInteractionListener.onClose(story)
            } else if (direction === OnSwipeListener.Direction.UP) {
                onStoryInteractionListener.onSwipeUp(story)
            }
        }
    }

    fun setOnStoryInteractionListener(onStoryInteractionListener: OnStoryInteractionListener) {
        this.onStoryInteractionListener = onStoryInteractionListener
    }

    override fun onComplete() {
        onStoryInteractionListener.onComplete(story)
    }

    interface OnStoryInteractionListener {
        fun onComplete(story: Story)

        fun onContentReady()

        fun onPreviousStory()

        fun onNextStory()

        fun onClose(story: Story)

        fun onSwipeUp(story: Story)
    }
}