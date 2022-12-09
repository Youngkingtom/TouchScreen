/*
 * /++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright(c) 2019 - 2022, SHOWTIME
 * + 项目名称:TouchScreen
 * + 文件名称:BaseResponseModel.kt
 * + 最近修改时间:2022/12/9 下午3:51
 * + Author:Youngkingdom
 * + All rights reserved.
 * ++++++++++++++++++++++++++++++++++++++++++++++++\
 */

package com.showtimer.touch.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponseModel<T>(
    @SerialName("code")
    val code: Int,
    @SerialName("msg")
    val msg: String,
    @SerialName("data")
    val data: T
)