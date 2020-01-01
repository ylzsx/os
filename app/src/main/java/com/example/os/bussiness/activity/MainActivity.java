package com.example.os.bussiness.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.lib_thread.bean.CustomThread;
import com.example.lib_thread.service.ThreadManager;
import com.example.os.App;
import com.example.os.R;
import com.example.os.base.BaseActivity;
import com.example.os.bussiness.IOSListener;
import com.example.os.bussiness.Repository;
import com.example.os.bussiness.adapter.FruitAdapter;
import com.example.os.bussiness.bean.FileDirRequest;
import com.example.os.bussiness.bean.FileResponse;
import com.example.os.bussiness.bean.OperateType;
import com.example.os.common.CacheKey;
import com.example.os.thread.DataDeleteThread;
import com.example.os.thread.DataGenerationThread;
import com.example.os.thread.ExecuteThread;
import com.example.os.thread.IThreadToUser;
import com.example.os.utils.CacheUtil;
import com.example.os.widget.CustomDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private Map<Integer, CustomThread> threadRunningQueue = ThreadManager.getInstance().getThreadRunningQueue();

    @Override
    protected void initView() {
        setSupportActionBar(mToolbar);

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
                    case R.id.nav_exit:
                        App.getInstance().removeAllActivity();
                        CacheUtil.clear();
                        Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.nav_memory:
                        Intent intent2 = new Intent(MainActivity.this, MemoryInfoActivity.class);
                        startActivity(intent2);
                    default:
                        break;
                }

                return false;
            }
        });

        // 点击创建文件：启动文件创建线程
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog = new CustomDialog(MainActivity.this);

                customDialog.setOnClickBottomListener(new CustomDialog.ClickBottomListener() {
                    @Override
                    public void onClickBottomListener(String fileName, String content) {
                        DataGenerationThread thread = new DataGenerationThread();
                        thread.start();
                        int userId = CacheUtil.getSP().getInt(CacheKey.MFDId, 1);
                        FileDirRequest fileDirRequest = new FileDirRequest();
                        fileDirRequest.setUserId(userId);
                        fileDirRequest.setContent(content);
                        fileDirRequest.setFileName(fileName);
                        fileDirRequest.setFileLength((int) Math.ceil(content.length()/4.0));

                        thread.setIThreadToUser(MainActivity.this);
                        thread.generateData(fileDirRequest);
                        customDialog.dismiss();
                    }
                });
                customDialog.show();
            }
        });

        // 删除文件
        mFruitAdapter.setItemDeleteFileListener(new FruitAdapter.ItemDeleteFileListener() {
            @Override
            public void onItemDeleteFileListener(int position) {
                DataDeleteThread thread = new DataDeleteThread();
                thread.start();
                thread.setIThreadToUser(MainActivity.this);
                thread.deleteData(mFruitAdapter.getFileList().get(position).getUfdId(), position);
            }
        });

        // 打开（关闭）文件
        mFruitAdapter.setItemOpenFileListener(new FruitAdapter.ItemOpenFileListener() {
            @Override
            public void onItemOpenFileListener(int position, OperateType operateType) {
                ExecuteThread thread = (ExecuteThread) threadRunningQueue.get(position);
                if (thread == null) {
                    thread = new ExecuteThread();
                    thread.start();
                }
                thread.operateFile(mFruitAdapter.getFileList().get(position).getUfdId(), position, operateType);
                thread.setThreadToUser(MainActivity.this);
            }
        });

        // 下一步
        mFruitAdapter.setItemNextListener(new FruitAdapter.ItemNextListener() {
            @Override
            public void onItemNextListener(int position) {
                ExecuteThread thread = (ExecuteThread) threadRunningQueue.get(position);
                if (thread != null) {
                    thread.readDisk(mFruitAdapter.getFileList().get(position).getUfdId(), position);
                }
            }
        });
    }

    @Override
    protected void initData() {
        int mfdId = CacheUtil.getSP().getInt(CacheKey.MFDId, 1);
        Repository.getInstance().getAllFile(mfdId, this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onError(Throwable t) {
        mProgressDialog.dismiss();
    }

    /**
     * 查找该用户所有文件
     * @param fileList
     */
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
    public void getMemoryBlocks(int position, ArrayList<Integer> memoryBlocks) {
        mFruitAdapter.getFileList().get(position).setIsOpenFlag(1);
        mFruitAdapter.getFileList().get(position).setMemoryBlock(memoryBlocks.toArray(new Integer[memoryBlocks.size()]));
        mFruitAdapter.notifyItemChanged(position);
    }

    @Override
    public void refreshInterface(int position, OperateType operateType) {
        if (operateType == OperateType.CLOSE) {
            mFruitAdapter.getFileList().get(position).setIsOpenFlag(0);
            // TODO: 查看阻塞队列是否有进程
        }
        mFruitAdapter.notifyItemChanged(position);
    }

    /**
     * 新建文件后进行局部刷新
     * @param response
     */
    @Override
    public void insertRefresh(FileResponse response) {
        mFruitAdapter.getFileList().add(response);
        mFruitAdapter.notifyItemInserted(mFruitAdapter.getFileList().size());
    }

    /**
     * 删除文件后进行界面刷新
     * @param position
     */
    @Override
    public void deleteRefresh(int position) {
        mFruitAdapter.getFileList().remove(position);
        mFruitAdapter.notifyItemRemoved(position);
    }

    @Override
    protected void onDestroy() {
        // 退出时关掉所有文件
        Map<Integer, CustomThread> threadRunningQueue = ThreadManager.getInstance().getThreadRunningQueue();
        for (Integer position : threadRunningQueue.keySet()) {
            ExecuteThread thread = (ExecuteThread) threadRunningQueue.get(position);
            thread.operateFile(mFruitAdapter.getFileList().get(position).getUfdId(), position, OperateType.CLOSE);
            thread.setThreadToUser(MainActivity.this);
        }
        super.onDestroy();
    }
}
