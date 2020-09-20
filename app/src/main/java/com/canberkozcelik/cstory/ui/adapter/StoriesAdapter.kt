/*
 * Created by canberkozcelik at 2020
 * Last modified 9/19/20 3:30 PM 
 */

package com.canberkozcelik.cstory.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canberkozcelik.cstory.data.model.Story
import com.canberkozcelik.cstory.data.model.StoryType
import com.canberkozcelik.cstory.databinding.ListItemStoryImageBinding
import com.canberkozcelik.cstory.databinding.ListItemStoryVideoBinding

class StoriesAdapter(private val storyList: ArrayList<Story>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> StoryImageViewHolder(
                ListItemStoryImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            1 -> StoryVideoViewHolder(
                ListItemStoryVideoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> StoryImageViewHolder(
                ListItemStoryImageBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = storyList[position]
        if (holder is StoryImageViewHolder) {
            holder.bind(item, position)
        } else if (holder is StoryVideoViewHolder) {
            holder.bind(item, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = storyList[position]
        if (item.type == StoryType.IMAGE.type) {
            return 0
        } else if (item.type == StoryType.VIDEO.type) {
            return 1
        }
        return -1
    }

    override fun getItemCount(): Int {
        return storyList.size
    }

    inner class StoryVideoViewHolder(private val binding: ListItemStoryVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Story, position: Int) {
            binding.root.bind(item, position)
        }
    }

    inner class StoryImageViewHolder(private val binding: ListItemStoryImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Story, position: Int) {
            binding.root.bind(item, position)
        }
    }
}
