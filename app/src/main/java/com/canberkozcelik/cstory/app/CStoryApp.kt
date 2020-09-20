/*
 * Created by canberkozcelik at 2020
 * Last modified 9/13/20 3:30 PM
 */

package com.canberkozcelik.cstory.app

import android.app.Application
import com.squareup.picasso.BuildConfig
import timber.log.Timber

class CStoryApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}