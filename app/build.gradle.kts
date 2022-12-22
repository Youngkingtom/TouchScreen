/*
 * /++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright(c) 2019 - 2022, SHOWTIME
 * + 项目名称:TouchScreen
 * + 文件名称:build.gradle.kts
 * + 最近修改时间:2022/12/9 下午2:30
 * + Author:Youngkingdom
 * + All rights reserved.
 * ++++++++++++++++++++++++++++++++++++++++++++++++\
 */

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.donex.univvisual"
    buildToolsVersion = "32.1.0 rc1"
    compileSdk = 32

    defaultConfig {
        applicationId = "com.donex.univvisual"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        named("release").configure {
            isDebuggable = false
            isMinifyEnabled = true
            isJniDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            manifestPlaceholders["APPNAME"] = "书唐"
        }
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_11)
        targetCompatibility(JavaVersion.VERSION_11)
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

}

dependencies {
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")

    //kotlin serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

    //kotlin coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    //android-startup https://github.com/idisfkj/android-startup
    implementation("io.github.idisfkj:android-startup:1.1.0")

    //okhttp
    implementation("com.squareup.okhttp3:okhttp:4.10.0")

    //google gson
    implementation("com.google.code.gson:gson:2.9.0")

    //网络请求封装库  https://github.com/liangjingkanji/Net/
    implementation("com.github.liangjingkanji:Net:3.5.3")

    //安卓设备唯一标识解决方案 https://github.com/gzu-liyujiang/Android_CN_OAID
    implementation("com.github.gzu-liyujiang:Android_CN_OAID:4.2.4")

    //权限请求框架：https://github.com/getActivity/XXPermissions
    implementation("com.github.getActivity:XXPermissions:16.2")

    //屏幕适配方案 https://github.com/JessYanCoding/AndroidAutoSize
    implementation("com.github.JessYanCoding:AndroidAutoSize:v1.2.1")

    //RecyclerView框架 https://github.com/liangjingkanji/BRV
    implementation("com.github.liangjingkanji:BRV:1.3.86")

    //智能下拉刷新框架 https://github.com/scwang90/SmartRefreshLayout
    implementation("io.github.scwang90:refresh-layout-kernel:2.0.5")      //核心必须依赖
    implementation("io.github.scwang90:refresh-header-classics:2.0.5")    //经典刷新头
    implementation("io.github.scwang90:refresh-header-radar:2.0.5")      //雷达刷新头
    implementation("io.github.scwang90:refresh-header-falsify:2.0.5")     //虚拟刷新头
    implementation("io.github.scwang90:refresh-header-material:2.0.5")    //谷歌刷新头
    implementation("io.github.scwang90:refresh-header-two-level:2.0.5")  //二级刷新头
    implementation("io.github.scwang90:refresh-footer-ball:2.0.5")        //球脉冲加载
    implementation("io.github.scwang90:refresh-footer-classics:2.0.5")   //经典加载

    //通用透明状态栏 https://github.com/liangjingkanji/StatusBar
    implementation("com.github.liangjingkanji:StatusBar:2.0.2")


}