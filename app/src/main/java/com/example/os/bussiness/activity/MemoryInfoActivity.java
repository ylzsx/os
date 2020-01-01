package com.example.os.bussiness.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.lib_memory.MemoryConstants;
import com.example.lib_memory.bean.MemoryBlock;
import com.example.lib_memory.service.MemoryManager;
import com.example.os.R;
import com.example.os.base.BaseActivity;
import com.example.os.bussiness.adapter.MemoryAdapter;
import com.example.os.bussiness.util.GridItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemoryInfoActivity extends BaseActivity {

    @BindView(R.id.txt_memory_occupied)
    TextView mTxtMemoryOccupied;
    @BindView(R.id.txt_memory_unoccupied)
    TextView mTxtMemoryUnoccupied;
    @BindView(R.id.recycler_memory_blocks)
    RecyclerView mRecyclerMemoryBlocks;
    private MemoryAdapter adapter;

    private int occupyed;

    @Override
    protected void initView() {
        adapter = new MemoryAdapter();
        mRecyclerMemoryBlocks.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4,
                LinearLayoutManager.VERTICAL, false);

        GridItemDecoration itemDecoration = new GridItemDecoration.Builder(this)
                .setVerticalSpan(R.dimen.vertical_span)
                .setHorizontalSpan(R.dimen.horizontal_span)
                .setColor(getResources().getColor(R.color.half_translate))
                .build();
        mRecyclerMemoryBlocks.setLayoutManager(gridLayoutManager);
        mRecyclerMemoryBlocks.addItemDecoration(itemDecoration);
    }

    @Override
    protected void initData() {
        for (MemoryBlock block:
                MemoryManager.getInstance().getMemory()) {
            if (block.isOccupied()) {
                occupyed++;
            }
        }

        mTxtMemoryOccupied.setText(String.valueOf(occupyed));
        mTxtMemoryUnoccupied.setText(String.valueOf(MemoryConstants.MEMORY_BLOCK_TOTAL - occupyed));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_memory_info;
    }


}
