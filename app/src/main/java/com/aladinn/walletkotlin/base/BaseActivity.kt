package com.aladinn.walletkotlin.base

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.aladinn.walletkotlin.R
import com.jaeger.library.StatusBarUtil

/**
 * @author 韩晓强
 * @date 2019/7/26
 * @describe
 * @e-mail:897971804@qq.com
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        StatusBarUtil.setColor(this, Color.BLACK)
        initView()
        loadData()
        findViewById<View>(R.id.ll_back)?.setOnClickListener { finish() }
    }

    abstract fun loadData()

    abstract fun getLayoutId(): Int


    abstract fun initView()


}