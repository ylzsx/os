package com.example.os.bussiness.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.os.R;
import com.example.os.base.BaseActivity;
import com.example.os.bussiness.IOSListener;
import com.example.os.bussiness.Repository;
import com.example.os.bussiness.adapter.BitMapAdapter;
import com.example.os.bussiness.bean.BitMapData;
import com.example.os.widget.GridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BitMapActivity extends BaseActivity implements IOSListener.OnGetDiskUseInfoListener {


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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4, LinearLayoutManager.VERTICAL, false);
        GridItemDecoration itemDecoration = new GridItemDecoration.Builder(getApplicationContext())
                .setHorizontalSpan(R.dimen.vertical_span)
                .setVerticalSpan(R.dimen.horizontal_span)
                .setColor(getResources().getColor(R.color.colorPrimary))
                .build();
        recyclerBitmapActivity.setLayoutManager(gridLayoutManager);
        recyclerBitmapActivity.addItemDecoration(itemDecoration);
    }
}
