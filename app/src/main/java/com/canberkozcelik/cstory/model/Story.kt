package com.canberkozcelik.cstory.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Story(
    @SerializedName("id") @Expose val id: String,
    @SerializedName("title") @Expose val title: String,
    @SerializedName("icon") @Expose val icon: String,
    @SerializedName("image") @Expose val image: String,
    @SerializedName("btnTitle") @Expose val btnTitle: String,
    @SerializedName("btnLink") @Expose val btnLink: String,
    @SerializedName("action") @Expose val action: Int,
    @SerializedName("shown") @Expose val shown: Boolean
) : Parcelable