package com.galaxy.graduationproject2011

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.galaxy.common.utils.PreferenceUtils
import com.galaxy.graduationproject2011.entity.Constant
import com.galaxy.graduationproject2011.room.AppDatabase
import com.hjq.umeng.UmengClient

/**
 * Created by Liam.Zheng on 2020/9/23
 *
 * Des:
 */

class MyApplication : Application() {
    var spUserName by PreferenceUtils(Constant.SP_USER_NAME, "")

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
        // 友盟统计、登录、分享 SDK
        UmengClient.init(instance)
    }

}