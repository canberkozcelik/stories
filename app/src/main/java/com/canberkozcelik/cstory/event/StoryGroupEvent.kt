/*
 * Created by canberkozcelik at 2020
 * Last modified 9/20/20 2:58 PM
 */

package com.canberkozcelik.cstory.event

enum class StoryGroupEventType {
    COMPLETE_NEXT,
    COMPLETE_PREVIOUS
}

class StoryGroupEvent(val type: StoryGroupEventType)