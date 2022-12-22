/*
 * /++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright(c) 2019 - 2022, SHOWTIME
 * + 项目名称:TouchScreen
 * + 文件名称:PermissionUtil.kt
 * + 最近修改时间:2022/12/22 下午4:42
 * + Author:Youngkingdom
 * + All rights reserved.
 * ++++++++++++++++++++++++++++++++++++++++++++++++\
 */

package com.donex.univvisual.util

import android.content.Context
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions

object PermissionUtil {

    private val defaultPermissions = mutableListOf(
        Permission.READ_PHONE_STATE,
        Permission.MANAGE_EXTERNAL_STORAGE,
        Permission.REQUEST_INSTALL_PACKAGES,
        Permission.WRITE_SETTINGS,
        Permission.CAMERA,
        Permission.RECORD_AUDIO,
        Permission.ACCESS_FINE_LOCATION,
        Permission.ACCESS_COARSE_LOCATION
    )

    fun requestPermission(
        context: Context?,
        permission: MutableList<String>? = defaultPermissions
    ) {
        XXPermissions.with(context)
            .permission(
                permission
            )
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>?, all: Boolean) {

                }

                override fun onDenied(permissions: MutableList<String>?, never: Boolean) {
                    super.onDenied(permissions, never)
                    requestPermission(context, permissions)
                }
            })
    }
}