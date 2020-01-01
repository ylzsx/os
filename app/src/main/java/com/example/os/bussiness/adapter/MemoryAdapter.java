package com.example.os.bussiness.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lib_memory.MemoryConstants;
import com.example.lib_memory.bean.MemoryBlock;
import com.example.lib_memory.service.MemoryManager;
import com.example.os.R;
import com.example.os.bussiness.viewholder.MemoryViewHolder;

import java.util.Arrays;

/**
 * Time:2020/1/2 2:46
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class MemoryAdapter extends RecyclerView.Adapter<MemoryViewHolder> {

    private MemoryBlock[] memoryBlocks = new MemoryBlock[MemoryConstants.MEMORY_BLOCK_TOTAL];

    public MemoryAdapter() {
        memoryBlocks = MemoryManager.getInstance().getMemory();
    }

    @NonNull
    @Override
    public MemoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_memeory, viewGroup, false);
        return new MemoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoryViewHolder memoryViewHolder, int i) {
        memoryViewHolder.bindView(memoryBlocks[i].isOccupied());
    }

    @Override
    public int getItemCount() {
        return memoryBlocks.length;
    }
}
