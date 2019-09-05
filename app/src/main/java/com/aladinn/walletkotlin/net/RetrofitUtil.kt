package com.aladinn.walletkotlin.net

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author 韩晓强
 * @date 2019/8/6
 * @describe
 * @e-mail:897971804@qq.com
 */
object RetrofitUtil {

    val baseUrl = "http://nuode.saileikeji.com:10085/"
    val apiService: ApiService = createRetrofit(baseUrl, ApiService::class.java)

    private fun <T> createRetrofit(url: String, service: Class<T>): T {

        return Retrofit.Builder()
            .baseUrl(url)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(service)
    }

    private fun getOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(getHttpLoggingInterceptor())
        .addInterceptor(getInterceptor())
        .build()

    private fun getHttpLoggingInterceptor(): Interceptor {
        var httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    private fun getInterceptor(): Interceptor = Interceptor { chain ->
        var original = chain.request()
        var requestBuilder = original.newBuilder()
            .header("token", "f90b1ba9b6e249dca60e3ab5734b870b")
        chain.proceed(requestBuilder.build())
    }

//    private fun <T> getService(baseUrl: String, service: Class<T>): T {
//        return createRetrofit(baseUrl).create(service)
//    }


}