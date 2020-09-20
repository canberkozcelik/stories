/*
 * Created by canberkozcelik at 2020
 * Last modified 9/19/20 3:17 PM
 */

package com.canberkozcelik.cstory.ui.custom

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

class ScrollDisabledLinearLayoutManager(context: Context) :
    LinearLayoutManager(context, HORIZONTAL, false) {

    override fun canScrollHorizontally(): Boolean {
        return false
    }

    override fun canScrollVertically(): Boolean {
        return false
    }
}