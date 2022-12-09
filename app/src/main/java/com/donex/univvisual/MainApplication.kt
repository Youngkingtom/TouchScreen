/*
 * /++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright(c) 2019 - 2022, SHOWTIME
 * + 项目名称:TouchScreen
 * + 文件名称:MainApplication.kt
 * + 最近修改时间:2022/12/9 下午3:25
 * + Author:Youngkingdom
 * + All rights reserved.
 * ++++++++++++++++++++++++++++++++++++++++++++++++\
 */

package com.donex.univvisual

import android.app.Application
import com.rousetime.android_startup.StartupManager
import com.donex.univvisual.startup.NetStartup

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        StartupManager.Builder()
            .addStartup(NetStartup())
            .build(this)
            .start()
            .await()
    }
}