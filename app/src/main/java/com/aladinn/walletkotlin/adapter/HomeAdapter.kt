package com.aladinn.walletkotlin.adapter

import com.aladinn.walletkotlin.R
import com.aladinn.walletkotlin.bean.CurrencyConverter
import com.aladinn.walletkotlin.utils.DateUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author 韩晓强
 * @date 2019/8/30
 * @describe
 * @e-mail:897971804@qq.com
 */
class HomeAdapter:
    BaseQuickAdapter<CurrencyConverter, BaseViewHolder>(R.layout.item_home) {
    override fun convert(holder: BaseViewHolder, item: CurrencyConverter) {

        holder.setText(R.id.tv_type, item.currency)
        holder.setText(R.id.tv_price, "${item.price}")
        holder.setText(R.id.tv_time, "${DateUtils.getTimeLongToYMD(item.createTime)}")
    }
}