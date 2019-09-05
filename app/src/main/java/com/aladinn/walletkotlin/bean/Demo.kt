package com.aladinn.walletkotlin.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * @author 韩晓强
 * @date 2019/8/27
 * @describe
 * @e-mail:897971804@qq.com
 * 序列化
 */
@Parcelize
data class Demo(var name: String) : Parcelable