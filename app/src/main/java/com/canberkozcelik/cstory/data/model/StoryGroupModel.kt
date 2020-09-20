/*
 * Created by canberkozcelik at 2020
 * Last modified 9/19/20 2:43 PM
 */

package com.canberkozcelik.cstory.data.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StoryGroupModel(
    @SerializedName("userStories")
    val storyGroups: ArrayList<StoryGroup>
) : Parcelable {
    @Parcelize
    data class StoryGroup(
        @SerializedName("profileImageUrl")
        val profileImageUrl: String?,
        @SerializedName("stories")
        val stories: ArrayList<Story>,
        @SerializedName("username")
        val username: String?
    ) : Parcelable
}