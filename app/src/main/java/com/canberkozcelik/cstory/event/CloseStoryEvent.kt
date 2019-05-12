package com.canberkozcelik.cstory.event

import com.canberkozcelik.cstory.model.Story

class CloseStoryEvent(private val story: Story, private val position: Int)
