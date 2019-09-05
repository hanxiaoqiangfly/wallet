package com.aladinn.walletkotlin.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import com.aladinn.walletkotlin.R

/**
 * @author 韩晓强
 * @date 2019/9/5
 * @describe
 * @e-mail:897971804@qq.com
 */
class LoadingDialog(context: Context) : Dialog(context, R.style.MyDialogStyleLoading) {
     class Builder(var context: Context) {
        var msg: String = ""
        /**
         * 设置提示信息
         * @param message
         * @return
         */

        fun setMessage(message: String): Builder {
            this.msg = message
            return this
        }

        fun create(): LoadingDialog {

            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.dialog_loading, null)
            val loadingDailog = LoadingDialog(context)
            val msgText = view.findViewById(R.id.tipTextView) as TextView
            msgText.text = msg
            loadingDailog.setContentView(view)
            return loadingDailog

        }

    }
}