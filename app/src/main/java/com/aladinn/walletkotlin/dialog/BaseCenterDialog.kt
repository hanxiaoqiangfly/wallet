package com.aladinn.walletkotlin.dialog

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import com.aladinn.walletkotlin.R

/**
 * @author 韩晓强
 * @date 2019/9/4
 * @describe
 * @e-mail:897971804@qq.com
 */
abstract class BaseCenterDialog : Dialog {
    constructor(context: Context) : this(context, 0)
    constructor(context: Context, themeResId: Int) : super(context, R.style.MyDialogStyle1)

    override fun show() {
        super.show()
        val dialogWindow = window
        dialogWindow!!.setGravity(Gravity.CENTER)
        val m = dialogWindow.windowManager
        val d = m.defaultDisplay
        val p = dialogWindow!!.attributes
        p.dimAmount = 0.3f
        //        p.height = (int) (d.getHeight() * 0.4);
        //        p.y = (int) (d.getHeight() * 0.2);
        p.width = (d.width * 0.8).toInt()
        dialogWindow.attributes = p
    }
}