package com.aladinn.walletkotlin.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import java.text.SimpleDateFormat

/**
 * @author 韩晓强
 * @date 2019/8/29
 * @describe
 * @e-mail:897971804@qq.com
 * 被inline标记的函数就是内联函数,其原理就是:在编译时期,把调用这个函数的地方用这行个函数的方法体进替换
 *
 * 注意方法体三个字，相当于把方法体中的内容复制到了具体使用的地方，是执行代码而不是调用方法
 *
 */
inline fun skip(context: Context, clazz: Class<*>) {
    context.startActivity(Intent(context, clazz))
}


inline fun <reified T : Activity> Fragment.startActivity() {
    startActivity(Intent(activity, T::class.java))
}

inline fun getTimeLongToYMD(time: Long): String {
    return getTimeFormat("yyyy-MM-dd hh:mm:ss", time)
}

inline fun getTimeLongToHM(time: Long): String {
    return getTimeFormat("hh:mm", time)
}

inline fun getTimeFormat(format: String, time: Long): String {
    return SimpleDateFormat(format).format(time)
}

inline fun <reified T : Fragment> Context.newFragment(vararg args: Pair<String, String>): T {
    val bundle = Bundle()
    args.let {
        for (arg in args) {
            bundle.putString(arg.first, arg.second)
        }
    }
    return Fragment.instantiate(this, T::class.java.name, bundle) as T
}
