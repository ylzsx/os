package com.example.os.bussiness.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lib_memory.bean.MemoryBlock;
import com.example.lib_memory.service.MemoryManager;
import com.example.os.R;
import com.example.os.bussiness.bean.FileResponse;
import com.example.os.bussiness.bean.OperateType;

import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author YangZhaoxin.
 * @since 2020/1/1 10:54.
 * email yangzhaoxin@hrsoft.net.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private Context mContext;
    private List<FileResponse> mFileList;

    private ItemOpenFileListener mItemOpenFileListener;
    private ItemCloseFileListener mItemCloseFileListener;
    private ItemNextListener mItemNextListener;

    public FruitAdapter(Context context, List<FileResponse> fileList) {
        this.mContext = context;
        this.mFileList = fileList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mContext == null) {
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.file_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        FileResponse file = mFileList.get(i);
        viewHolder.mTxtFileName.setText(file.getFileName());
        viewHolder.mTxtUfdId.setText(file.getUfdId() + "");
        viewHolder.mTxtFileLength.setText(file.getFileLength() + "");

        if (file.getIsOpenFlag() == 1) {    // 文件处于打开状态
            viewHolder.mBtnOpenFile.setText("关闭");
            viewHolder.mLlayoutMemory.setVisibility(View.VISIBLE);
        } else {
            viewHolder.mBtnOpenFile.setText("打开");
            viewHolder.mLlayoutMemory.setVisibility(View.GONE);
        }

        viewHolder.mBtnOpenFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (file.getIsOpenFlag() == 1) {
                    mItemOpenFileListener.onItemOpenFileListener(i, OperateType.CLOSE);
                } else {
                    mItemOpenFileListener.onItemOpenFileListener(i, OperateType.OPEN);
                }
            }
        });

        viewHolder.mBtnDeleteFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemCloseFileListener.onItemCloseFileListener(i);
            }
        });

        viewHolder.mImgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemNextListener.onItemNextListener(i);
            }
        });


        // 得到占用的内存块
        Integer[] block = file.getMemoryBlock();
        if (block != null && block[0] != null) {
            // 得到现在的内存情况
            MemoryBlock[] memory = MemoryManager.getInstance().getMemory();

            viewHolder.mTxtMemory1Block.setText(block[0] + "");
            if (memory[block[0]].isOccupied()) {
                viewHolder.mTxtMemory1Content.setText(memory[block[0]].getContent() + "");
                viewHolder.mTxtMemory1Time.setText(mContext.getString(R.string.access_time_label)
                        + memory[block[0]].getAccessTime() + "");
            }

            viewHolder.mTxtMemory2Block.setText(block[1] + "");
            if (memory[block[1]].isOccupied()) {
                viewHolder.mTxtMemory2Content.setText(memory[block[1]].getContent() + "");
                viewHolder.mTxtMemory2Time.setText(mContext.getString(R.string.access_time_label)
                        + memory[block[1]].getAccessTime() + "");
            }

            viewHolder.mTxtMemory3Block.setText(block[2] + "");
            if (memory[block[2]].isOccupied()) {
                viewHolder.mTxtMemory3Content.setText(memory[block[2]].getContent() + "");
                viewHolder.mTxtMemory3Time.setText(mContext.getString(R.string.access_time_label)
                        + memory[block[2]].getAccessTime() + "");
            }

            viewHolder.mTxtMemory4Block.setText(block[3] + "");
            if (memory[block[3]].isOccupied()) {
                viewHolder.mTxtMemory4Content.setText(memory[block[3]].getContent() + "");
                viewHolder.mTxtMemory4Time.setText(mContext.getString(R.string.access_time_label)
                        + memory[block[3]].getAccessTime() + "");
            }
        }
    }

    @Override
    public int getItemCount() {
        return mFileList.size();
    }

    public void addItems(Collection<FileResponse> datas) {
        mFileList.addAll(datas);
        notifyDataSetChanged();
    }

    public List<FileResponse> getFileList() {
        return mFileList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_fileName)
        TextView mTxtFileName;
        @BindView(R.id.txt_ufdId)
        TextView mTxtUfdId;
        @BindView(R.id.txt_file_length)
        TextView mTxtFileLength;
        @BindView(R.id.btn_open_file)
        Button mBtnOpenFile;
        @BindView(R.id.btn_delete_file)
        Button mBtnDeleteFile;
        @BindView(R.id.txt_memory1_block)
        TextView mTxtMemory1Block;
        @BindView(R.id.txt_memory1_content)
        TextView mTxtMemory1Content;
        @BindView(R.id.txt_memory1_time)
        TextView mTxtMemory1Time;
        @BindView(R.id.txt_memory2_block)
        TextView mTxtMemory2Block;
        @BindView(R.id.txt_memory2_content)
        TextView mTxtMemory2Content;
        @BindView(R.id.txt_memory2_time)
        TextView mTxtMemory2Time;
        @BindView(R.id.txt_memory3_block)
        TextView mTxtMemory3Block;
        @BindView(R.id.txt_memory3_content)
        TextView mTxtMemory3Content;
        @BindView(R.id.txt_memory3_time)
        TextView mTxtMemory3Time;
        @BindView(R.id.txt_memory4_block)
        TextView mTxtMemory4Block;
        @BindView(R.id.txt_memory4_content)
        TextView mTxtMemory4Content;
        @BindView(R.id.txt_memory4_time)
        TextView mTxtMemory4Time;
        @BindView(R.id.llayout_memory)
        LinearLayout mLlayoutMemory;
        @BindView(R.id.img_next)
        ImageView mImgNext;
        @BindView(R.id.rlayout1)
        RelativeLayout mRLayout1;
        @BindView(R.id.rlayout2)
        RelativeLayout mRLayout2;
        @BindView(R.id.rlayout3)
        RelativeLayout mRLayout3;
        @BindView(R.id.rlayout4)
        RelativeLayout mRLayout4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ItemOpenFileListener {
        void onItemOpenFileListener(int position, OperateType operateType);
    }

    public void setItemOpenFileListener(ItemOpenFileListener itemOpenFileListener) {
        mItemOpenFileListener = itemOpenFileListener;
    }

    public interface ItemCloseFileListener {
        void onItemCloseFileListener(int position);
    }

    public void setItemCloseFileListener(ItemCloseFileListener itemCloseFileListener) {
        mItemCloseFileListener = itemCloseFileListener;
    }

    public interface ItemNextListener {
        void onItemNextListener(int position);
    }

    public void setItemNextListener(ItemNextListener itemNextListener) {
        mItemNextListener = itemNextListener;
    }
}
