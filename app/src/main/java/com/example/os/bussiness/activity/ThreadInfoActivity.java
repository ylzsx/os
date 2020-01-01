package com.example.os.bussiness.activity;

import android.widget.TextView;

import com.example.lib_thread.service.ThreadManager;
import com.example.os.R;
import com.example.os.base.BaseActivity;
import com.example.os.utils.PieChartUtil;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.HashMap;

import butterknife.BindView;

/**
 * @author YangZhaoxin.
 * @since 2020/1/2 6:12.
 * email yangzhaoxin@hrsoft.net.
 */

public class ThreadInfoActivity extends BaseActivity {
    @BindView(R.id.txt_thread_all)
    TextView mTxtThreadAll;
    @BindView(R.id.txt_thread_running)
    TextView mTxtThreadRunning;
    @BindView(R.id.txt_thread_block)
    TextView mTxtThreadBlock;
    @BindView(R.id.pie_chart)
    PieChart mPieChart;

    private HashMap<String, Float> mthreadInfos = new HashMap<>();

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        int runningThread = ThreadManager.getInstance().getThreadRunningQueue().size();
        int blockThread = ThreadManager.getInstance().getThreadBlockedQueue().size();

        mTxtThreadRunning.setText(String.valueOf(runningThread));
        mTxtThreadBlock.setText(String.valueOf(blockThread));
        mTxtThreadAll.setText(String.valueOf(runningThread + blockThread));

        mthreadInfos.put("执行线程", Float.valueOf(runningThread));
        mthreadInfos.put("阻塞线程", Float.valueOf(blockThread));

        PieChartUtil.getPitChart().setPieChart(mPieChart, mthreadInfos,"线程",true);
        //点击事件
        mPieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                PieEntry pieEntry=(PieEntry)e;
                mPieChart.setCenterText(pieEntry.getLabel());
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_thread_info;
    }

}
