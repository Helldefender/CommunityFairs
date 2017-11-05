package com.helldefender.enjoylife.modules;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.ui.activity.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Helldefender on 2017/6/8.
 */

public class LabFinanceActivity extends BaseActivity {

    @BindView(R.id.tv_lab_finance_title)
    TextView tvLabFinanceTitle;


    @BindView(R.id.pie_chart_lab_finance)
    PieChart pieChart;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lab_finance;
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.tv_lab_finance_title:
                finish();
                break;
        }
    }

    @Override
    protected void setWidgetListener() {
        super.setWidgetListener();
        tvLabFinanceTitle.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        handleStatusBar();

        int[] date = new int[]{10, 15, 5};
        initPieChat(date);
    }

    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }

    private void initPieChat(int[] date) {
        int sum = date[0] + date[1] + date[2];

        pieChart.setDescription("");
        pieChart.animateXY(1000, 1000);//设置动画效果
        pieChart.setDrawSliceText(false);//圆环上不绘制图例文字
        pieChart.setHoleRadius(68f);//设置内圆环半径
        pieChart.setCenterTextSize(12f);//设置中间文字中大小
        pieChart.setCenterText(generateCenterText(0));
        Legend legend = pieChart.getLegend();//获取图例

        if (sum == 0) {
            pieChart.setData(generateEmptyPieData());
            pieChart.setHighlightPerTapEnabled(false);//点击不响应
            legend.setEnabled(false);//图例隐藏
            return;
        }

        pieChart.setData(generatePieData(date));

        legend.setEnabled(true);//图例显示
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);//图例显示位置设置

        pieChart.setHighlightPerTapEnabled(true);//点击响应
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {//圆盘点击事件
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    /**
     * 中间文字绘制
     *
     * @param sum 总数
     * @return
     */
    private SpannableString generateCenterText(int sum) {
        String total = "报名人数";
        SpannableString s = new SpannableString(total);
        s.setSpan(new RelativeSizeSpan(2f), 0, total.length(), 0);
        s.setSpan(new ForegroundColorSpan(Color.rgb(88, 146, 240)), 0, total.length(), 0);
        s.setSpan(new ForegroundColorSpan(Color.rgb(153, 153, 153)), total.length(), s.length(), 0);
        return s;
    }

    /**
     * 图表数据设置
     *
     * @param date
     * @return
     */
    protected PieData generatePieData(int[] date) {
        ArrayList<Entry> yVals = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<>();

        xVals.add("计算机");
        xVals.add("通信");
        xVals.add("自动化");

        yVals.add(new Entry((float) date[0], 0));
        yVals.add(new Entry((float) date[1], 1));
        yVals.add(new Entry((float) date[2], 2));

        PieDataSet pieDataSet = new PieDataSet(yVals, "");
        pieDataSet.setValueFormatter(new ValueFormatter() {//圆环内文字设置
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                int n = (int) value;

                String str = n + "%";
                if (n == 0) {
                    str = "";
                }
                return str;
            }
        });


        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#FBB963"));
        colors.add(Color.parseColor("#F66775"));
        colors.add(Color.parseColor("#66CDCC"));
//        colors.add(Color.rgb(23, 213, 159));
//        colors.add(Color.rgb(245, 166, 35));
//        colors.add(Color.rgb(184, 233, 134));
        pieDataSet.setColors(colors);//颜色设置

        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(12f);

        return new PieData(xVals, pieDataSet);
    }

    /**
     * 空图表数据设置
     *
     * @return
     */
    protected PieData generateEmptyPieData() {
        ArrayList<Entry> yVals = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<>();

        xVals.add("无设备");
        yVals.add(new Entry((float) 1, 1));

        PieDataSet pieDataSet = new PieDataSet(yVals, "");
        pieDataSet.setValueFormatter(new ValueFormatter() {//圆环内文字设置为空
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return "";
            }
        });

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(153, 153, 153));
        pieDataSet.setColors(colors);

        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(12f);

        PieData pieData = new PieData(xVals, pieDataSet);

        return pieData;
    }

}
