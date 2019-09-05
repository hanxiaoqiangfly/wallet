package com.aladinn.walletkotlin

import android.app.Application
import kotlin.properties.Delegates

/**
 * @author 韩晓强
 * @date 2019/7/26
 * @describe
 * @e-mail:897971804@qq.com
 */
class MyApplication : Application() {
    //    创建了一个伴生对象，用于获取Application实例
    companion object {
        var instance: MyApplication by Delegates.notNull()

        fun instance() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


}