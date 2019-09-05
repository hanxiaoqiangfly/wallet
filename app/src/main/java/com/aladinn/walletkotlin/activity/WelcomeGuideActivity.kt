package com.aladinn.walletkotlin.activity

import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.aladinn.walletkotlin.MainActivity
import com.aladinn.walletkotlin.R
import com.aladinn.walletkotlin.base.BaseActivity
import com.aladinn.walletkotlin.utils.Preference
import kotlinx.android.synthetic.main.activity_welcome.*
import org.jetbrains.anko.startActivity

/**
 * @author 韩晓强
 * @date 2019/7/29
 * @describe
 * @e-mail:897971804@qq.com
 */
class WelcomeGuideActivity : BaseActivity() {
    private var isFirst by Preference("isFirst", true)

    //    延迟初始化(当程序第一次使用该变量的时候才初始化)
    private val pics: IntArray = intArrayOf(
        R.drawable.welcome1,
        R.drawable.welcome2,
        R.drawable.welcome3,
        R.drawable.welcome4
    )

    //    后期初始化(不支持基本数据类型)
//    private lateinit var views: ArrayList<View>

    lateinit var views: MutableList<View>

    override fun loadData() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_welcome
    }

    override fun initView() {
//        使用的时候初始化
        views = mutableListOf()
        var inflater = LayoutInflater.from(this)
        for (pic in pics) {
            var view = inflater.inflate(R.layout.item_vp, null)
            view.findViewById<ImageView>(R.id.iv).setImageResource(pic)
            views.add(view)
        }
        vp.adapter = object : PagerAdapter() {
            override fun isViewFromObject(p0: View, p1: Any): Boolean {
                return p0 == p1
            }

            override fun getCount(): Int {
                return views.size
            }

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                container.addView(views[position])
                return views[position]
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//                super.destroyItem(container, position, `object`)
                container.removeView(views[position])
            }

        }
        vp.addOnPageChangeListener(MyOnPageChangeListener())
        tv_enter.setOnClickListener {
            isFirst = false
            startActivity<MainActivity>()
            finish()
        }
    }

    private inner class MyOnPageChangeListener : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
        }

        override fun onPageSelected(p0: Int) {
            when (p0) {
                3 -> tv_enter.visibility = View.VISIBLE
                else -> tv_enter.visibility = View.GONE
            }
        }

        override fun onPageScrollStateChanged(p0: Int) {
        }

    }
}