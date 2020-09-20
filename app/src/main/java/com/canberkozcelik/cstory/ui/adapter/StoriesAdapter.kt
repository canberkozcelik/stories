/*
 * Created by canberkozcelik at 2020
 * Last modified 9/19/20 3:30 PM 
 */

package com.canberkozcelik.cstory.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canberkozcelik.cstory.data.model.Story
import com.canberkozcelik.cstory.data.model.StoryType
import com.canberkozcelik.cstory.databinding.ListItemStoryImageBinding
import com.canberkozcelik.cstory.databinding.ListItemStoryVideoBinding
import com.canberkozcelik.cstory.utility.CacheDataSourceCustom
import com.canberkozcelik.cstory.utility.Constants

class StoriesAdapter(context: Context, private val storyList: ArrayList<Story>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var caching = CacheDataSourceCustom(context, Constants.MAX_CACHE_SIZE, Constants.MAX_FILE_SIZE)

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
        }
        return 1
    }

    override fun getItemCount(): Int {
        return storyList.size
    }

    inner class StoryVideoViewHolder(private val binding: ListItemStoryVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Story, position: Int) {
            binding.root.bind(item)
        }
    }

    inner class StoryImageViewHolder(private val binding: ListItemStoryImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Story, position: Int) {
            binding.root.bind(item, position)
        }
    }
}
