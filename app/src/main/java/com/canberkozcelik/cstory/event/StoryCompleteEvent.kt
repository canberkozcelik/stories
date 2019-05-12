package com.canberkozcelik.cstory.event

import com.canberkozcelik.cstory.model.Story

class StoryCompleteEvent(val story: Story, val position: Int)