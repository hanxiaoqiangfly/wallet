package com.aladinn.walletkotlin.widget

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

/**
 * @author 韩晓强
 * @date 2019/9/2
 * @describe
 * @e-mail:897971804@qq.com
 */
class TextViewDINMedium @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : TextView(context, attributeSet) {
    init {
        this.typeface = Typeface.createFromAsset(context.assets, "fonts/DIN-Medium.ttf")
    }
}