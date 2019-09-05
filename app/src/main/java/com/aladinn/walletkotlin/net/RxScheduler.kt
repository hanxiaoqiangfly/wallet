package com.aladinn.walletkotlin.net

import io.reactivex.FlowableTransformer
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author 韩晓强
 * @date 2019/8/6
 * @describe
 * @e-mail:897971804@qq.com
 * 线程调度
 *
 */
class RxScheduler {
    companion object {
        fun <T> Flo_io_main(): FlowableTransformer<T, T> {
            return FlowableTransformer<T, T> { upstream ->
                upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }

        fun <T> Obs_io_main(): ObservableTransformer<T, T> {

            return object : ObservableTransformer<T, T> {
                override fun apply(upstream: Observable<T>): ObservableSource<T> {
                    return upstream.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                }

            }
        }
    }
}