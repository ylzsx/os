package com.example.os.bussiness.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.os.R;
import com.example.os.bussiness.bean.BitMapData;
import com.example.os.bussiness.viewholder.BitMapViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Time:2020/1/1 20:26
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class BitMapAdapter extends RecyclerView.Adapter<BitMapViewHolder> {

    private List<BitMapData> bitMapDatas = new ArrayList<>();

    public BitMapAdapter(List<BitMapData> bitMapDatas) {
        this.bitMapDatas = bitMapDatas;
    }

    @NonNull
    @Override
    public BitMapViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bitmap_activity, viewGroup,false);
        return new BitMapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BitMapViewHolder bitMapViewHolder, int i) {
        bitMapViewHolder.bindView(bitMapDatas.get(i));
    }

    @Override
    public int getItemCount() {
        return bitMapDatas.size();
    }
}
