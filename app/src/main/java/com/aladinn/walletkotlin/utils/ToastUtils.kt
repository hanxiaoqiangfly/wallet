package com.aladinn.walletkotlin.utils

import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.aladinn.walletkotlin.MyApplication
import com.aladinn.walletkotlin.R

/**
 * @author 韩晓强
 * @date 2019/8/26
 * @describe
 * @e-mail:897971804@qq.com
 * 单例对象
 */
object ToastUtils {
    enum class ToastType {
        SUCCESS, ERROR, WARN
    }

    var toast: Toast? = null
    fun showCenterToast(message: CharSequence?, type: ToastType) {

        if (toast == null) {
            var toastView = View.inflate(MyApplication.instance(), R.layout.toast, null)
            val tv_message = toastView.findViewById(R.id.tv_message) as TextView
            tv_message.text = message

            with(toastView) { findViewById<ImageView>(R.id.iv_icon) }.apply {
                setImageResource(if (type == ToastType.SUCCESS) R.drawable.duigou else if (type == ToastType.ERROR) R.drawable.error else R.drawable.warn)
            }

            toast = Toast(MyApplication.instance())
            toast?.view = toastView
            toast?.duration = Toast.LENGTH_SHORT
            toast?.setGravity(Gravity.CENTER, 0, 0)
        } else {
            toast?.cancel()
            toast?.show()
        }
    }
}
