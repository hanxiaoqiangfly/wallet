package com.aladinn.walletkotlin.bean

import com.sunfusheng.marqueeview.IMarqueeItem
import java.math.BigDecimal

/**
 * @author 韩晓强
 * @date 2019/8/30
 * @describe
 * @e-mail:897971804@qq.com
 */
data class HomeBean(
    val banner: List<BannerBean>,
    val currencyConverter: List<CurrencyConverter>,
    val mgTrend: List<MgTrendBean>,
    val notice: List<Notice>
)

data class Notice(
    val content: String,
    val createTime: String,
    val createUser: Any,
    val id: Int,
    val updateTime: String,
    val updateUser: Any
) : IMarqueeItem {
    override fun marqueeMessage(): CharSequence {
        return content
    }
}

data class CurrencyConverter(
    val createTime: Long,
    val currency: String
) {
    var price: BigDecimal? = null
        get() {
            return field?.setScale(2, BigDecimal.ROUND_HALF_UP) ?: BigDecimal("0.00")
        }
}

data class MgTrendBean(
    val statisticalTime: Long
) {
    var mgPirce = BigDecimal("0")
        get() {
            return field.setScale(5, BigDecimal.ROUND_HALF_UP)
        }
}

data class BannerBean(
    val bannerSize: Double,
    val bannerType: Any,
    val bannerUrl: String,
    val createTime: String,
    val createUser: Any,
    val id: Int,
    val path: Any,
    val updateTime: Any,
    val updateUser: Any
)