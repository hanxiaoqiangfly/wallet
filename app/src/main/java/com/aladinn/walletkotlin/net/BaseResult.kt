package com.aladinn.walletkotlin.net

/**
 * @author 韩晓强
 * @date 2019/8/6
 * @describe
 * @e-mail:897971804@qq.com
 */
data class BaseResult<T>(val code: String, val message: String, val result: T?, val sumNum: Any?)
