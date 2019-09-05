package com.aladinn.walletkotlin.fragment

import android.view.View
import com.aladinn.walletkotlin.R
import com.aladinn.walletkotlin.base.BaseFragment
import kotlinx.android.synthetic.main.top_bar_text.*


/**
 * @author 韩晓强
 * @date 2019/8/30
 * @describe
 * @e-mail:897971804@qq.com
 */
class MarketFragment : BaseFragment() {


    override fun initView() {
        tobBarTitle.text = arguments?.getString("type")
        tobBarRightText.text="我的订单"
        ll_back.visibility = View.INVISIBLE
    }



    override fun loadData() {

    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_market
    }
}