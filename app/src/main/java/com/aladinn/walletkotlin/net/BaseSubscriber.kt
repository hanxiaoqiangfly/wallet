package com.aladinn.walletkotlin.net

import android.content.Context
import com.aladinn.walletkotlin.dialog.LoadingDialog
import com.aladinn.walletkotlin.utils.ToastUtils
import io.reactivex.subscribers.DisposableSubscriber

/**
 * @author 韩晓强
 * @date 2019/8/6
 * @describe
 * @e-mail:897971804@qq.com
 */
abstract class BaseSubscriber<T>(var context: Context) : DisposableSubscriber<BaseResult<T>>() {
    private val SUCCESS_CODE = "000"
    var loadingDialog: LoadingDialog

    init {
        loadingDialog = LoadingDialog.Builder(context).setMessage("加载中...").create()

    }

    abstract fun onHandleSuccess(result: T?, message: String)

    override fun onComplete() {
        hideLoading()
    }

    override fun onStart() {
        super.onStart()
        showLoading()
    }

    override fun onNext(result: BaseResult<T>) {
        if (SUCCESS_CODE == result.code) {
            onHandleSuccess(result.result, result.message)
        } else {
            onHandleError(result.code, result.message)
        }
    }


    open fun onHandleError(code: String, message: String) {
        ToastUtils.showCenterToast(message, ToastUtils.ToastType.ERROR)
    }

    override fun onError(t: Throwable?) {
        hideLoading()
    }

    private fun showLoading() {
        if (loadingDialog != null && !loadingDialog.isShowing) {
            loadingDialog.show()
        }
    }

    fun hideLoading() {
        if (loadingDialog != null && loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
    }
}