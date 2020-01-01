package com.example.os.bussiness.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.os.R;
import com.example.os.base.BaseActivity;
import com.example.os.bussiness.IOSListener;
import com.example.os.bussiness.Repository;
import com.example.os.bussiness.adapter.BitMapAdapter;
import com.example.os.bussiness.bean.BitMapData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BitMapActivity extends BaseActivity implements IOSListener.OnGetDiskUseInfoListner {


    @BindView(R.id.recycler_bitmap_activity)
    RecyclerView recyclerBitmapActivity;

    private BitMapAdapter adapter;

    private List<BitMapData> bitMapDatas = new ArrayList<>();

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Repository.getInstance().getDiskUseInfo(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_bit_map;
    }


    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onGetDiskUseInfo(List<BitMapData> bitMapDataList) {
        this.bitMapDatas = bitMapDataList;
        initAdapter();
    }

    private void initAdapter() {
        adapter = new BitMapAdapter(bitMapDatas);
        recyclerBitmapActivity.setAdapter(adapter);
        recyclerBitmapActivity.setLayoutManager(new GridLayoutManager(getApplicationContext(), 5, LinearLayoutManager.VERTICAL, false));
    }
}
