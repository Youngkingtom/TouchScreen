/*
 * /++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright(c) 2019 - 2022, SHOWTIME
 * + 项目名称:TouchScreen
 * + 文件名称:MainActivity.kt
 * + 最近修改时间:2022/12/9 下午3:06
 * + Author:Youngkingdom
 * + All rights reserved.
 * ++++++++++++++++++++++++++++++++++++++++++++++++\
 */

package com.showtimer.touch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.drake.net.Post
import com.drake.net.utils.scopeNetLife
import com.showtimer.touch.model.BaseResponseModel
import com.showtimer.touch.model.test
import com.showtimer.touch.net.GsonConverter
import com.showtimer.touch.net.SerializationConverter
import com.shwotimer.touch.R
import com.shwotimer.touch.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.button.setOnClickListener {
            scopeNetLife {
                val data =
                    Post<test>("touch/appversion/getActive") {
                    }.await()
                binding.textView.text = data.description
            }
        }
    }
}