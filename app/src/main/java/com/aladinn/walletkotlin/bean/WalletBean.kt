package com.aladinn.walletkotlin.bean

import java.math.BigDecimal

/**
 * @author 韩晓强
 * @date 2019/9/4
 * @describe
 * @e-mail:897971804@qq.com
 */
data class WalletBean(
    val articleList: Any,
    val availableGold: Double,
    val currencyAddress: String,
    val goldPrice: Double,
    val goldServiceFee: Any,
    val mgPrice: Double,
    val mgtransferRate: String,
    val nickName: String,
    val sellmgRate: String,
    val transferoutRate: String,
    val userId: Int,
    val walletAddress: String
) {
    var availableCny: BigDecimal? = null
        get() {
            return field?.setScale(2, BigDecimal.ROUND_HALF_UP) ?: BigDecimal("0.00")
        }
    var availableMg: BigDecimal? = null
        get() {
            return field?.setScale(2, BigDecimal.ROUND_HALF_UP) ?: BigDecimal("0.00")
        }
}