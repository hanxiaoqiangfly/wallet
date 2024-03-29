package com.aladinn.walletkotlin.utils

import android.content.Context
import android.content.SharedPreferences
import com.aladinn.walletkotlin.MyApplication
import kotlin.reflect.KProperty

/**
 * @author 韩晓强
 * @date 2019/8/6
 * @describe
 * @e-mail:897971804@qq.com
 * 由于SP支持存储多种类型的数据，所以在这里我们定义成了一个带泛型的类
 *
 */
class Preference<T>(val name: String, private val default: T) {
    //    第一个变量prefs使用了懒加载lazy来缓存SharedPreference对象,在首次使用时初始化
    private val prefs: SharedPreferences by lazy {
        MyApplication.instance().getSharedPreferences("sp_data", Context.MODE_PRIVATE)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return getSharePreferences(name, default)
    }

    @Suppress("UNCHECKED_CAST")
    private fun getSharePreferences(name: String, default: T): T = with(prefs) {

        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type of data cannot be find!")
        }

        return res as T

    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putSharePreferences(name, value)
    }

    private fun putSharePreferences(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type of data cannot be saved!")
        }.apply()
    }
}