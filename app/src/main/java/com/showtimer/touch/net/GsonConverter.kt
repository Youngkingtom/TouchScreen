/*
 * /++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright(c) 2019 - 2022, SHOWTIME
 * + 项目名称:TouchScreen
 * + 文件名称:GsonConverter.kt
 * + 最近修改时间:2022/12/9 下午2:24
 * + Author:Youngkingdom
 * + All rights reserved.
 * ++++++++++++++++++++++++++++++++++++++++++++++++\
 */

package com.showtimer.touch.net

import android.util.Log
import com.drake.net.convert.JSONConvert
import com.google.gson.GsonBuilder
import org.json.JSONObject
import java.lang.reflect.Type

class GsonConverter : JSONConvert() {
    val gson = GsonBuilder().serializeNulls().create()
    override fun <R> String.parseBody(succeed: Type): R {
        val data = JSONObject(this).getString("data")
        Log.e("TAG", "parseBody: $data")
        return gson.fromJson(data, succeed)
    }
}