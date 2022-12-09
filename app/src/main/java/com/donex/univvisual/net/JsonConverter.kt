/*
 * /++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright(c) 2019 - 2022, SHOWTIME
 * + 项目名称:TouchScreen
 * + 文件名称:JsonConverter.kt
 * + 最近修改时间:2022/12/9 下午2:24
 * + Author:Youngkingdom
 * + All rights reserved.
 * ++++++++++++++++++++++++++++++++++++++++++++++++\
 */

package com.donex.univvisual.net

import com.drake.net.convert.JSONConvert
import com.google.gson.GsonBuilder
import java.lang.reflect.Type

class JsonConverter() : JSONConvert() {
    val gson = GsonBuilder().serializeNulls().create()
    override fun <R> String.parseBody(succeed: Type): R {
        return gson.fromJson(this, succeed)
    }
}