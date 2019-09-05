package com.aladinn.walletkotlin.net

import com.aladinn.walletkotlin.bean.AboutUsBean
import com.aladinn.walletkotlin.bean.HomeBean
import com.aladinn.walletkotlin.bean.MgTrendBean
import com.aladinn.walletkotlin.bean.WalletBean
import io.reactivex.Flowable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @author 韩晓强
 * @date 2019/8/6
 * @describe
 * @e-mail:897971804@qq.com
 */
interface ApiService {

    @GET("common/aboutUs")
    fun aboutUs(): Flowable<BaseResult<AboutUsBean>>

    @GET("homepage/mgTrend/homePage")
    fun homePage(): Flowable<BaseResult<HomeBean>>

    @GET("homepage/mgTrend/WeekTrend")
    fun WeekTrend(): Flowable<BaseResult<List<MgTrendBean>>>

    @GET("homepage/mgTrend/MonthTrend")
    fun MonthTrend(): Flowable<BaseResult<List<MgTrendBean>>>

    @FormUrlEncoded
    @POST("wallet/walletInfo")
    fun walletInfo(@Field("userId") userId: String): Flowable<BaseResult<WalletBean>>

}