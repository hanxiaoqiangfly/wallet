package com.aladinn.walletkotlin.fragment

import android.view.View
import com.aladinn.walletkotlin.R
import com.aladinn.walletkotlin.base.BaseFragment
import com.aladinn.walletkotlin.bean.WalletBean
import com.aladinn.walletkotlin.dialog.CopyWalletAddressDialog
import com.aladinn.walletkotlin.net.BaseSubscriber
import com.aladinn.walletkotlin.net.RetrofitUtil
import com.aladinn.walletkotlin.net.netFlowable
import kotlinx.android.synthetic.main.fragment_wallet.*
import kotlinx.android.synthetic.main.top_bar_main.*

/**
 * @author 韩晓强
 * @date 2019/8/30
 * @describe
 * @e-mail:897971804@qq.com
 */
class WalletFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_copy -> {
                CopyWalletAddressDialog(mContext!!).setAddress(tv_wallet_address.text.toString()).show()
            }
        }
    }

    override fun initView() {
        tobBarTitle.text = arguments?.getString("type")
        iv_copy.setOnClickListener(this)
    }


    override fun loadData() {
        netFlowable(
            RetrofitUtil.apiService.walletInfo("1564032581636"),
            this,
            object : BaseSubscriber<WalletBean>(mContext) {
                override fun onHandleSuccess(result: WalletBean?, message: String) {
                    tv_wallet_address.text = result?.walletAddress
                    tvdm_cny.text = "${result?.availableCny}"
                    tvdm_mg.text = "${result?.availableMg}"
                }
            })
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_wallet
    }
}