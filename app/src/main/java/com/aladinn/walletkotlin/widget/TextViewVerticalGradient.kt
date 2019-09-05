package com.aladinn.walletkotlin.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.TextView

/**
 * @author 韩晓强
 * @date 2019/9/2
 * @describe
 * @e-mail:897971804@qq.com
 */
class TextViewVerticalGradient @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : TextView(context, attributeSet) {
    private var mLinearGradient: LinearGradient? = null
    private var mPaint: Paint? = null
    private var mViewHeight = 0
    private val mTextBound = Rect()
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mViewHeight = measuredHeight
        mPaint = paint
        val mTipText = text.toString()
        mPaint?.getTextBounds(mTipText, 0, mTipText.length, mTextBound)
        mLinearGradient = LinearGradient(
            0f, 0f, 0f, mViewHeight.toFloat(),
            intArrayOf(0xFFFBCD31.toInt(), 0xFFF45654.toInt()), null, Shader.TileMode.REPEAT
        )
        mPaint?.shader = mLinearGradient
        canvas?.drawText(
            mTipText,
            (measuredWidth / 2 - mTextBound.width() / 2).toFloat(),
            (measuredHeight / 2 + mTextBound.height() / 2).toFloat(),
            mPaint
        )
    }
}