/*
 * Created by canberkozcelik at 2020
 * Last modified 9/20/20 2:09 PM
 */

package com.canberkozcelik.cstory.event

enum class StoryEventType {
    RESUME,
    PAUSE,
    PREVIOUS,
    NEXT,
    CLOSE,
    COMPLETE
}

class StoryEvent(val type: StoryEventType, val adapterPosition: Int)
