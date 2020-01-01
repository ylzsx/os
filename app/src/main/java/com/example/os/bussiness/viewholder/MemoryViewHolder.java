package com.example.os.bussiness.viewholder;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.os.R;

/**
 * Time:2020/1/2 2:46
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class MemoryViewHolder extends RecyclerView.ViewHolder {

    private RelativeLayout relativeLayout;

    public MemoryViewHolder(@NonNull View itemView) {
        super(itemView);
        relativeLayout = itemView.findViewById(R.id.relative_item_memory);
    }

    public void bindView(boolean isOccupied) {
        if (isOccupied) {
            relativeLayout.setBackgroundColor(Color.parseColor("#ef2f3e"));
        } else {
            relativeLayout.setBackgroundColor(Color.parseColor("#1fd3ff"));
        }
    }
}
