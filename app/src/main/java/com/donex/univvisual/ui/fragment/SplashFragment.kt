/*
 * /++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright(c) 2019 - 2022, SHOWTIME
 * + 项目名称:TouchScreen
 * + 文件名称:SplashFragment.kt
 * + 最近修改时间:2022/12/22 下午4:51
 * + Author:Youngkingdom
 * + All rights reserved.
 * ++++++++++++++++++++++++++++++++++++++++++++++++\
 */

package com.donex.univvisual.ui.fragment

import androidx.navigation.fragment.findNavController
import com.donex.univvisual.R
import com.donex.univvisual.ui.base.BaseFragment
import com.donex.univvisual.databinding.FragmentSplashBinding
import com.donex.univvisual.util.PermissionUtil

class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_splash
    }

    override fun initView() {
        PermissionUtil.requestPermission(mActivity)
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
        }
    }


}