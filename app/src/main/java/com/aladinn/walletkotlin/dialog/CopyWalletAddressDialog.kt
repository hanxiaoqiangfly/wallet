package com.aladinn.walletkotlin.dialog

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.aladinn.walletkotlin.R
import com.aladinn.walletkotlin.utils.ToastUtils
import kotlinx.android.synthetic.main.dialog_copy_wallet_address.*

/**
 * @author 韩晓强
 * @date 2019/9/4
 * @describe
 * @e-mail:897971804@qq.com
 */
class CopyWalletAddressDialog : BaseCenterDialog {
    constructor(context: Context) : super(context){
        setContentView(R.layout.dialog_copy_wallet_address)
        ll_copy.setOnClickListener {
            if (copy()) {
                ToastUtils.showCenterToast("复制成功", ToastUtils.ToastType.SUCCESS)
            } else {
                ToastUtils.showCenterToast("复制失败", ToastUtils.ToastType.ERROR)
            }
            dismiss()
        }
    }

    fun setAddress(address: String): CopyWalletAddressDialog {
        tv_wallet_address.text = address
        return this
    }
    /**
     * 复制内容到剪切板
     *
     * @return
     */
    private fun copy(): Boolean {
        return try {
            //获取剪贴板管理器
            val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            // 创建普通字符型ClipData
            val mClipData = ClipData.newPlainText("Label", tv_wallet_address.text.toString())
            // 将ClipData内容放到系统剪贴板里。
            cm.primaryClip = mClipData
            true
        } catch (e: Exception) {
            false
        }

    }
}