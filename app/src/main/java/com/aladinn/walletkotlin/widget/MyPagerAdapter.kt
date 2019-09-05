package com.aladinn.walletkotlin.widget

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * @author 韩晓强
 * @date 2019/8/30
 * @describe
 * @e-mail:897971804@qq.com
 */
class MyPagerAdapter(fragmentManager: FragmentManager, fragments: MutableList<Fragment>) :
    FragmentPagerAdapter(fragmentManager) {
    private  var mFragments: MutableList<Fragment> = fragments

    override fun getItem(p0: Int): Fragment {
        return mFragments[p0]
    }

    override fun getCount(): Int {
        return mFragments.size
    }

}