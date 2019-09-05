package com.aladinn.walletkotlin.base

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author 韩晓强
 * @date 2019/8/30
 * @describe
 * @e-mail:897971804@qq.com
 */
abstract class BaseFragment : Fragment() {


    //Fragment的View加载完毕的标记
    private var isViewCreated: Boolean = false
    //Fragment对用户可见的标记
    private var isUIVisible: Boolean = false
    lateinit var mContext: Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = activity!!

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreated = true

    }

    override fun onStart() {
        super.onStart()
        initView()
        lazyLoad()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            isUIVisible = true
            lazyLoad()
        } else {
            isUIVisible = false
        }
    }

    private fun lazyLoad() {
        if (isViewCreated && isUIVisible) {
            loadData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isUIVisible = false
        isViewCreated = false
    }

    abstract fun initView()

    abstract fun loadData()

    abstract fun getLayoutId(): Int

}