package com.aladinn.walletkotlin.fragment

import android.view.View
import com.aladinn.walletkotlin.R
import com.aladinn.walletkotlin.activity.AboutActivity
import com.aladinn.walletkotlin.base.BaseFragment
import com.aladinn.walletkotlin.utils.startActivity
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.android.synthetic.main.top_bar_main.*

/**
 * @author 韩晓强
 * @date 2019/8/30
 * @describe
 * @e-mail:897971804@qq.com
 */
class MineFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_about_us -> {
                startActivity<AboutActivity>()
            }
        }
    }

    override fun initView() {
        tobBarTitle.text = arguments?.getString("type")
        tv_about_us.setOnClickListener(this)
    }


    override fun loadData() {
/**/
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }
}