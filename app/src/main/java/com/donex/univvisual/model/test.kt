/*
 * /++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright(c) 2019 - 2022, SHOWTIME
 * + 项目名称:TouchScreen
 * + 文件名称:test.kt
 * + 最近修改时间:2022/12/9 下午4:17
 * + Author:Youngkingdom
 * + All rights reserved.
 * ++++++++++++++++++++++++++++++++++++++++++++++++\
 */

package com.donex.univvisual.model

import kotlinx.serialization.Serializable

@Serializable
data class test(
    val description: String,
    val name: String,
    val url: String,
    val version: String
)