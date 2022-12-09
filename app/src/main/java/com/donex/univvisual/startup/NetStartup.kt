/*
 * /++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright(c) 2019 - 2022, SHOWTIME
 * + 项目名称:TouchScreen
 * + 文件名称:NetStartup.kt
 * + 最近修改时间:2022/12/9 下午3:25
 * + Author:Youngkingdom
 * + All rights reserved.
 * ++++++++++++++++++++++++++++++++++++++++++++++++\
 */

package com.donex.univvisual.startup

import android.content.Context
import com.donex.univvisual.BuildConfig
import com.drake.net.NetConfig
import com.drake.net.cookie.PersistentCookieJar
import com.drake.net.okhttp.setConverter
import com.drake.net.okhttp.setDebug
import com.rousetime.android_startup.AndroidStartup
import com.donex.univvisual.net.HttpLoggingInterceptor
import com.donex.univvisual.net.SerializationConverter
import java.util.concurrent.TimeUnit

class NetStartup : AndroidStartup<String>() {
    override fun create(context: Context): String? {
        NetConfig.initialize("https://api.touch.showtimer.cn/", context) {
            /** 请求超时时间 */
            connectTimeout(2, TimeUnit.MINUTES)
            /** 读取超时时间 */
            readTimeout(2, TimeUnit.MINUTES)
            /** 写入超时时间 */
            writeTimeout(2, TimeUnit.MINUTES)
            /** 设置作用域发生异常是否打印 */
            setDebug(BuildConfig.DEBUG)
            /** 设置日志拦截器 */
            addInterceptor(
                HttpLoggingInterceptor(
                    "netLogs",
                    BuildConfig.DEBUG
                ).setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
            )
            /** 设置转换器 */
            setConverter(SerializationConverter())
            /** 设置cookie持久化 */
            cookieJar(PersistentCookieJar(context))
        }
        return this.javaClass.name
    }

    override fun callCreateOnMainThread(): Boolean {
        return true
    }

    override fun waitOnMainThread(): Boolean {
        return true
    }
}