package com.aladinn.walletkotlin.net

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.FlowableSubscribeProxy
import com.uber.autodispose.ObservableSubscribeProxy
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.subscribers.DisposableSubscriber

/**
 * @author 韩晓强
 * @date 2019/9/4
 * @describe
 * @e-mail:897971804@qq.com
 *
 * 对Observable进行线程调度和生命周期绑定
 */

fun <T> Observable<T>.transform(owner: LifecycleOwner): ObservableSubscribeProxy<T> {
    return this.compose(RxScheduler.Obs_io_main()).`as`(
        AutoDispose.autoDisposable(
            AndroidLifecycleScopeProvider.from(
                owner,
                Lifecycle.Event.ON_DESTROY
            )
        )
    )
}

fun <T> Flowable<T>.transform(owner: LifecycleOwner): FlowableSubscribeProxy<T> {
    return this.compose(RxScheduler.Flo_io_main()).`as`(
        AutoDispose.autoDisposable(
            AndroidLifecycleScopeProvider.from(
                owner,
                Lifecycle.Event.ON_DESTROY
            )
        )
    )
}


inline fun <T> netFlowable(
    flowable: Flowable<T>,
    owner: LifecycleOwner,
    subscriber: DisposableSubscriber<T>
) =
    flowable.transform(owner).subscribe(subscriber)

fun <T> netObservable(observable: Observable<T>, owner: LifecycleOwner, subscriber: Observer<T>) =
    observable.transform(owner).subscribe(subscriber)
