/*
 * Created by canberkozcelik at 2020
 * Last modified 9/19/20 7:41 PM
 */

package com.canberkozcelik.cstory.utility.extension

import android.os.Bundle
import androidx.fragment.app.Fragment

inline fun <T : Fragment> T.withArgs(argsBuilder: Bundle.() -> Unit): T = this.apply {
    arguments = Bundle().apply(argsBuilder)
}