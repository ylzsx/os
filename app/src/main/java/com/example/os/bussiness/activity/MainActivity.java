package com.example.os.bussiness.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.os.R;
import com.example.os.base.BaseActivity;
import com.example.os.bussiness.IOSListener;
import com.example.os.bussiness.Repository;
import com.example.os.bussiness.adapter.FruitAdapter;
import com.example.os.bussiness.bean.FileResponse;
import com.example.os.thread.ExecuteThread;
import com.example.os.thread.IThreadToUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements IOSListener.IGetAllFileListener,
        IThreadToUser {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private FruitAdapter mFruitAdapter;
    private List<FileResponse> mFileList = new ArrayList<>();

    @Override
    protected void initView() {
        setSupportActionBar(mToolbar);

        mFruitAdapter = new FruitAdapter(this, mFileList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFruitAdapter);
        mProgressDialog.show();

        mFruitAdapter.setItemCloseFileListener(new FruitAdapter.ItemCloseFileListener() {
            @Override
            public void onItemCloseFileListener(int position) {

            }
        });

        mFruitAdapter.setItemOpenFileListener(new FruitAdapter.ItemOpenFileListener() {
            @Override
            public void onItemOpenFileListener(int position) {
                ExecuteThread thread = new ExecuteThread();
                thread.start();
                thread.openFile(mFileList.get(position).getUfdId(), position);
                thread.setThreadToUser(MainActivity.this);
            }
        });
    }

    @Override
    protected void initData() {
        Repository.getInstance().getAllFile(2, this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onError(Throwable t) {
        mProgressDialog.dismiss();
    }

    @Override
    public void onGetAllFileListener(List<FileResponse> fileList) {
        mProgressDialog.dismiss();
        mFruitAdapter.addItems(fileList);
    }

    /**
     * 得到被分配的内存块
     * @param memoryBlocks
     */
    @Override
    public void getMemoryBlocks(ArrayList<Integer> memoryBlocks) {

    }
}
