/*
 * /++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright(c) 2019 - 2022, SHOWTIME
 * + 项目名称:TouchScreen
 * + 文件名称:MainFragment.kt
 * + 最近修改时间:2022/12/22 下午3:57
 * + Author:Youngkingdom
 * + All rights reserved.
 * ++++++++++++++++++++++++++++++++++++++++++++++++\
 */

package com.donex.univvisual

import androidx.navigation.fragment.findNavController
import com.donex.univvisual.base.BaseFragment
import com.donex.univvisual.databinding.FragmentMainBinding


class MainFragment : BaseFragment<FragmentMainBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun initView() {
        binding.imageButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_splashFragment)
        }
    }

}