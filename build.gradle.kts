/*
 * /++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright(c) 2019 - 2022, SHOWTIME
 * + 项目名称:TouchScreen
 * + 文件名称:build.gradle.kts
 * + 最近修改时间:2022/12/9 下午3:05
 * + Author:Youngkingdom
 * + All rights reserved.
 * ++++++++++++++++++++++++++++++++++++++++++++++++\
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {

    repositories {
        google()
        mavenCentral()
        jcenter()
        maven("https://jitpack.io")
        maven("https://maven.aliyun.com/repository/public")
        maven("https://maven.google.com")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.7.10")
    }
}
allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://maven.aliyun.com/repository/public")
        maven("https://maven.google.com")
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}

tasks.register("clean").configure {
    delete("build")
}