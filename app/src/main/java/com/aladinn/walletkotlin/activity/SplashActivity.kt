package com.aladinn.walletkotlin.activity

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.aladinn.walletkotlin.MainActivity
import com.aladinn.walletkotlin.utils.Preference
import org.jetbrains.anko.startActivity

/**
 * @author 韩晓强
 * @date 2019/7/26
 * @describe
 * @e-mail:897971804@qq.com
 */
class SplashActivity : AppCompatActivity() {
    private var isFirst by Preference("isFirst", true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        start()
    }

    private fun start() {
        Handler().postDelayed({
            if (isFirst) {
                startActivity<WelcomeGuideActivity>()
            } else {
                startActivity<MainActivity>()
            }
            finish()
        }, 500)


    }


}