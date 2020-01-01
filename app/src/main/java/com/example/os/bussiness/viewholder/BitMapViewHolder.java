package com.example.os.bussiness.viewholder;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.os.R;
import com.example.os.bussiness.bean.BitMapData;

/**
 * Time:2020/1/1 20:26
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class BitMapViewHolder extends RecyclerView.ViewHolder {

    TextView tvDiskId;
    TextView tvIsUse;
    RelativeLayout relativeBackGround;

    public BitMapViewHolder(@NonNull View itemView) {
        super(itemView);
        tvDiskId = itemView.findViewById(R.id.txt_diskId_item_bitmap);
        tvIsUse = itemView.findViewById(R.id.txt_isUse_item_bitmap);
        relativeBackGround = itemView.findViewById(R.id.relative_item_bitmap);
    }

    public void bindView(BitMapData data) {
        if (data.getIsUseFlag() == 0) {
            relativeBackGround.setBackgroundColor(Color.parseColor("#ffff4d"));
        } else {
            relativeBackGround.setBackgroundColor(Color.parseColor("#22c32e"));
        }
        tvDiskId.setText(String.valueOf(data.getDiskId()));
        tvIsUse.setText(String.valueOf(data.getIsUseFlag()));
    }
}
