package com.example.os.bussiness.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.example.os.R;
import com.example.os.base.BaseActivity;
import com.example.os.bussiness.IOSListener;
import com.example.os.bussiness.Repository;
import com.example.os.bussiness.adapter.FruitAdapter;
import com.example.os.bussiness.bean.FileResponse;
import com.example.os.thread.ExecuteThread;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements IOSListener.IGetAllFileListener {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.nav_view)
    NavigationView mNavView;

    private FruitAdapter mFruitAdapter;
    private List<FileResponse> mFileList = new ArrayList<>();

    @Override
    protected void initView() {
        mFruitAdapter = new FruitAdapter(this, mFileList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFruitAdapter);
        mProgressDialog.show();

        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_user_management:
                        break;
                    case R.id.nav_bitmap:
                        Intent intent = new Intent(MainActivity.this, BitMapActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }

                return false;
            }
        });
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
                thread.openFile(mFileList.get(position).getUfdId());
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
        mFileList.addAll(fileList);
        mFruitAdapter.notifyDataSetChanged();
    }
}
