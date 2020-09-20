/*
 * Created by canberkozcelik at 2020
 * Last modified 9/19/20 2:05 PM
 */

package com.canberkozcelik.cstory.ui.component.storyprogressview

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.canberkozcelik.cstory.R
import com.canberkozcelik.cstory.event.StoryEvent
import com.canberkozcelik.cstory.event.StoryEventType
import com.canberkozcelik.cstory.event.StoryGroupEvent
import com.canberkozcelik.cstory.event.StoryGroupEventType
import org.greenrobot.eventbus.EventBus

class StoryProgressView : LinearLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    private val spaceBetweenProgressBars: Int = 5
    private val max: Int = 100
    private val progressBars: ArrayList<ProgressBar> = ArrayList()
    private val animators: ArrayList<Animator> = ArrayList()
    private var size: Int = -1
    private var currentIndex: Int = 0
    private var callbacks: Callbacks? = null
    internal var isReverse: Boolean = false
    internal var isComplete: Boolean = false

    private fun bind() {
        removeAllViews()
        createProgressBarsForSize()
    }

    private fun createProgressBarsForSize() {
        for (i in 0 until size) {
            val progressBar = createProgressBar()
            progressBar.max = max
            progressBars.add(progressBar)
            addView(progressBar)
            if (i + 1 < size) {
                val space = addSpaceBetweenProgressBars()
                addView(space)
            }
        }
    }

    private fun addSpaceBetweenProgressBars(): View {
        return View(context).apply {
            layoutParams =
                LayoutParams(spaceBetweenProgressBars, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

    private fun createProgressBar(): ProgressBar {
        return ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal).apply {
            layoutParams = LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
            progressDrawable = ContextCompat.getDrawable(context, R.drawable.progress_bar_drawable)
        }
    }

    fun setSize(size: Int?) {
        this.size = size ?: 0
        bind()
    }

    fun setCallbacks(callbacks: Callbacks) {
        this.callbacks = callbacks
    }

    fun skip() {
        if (isComplete) return
        val currentProgressBar = progressBars[currentIndex]
        currentProgressBar.progress = currentProgressBar.max
        val currentAnimator = animators[currentIndex]
        currentAnimator.cancel()
    }

    private fun next() {
        val nextIndex = currentIndex + 1
        if (nextIndex > animators.size - 1) {
            isComplete = true
            callbacks?.onComplete()
            EventBus.getDefault().post(StoryGroupEvent(StoryGroupEventType.COMPLETE_NEXT))
            return
        }
        callbacks?.onNext()
        animators[nextIndex].start()
    }

    fun pause() {
        if (isComplete) return
        val currentProgressBar = progressBars[currentIndex]
        currentProgressBar.progress = currentProgressBar.progress
        animators[currentIndex].pause()
    }

    fun resume() {
        if (isComplete) return
        val currentProgressBar = progressBars[currentIndex]
        currentProgressBar.progress = currentProgressBar.progress
        animators[currentIndex].resume()
    }

    fun reverse() {
        if (isComplete) return
        var p = progressBars[currentIndex]
        p.progress = 0
        isReverse = true
        animators[currentIndex].cancel()
        if (0 <= currentIndex - 1) {
            p = progressBars[currentIndex - 1]
            p.progress = 0
            animators[--currentIndex].start()
        } else {
            animators[currentIndex].start()
        }
    }

    fun play() {
        animators[0].start()
    }

    fun setDuration(duration: Long) {
        animators.clear()
        progressBars.indices.forEach {
            animators.add(createAnimator(it, duration))
        }
    }

    fun setDurationForIndex(duration: Long, index: Int) {
        if (!isReverse) {
            animators[index].duration = duration
        }
    }

    fun clear() {
        progressBars.clear()
        animators.clear()
        size = -1
    }

    fun destroy() {
        animators.forEach {
            it.removeAllListeners()
            it.cancel()
        }
    }

    private fun createAnimator(index: Int, duration: Long): ObjectAnimator {
        return ObjectAnimator.ofInt(progressBars[index], "progress", max).apply {
            interpolator = LinearInterpolator()
            this.duration = duration
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator?) {
                    currentIndex = index
                }

                override fun onAnimationEnd(p0: Animator?) {
                    if (isReverse) {
                        isReverse = false
                        callbacks?.onPrevious()
                        return
                    }
                    next()
                }

                override fun onAnimationCancel(p0: Animator?) {
                    if (index == progressBars.size - 1 || (isReverse && index == 0)) {
                        p0?.removeAllListeners()
                    }
                }

                override fun onAnimationRepeat(p0: Animator?) {}

            })
        }
    }

    interface Callbacks {

        fun onNext()
        fun onPrevious()
        fun onComplete()
    }
}