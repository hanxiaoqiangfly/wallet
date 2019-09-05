package com.aladinn.walletkotlin

import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import com.aladinn.walletkotlin.base.BaseActivity
import com.aladinn.walletkotlin.fragment.HomeFragment
import com.aladinn.walletkotlin.fragment.MarketFragment
import com.aladinn.walletkotlin.fragment.MineFragment
import com.aladinn.walletkotlin.fragment.WalletFragment
import com.aladinn.walletkotlin.utils.newFragment
import com.aladinn.walletkotlin.widget.MyPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import kotlin.system.exitProcess

class MainActivity : BaseActivity(), View.OnClickListener {

    lateinit var tv_list: MutableList<TextView>

    override fun loadData() {
//        RetrofitUtil.apiService.aboutUs()
//            .compose(RxScheduler.Flo_io_main())
//            .subscribe(object : BaseSubscriber<AboutUsBean>(this) {
//                override fun onHandleSuccess(result: AboutUsBean?, message: String) {
//                    ToastUtils.showCenterToast(message, ToastUtils.ToastType.SUCCESS)
//
//                }
//
//            })
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        initViewPager()
        initTv()
        initListener()
    }

    private fun initTv() {
        tv_list = mutableListOf<TextView>()
        tv_list.add(tv1)
        tv_list.add(tv2)
        tv_list.add(tv3)
        tv_list.add(tv4)
        changePageSelect(0)
    }

    private fun initListener() {
        ll_tab1.setOnClickListener(this)
        ll_tab2.setOnClickListener(this)
        ll_tab3.setOnClickListener(this)
        ll_tab4.setOnClickListener(this)
    }

    private fun initViewPager() {
        val fragments = mutableListOf<Fragment>()
        fragments.add(newFragment<HomeFragment>(Pair("type",getString(R.string.gold_shopkeeper))))
        fragments.add(newFragment<MarketFragment>(Pair("type",getString(R.string.market))))
        fragments.add(newFragment<WalletFragment>(Pair("type",getString(R.string.gold_shopkeeper))))
        fragments.add(newFragment<MineFragment>(Pair("type",getString(R.string.mine))))
//        fragments.add(HomeFragment.newInstance(getString(R.string.gold_shopkeeper)))
//        fragments.add(MarketFragment.newInstance(getString(R.string.market)))
//        fragments.add(WalletFragment.newInstance(getString(R.string.gold_shopkeeper)))
//        fragments.add(MineFragment.newInstance(getString(R.string.mine)))
        viewpager.offscreenPageLimit = 4
        viewpager.adapter = MyPagerAdapter(supportFragmentManager, fragments)
        viewpager.currentItem = 0
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ll_tab1 -> switchTab(0)
            R.id.ll_tab2 -> switchTab(1)
            R.id.ll_tab3 -> switchTab(2)
            R.id.ll_tab4 -> switchTab(3)
        }
    }

    private fun switchTab(tabPosition: Int) {
        if (viewpager.currentItem != tabPosition) {
            viewpager.setCurrentItem(tabPosition, false)
            changePageSelect(tabPosition)
        }
    }

    private fun changePageSelect(position: Int) {
//         第一种
//        for (indices in tv_list.indices) {
//            tv_list[indices].isEnabled = indices == position
//        }
//        第二种
        tv_list.indices.forEach { indices ->
            tv_list[indices].isEnabled = indices == position
        }

    }

    //声明一个long类型变量：用于存放上一点击“返回键”的时刻
    private var mExitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                toast("再按一次退出程序")
                mExitTime = System.currentTimeMillis()
            } else {
                exitProcess(0)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
