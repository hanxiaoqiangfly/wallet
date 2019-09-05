package com.aladinn.walletkotlin.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author 韩晓强
 * @date 2019/9/3
 * @describe
 * @e-mail:897971804@qq.com
 */
object DateUtils {

    //ᬬ2017-10-01 10:00:00
    val nowDateTime: String
        get() {
            return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
        }

    fun getTimeLongToYMD(time: Long): String = getTimeFormat("yyyy-MM-dd hh:mm:ss", time)


    fun getTimeLongToHM(time: Long): String = getTimeFormat("hh:mm", time)


    private fun getTimeFormat(format: String, time: Long): String {
        return SimpleDateFormat(format).format(time)
    }
}

