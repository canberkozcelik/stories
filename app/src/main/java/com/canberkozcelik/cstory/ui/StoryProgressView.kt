package com.canberkozcelik.cstory.ui

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar
import androidx.core.content.ContextCompat

/**
 * Created by canberkozcelik on 2019-05-05.
 */
class StoryProgressView : ProgressBar {

    private lateinit var onStoryProgressListener: OnStoryProgressListener
    private lateinit var animator: ObjectAnimator
    private lateinit var animatorListener: Animator.AnimatorListener
    private var duration: Long
    private val maxProgress: Int = 1000
    private val defaultStoryDuration : Long = 5000

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    init {
        setProgressDrawableTiled(ContextCompat.getDrawable(context, com.canberkozcelik.cstory.R.drawable.progress))
        max = maxProgress
        duration = defaultStoryDuration
        createAnimator()
    }

    private fun createAnimator() {
        animator = ObjectAnimator.ofInt(this, "progress", maxProgress)
        animator.duration = duration
        animator.interpolator = LinearInterpolator()
        animatorListener = object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator) {
                reset()
                onStoryProgressListener.onComplete()
            }

            override fun onAnimationCancel(animation: Animator) {

            }

            override fun onAnimationRepeat(animation: Animator) {

            }
        }
        animator.addListener(animatorListener)
    }

    fun pause() {
        animator.pause()
    }

    fun play() {
        if (animator.isStarted) {
            animator.resume()
            return
        }
        animator.cancel()
        progress = 0
        animator.start()
    }

    fun reset() {
        animator.removeAllListeners()
        animator.cancel()
        progress = 0
        animator.addListener(animatorListener)
    }

    fun destroy() {
        animator.removeAllListeners()
        animator.cancel()
    }

    fun setOnStoryProgressListener(onStoryProgressListener: OnStoryProgressListener) {
        this.onStoryProgressListener = onStoryProgressListener
    }

    interface OnStoryProgressListener {
        fun onComplete()
    }
}
