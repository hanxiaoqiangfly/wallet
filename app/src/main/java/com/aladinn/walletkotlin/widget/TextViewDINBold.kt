package com.aladinn.walletkotlin.widget

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

/**
 * @author 韩晓强
 * @date 2019/8/30
 * @describe
 * @e-mail:897971804@qq.com
 */
class TextViewDINBold @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    TextView(context, attrs){
    init {
        this.typeface = Typeface.createFromAsset(context.assets, "fonts/DIN-Bold.ttf")
    }
}