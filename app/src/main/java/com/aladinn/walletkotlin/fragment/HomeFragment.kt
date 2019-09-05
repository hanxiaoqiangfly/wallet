package com.aladinn.walletkotlin.fragment

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import com.aladinn.walletkotlin.R
import com.aladinn.walletkotlin.adapter.HomeAdapter
import com.aladinn.walletkotlin.base.BaseFragment
import com.aladinn.walletkotlin.bean.BannerBean
import com.aladinn.walletkotlin.bean.HomeBean
import com.aladinn.walletkotlin.bean.MgTrendBean
import com.aladinn.walletkotlin.bean.Notice
import com.aladinn.walletkotlin.net.BaseSubscriber
import com.aladinn.walletkotlin.net.RetrofitUtil
import com.aladinn.walletkotlin.net.RxScheduler
import com.aladinn.walletkotlin.net.netFlowable
import com.aladinn.walletkotlin.utils.DateUtils
import com.bumptech.glide.Glide
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.tab.*
import kotlinx.android.synthetic.main.top_bar_main.*

/**
 * @author 韩晓强
 * @date 2019/8/30
 * @describe
 * @e-mail:897971804@qq.com
 */
class HomeFragment : BaseFragment() {
    private lateinit var mHomeAdapter: HomeAdapter
    // 1-->一天  7-->一周  30-->一月
    private var type = 1

    override fun initView() {

        tobBarTitle.text = arguments!!.getString("type")
        initRecyclerView()
        initRadioGroup()
    }

    private fun initRadioGroup() {

        rg.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_one_day -> {
                    type = 1
                    homePage()
                }
                R.id.rb_one_week -> {
                    type = 7
                    WeekTrend()
                }
                R.id.rb_one_month -> {
                    type = 30
                    MonthTrend()
                }
            }
        }
    }

    private fun initRecyclerView() {
        val verticalLayoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        rv.layoutManager = verticalLayoutManager
        mHomeAdapter = HomeAdapter()
        rv.adapter = mHomeAdapter
    }

    override fun loadData() {
        when (type) {
            1 -> homePage()
            7 -> WeekTrend()
            30 -> MonthTrend()
        }
    }

    fun homePage() {
        netFlowable(
            RetrofitUtil.apiService.homePage(),
            this,
            object : BaseSubscriber<HomeBean>(mContext) {
                override fun onHandleSuccess(result: HomeBean?, message: String) {
                    initBanner(result?.banner)
                    initMarqueeView(result?.notice)
                    mHomeAdapter.setNewData(result?.currencyConverter)
                    initLineData(result?.mgTrend, 1)
                }
            })
    }


    fun WeekTrend() = RetrofitUtil.apiService.WeekTrend()
        .compose(RxScheduler.Flo_io_main())
        .subscribe(object : BaseSubscriber<List<MgTrendBean>>(mContext) {
            override fun onHandleSuccess(result: List<MgTrendBean>?, message: String) {
                initLineData(result, 1)
            }

        })

    fun MonthTrend() = netFlowable(
        RetrofitUtil.apiService.MonthTrend(),
        this,
        object : BaseSubscriber<List<MgTrendBean>>(mContext) {
            override fun onHandleSuccess(result: List<MgTrendBean>?, message: String) {
                initLineData(result, 1)
            }
        })


    /**
     * @param mgTrend 走势数据
     * @param type    1-->一天  7-->一周  30-->一月
     */
    private fun initLineData(mgTrend: List<MgTrendBean>?, type: Int) {
        //1.设置x轴和y轴的点
        val entityList = ArrayList<Entry>()
        //准备好每个点对应的x轴数值
        val xList = ArrayList<String>()

        mgTrend?.forEachIndexed { index, mgTrendBean ->
            entityList.add(Entry(index.toFloat(), mgTrendBean.mgPirce.toFloat()))
            if (type == 1) {
                xList.add(DateUtils.getTimeLongToHM(mgTrendBean.statisticalTime))
            } else {
                xList.add(DateUtils.getTimeLongToHM(mgTrendBean.statisticalTime))
            }
        }

//        for (i in mgTrend?.indices!!) {
//            val mgTrendBean = mgTrend[i]
//            if (mgTrendBean != null) {
//                entityList.add(Entry(i.toFloat(), mgTrendBean!!.mgPirce.toFloat()))
//                if (type == 1) {
//                    xList.add(getTimeLongToHM(mgTrendBean!!.statisticalTime))
//                } else {
//                    xList.add(getTimeLongToHM(mgTrendBean!!.statisticalTime))
//                }
//            }
//        }
        //       2. 把数据赋值到你的线条
        val lineDataSet = LineDataSet(entityList, "")
        lineDataSet.color = Color.parseColor("#F45654")//线条颜色
        lineDataSet.setCircleColor(Color.parseColor("#F45654"))//圆点颜色
        lineDataSet.lineWidth = 1f//线条宽度
        //       填充
        lineDataSet.setDrawFilled(true) // 启用填充
        lineDataSet.fillColor = Color.parseColor("#F45654") // 填充白色
        lineDataSet.fillAlpha = 100 // 透明度
        lineDataSet.setDrawCircles(false)
        lineDataSet.setDrawValues(false)
        //设置样式
        val rightAxis = lineChart.axisRight

        //设置图表右边的y轴禁用
        rightAxis.isEnabled = false
        val leftAxis = lineChart.axisLeft
        //设置图表左边的y轴禁用
        leftAxis.isEnabled = true
        leftAxis.labelCount = 4
        //        leftAxis.setAxisMinimum(0f);
        //        leftAxis.setAxisMaximum(0.400f);
        //设置x轴
        val xAxis = lineChart.xAxis
        xAxis.textColor = Color.parseColor("#333333")
        xAxis.textSize = 11f
        xAxis.axisMinimum = 0f
        xAxis.setDrawAxisLine(false)//是否绘制轴线
        xAxis.setDrawGridLines(false)//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true)//绘制标签  指x轴上的对应数值
        xAxis.position = XAxis.XAxisPosition.BOTTOM//设置x轴的显示位置
        xAxis.granularity = 1f//禁止放大后x轴标签重绘
        xAxis.valueFormatter = IndexAxisValueFormatter(xList)


        //透明化图例 左下角的描述
        val legend = lineChart.legend
        legend.form = Legend.LegendForm.NONE
        legend.textColor = Color.parseColor("#999999")

        //隐藏x轴描述 右下角
        val description = Description()
        description.text = "数据来源：上海黄金交易所"
        description.textColor = Color.parseColor("#999999")
        description.isEnabled = false
        lineChart.description = description


        //3.chart设置数据
        val lineData = LineData(lineDataSet)
        lineChart.data = if (mgTrend?.size == 0) null else lineData
        lineChart.setNoDataText("暂无数据")
        lineChart.setScaleEnabled(false)
        lineChart.invalidate() // refresh
    }

    private fun initMarqueeView(notices: List<Notice>?) {
        marqueeView.startWithList(notices as List<Nothing>?)
    }

    private fun initBanner(banners: List<BannerBean>?) {
        banner.setIndicatorGravity(BannerConfig.RIGHT)
            .setImages(banners)
            .setImageLoader(MyImageLoader())
            .start()
    }

    override fun onStart() {
        super.onStart()
        if (marqueeView != null)
            marqueeView.startFlipping()
        if (banner != null)
            banner.startAutoPlay()

    }

    override fun onStop() {
        super.onStop()
        if (marqueeView != null)
            marqueeView.stopFlipping()
        if (banner != null)
            banner.stopAutoPlay()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    inner class MyImageLoader : ImageLoader() {
        override fun displayImage(context: Context, path: Any, imageView: ImageView) {
            var bannerBean = path as BannerBean
            Glide.with(context).load(bannerBean.bannerUrl).into(imageView)
        }
    }
}