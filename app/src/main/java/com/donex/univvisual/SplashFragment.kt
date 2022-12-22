/*
 * /++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright(c) 2019 - 2022, SHOWTIME
 * + 项目名称:TouchScreen
 * + 文件名称:SplashFragment.kt
 * + 最近修改时间:2022/12/22 下午3:42
 * + Author:Youngkingdom
 * + All rights reserved.
 * ++++++++++++++++++++++++++++++++++++++++++++++++\
 */

package com.donex.univvisual

import com.donex.univvisual.base.BaseFragment
import com.donex.univvisual.databinding.FragmentSplashBinding
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions

class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_splash
    }

    override fun initView() {
        XXPermissions.with(this)
            .permission(
                mutableListOf(
                    Permission.READ_PHONE_STATE,
                )
            )
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>?, all: Boolean) {

                }

                override fun onDenied(permissions: MutableList<String>?, never: Boolean) {
                    super.onDenied(permissions, never)

                }
            })
    }


}