package com.galaxy.graduationproject2011

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

/**
 * Created by Liam.Zheng on 2020/9/23
 *
 * Des:
 */

class MyApplication : Application() {
    companion object {
        private const val TAG = "MyApplication"

        @get:Synchronized
        lateinit var instance: MyApplication
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}