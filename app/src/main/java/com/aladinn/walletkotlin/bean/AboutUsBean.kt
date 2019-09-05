package com.aladinn.walletkotlin.bean

/**
 * @author 韩晓强
 * @date 2019/8/6
 * @describe
 * @e-mail:897971804@qq.com
 */
data class AboutUsBean(val id: Int,
                       val content: String,
                       val createTime: String,
                       var createUser: Any?,
                       var updateTime: Any?,
                       var updateUser: Any?,
                       val workTime: String,
                       val workPhone: String)