package com.example.os.thread;

import com.example.lib_memory.service.MemoryManager;
import com.example.lib_thread.bean.CustomThread;
import com.example.lib_thread.service.ThreadManager;
import com.example.os.bussiness.IOSListener;
import com.example.os.bussiness.Repository;
import com.example.os.bussiness.bean.FileContentResponse;
import com.example.os.bussiness.bean.OperateType;

import java.util.ArrayList;

/**
 * @author YangZhaoxin.
 * @since 2019/12/30 11:00.
 * email yangzhaoxin@hrsoft.net.
 */

public class ExecuteThread extends CustomThread {

    // 为展示LRU置换过程，设置的一个缓存区
    private ArrayList<String> buffer = new ArrayList<>();
    private int page = 0;

    private IThreadToUser mThreadToUser;

    public void setThreadToUser(IThreadToUser threadToUser) {
        mThreadToUser = threadToUser;
    }

    public void readDisk(int ufdId, int position) {

        ArrayList<Integer> memoryBlocks = this.memoryBlocks;
        // 1. 查看缓存区是否有
        if (!buffer.isEmpty()) {
            MemoryManager.getInstance().LRU(memoryBlocks, buffer.get(0));
            buffer.remove(0);
            // 通知界面更新
            mThreadToUser.refreshInterface(position, OperateType.OPEN);
        } else {
            // 2. 缓存区无，则发送网络请求，加入缓存区
            Repository.getInstance().searchFile(ufdId, page, new IOSListener.ISearchFileListener() {
                @Override
                public void onSearchFileListener(ArrayList<FileContentResponse> fileContentResponses) {
                    for (FileContentResponse fileContentRespons : fileContentResponses) {
                        buffer.add(fileContentRespons.getContent());
                    }
                    page++;
                    MemoryManager.getInstance().LRU(memoryBlocks, buffer.get(0));
                    buffer.remove(0);
                    mThreadToUser.refreshInterface(position, OperateType.OPEN);
                }

                @Override
                public void onError(Throwable t) {

                }
            });
        }
    }

    public void operateFile(int ufdId, int position, OperateType operateType) {
        if (operateType == OperateType.OPEN) {
            openFile(ufdId, position);
        } else {
            closeFile(ufdId, position);
        }
    }

    /**
     * 关闭文件
     * 1. 释放内存
     * 2. 修改文件目录表
     * 3. 通知用户界面
     * 4. 结束当前进程
     * @param ufdId
     * @param position
     */
    private void closeFile(int ufdId, int position) {
        if(MemoryManager.getInstance().free(this.getMemoryBlocks()) > 0) {

            // 修改文件目录表
            Repository.getInstance().closeFile(ufdId, new IOSListener.ICloseFileListener() {
                @Override
                public void onCloseFileListener(int response) {
                    // 通知用户界面
                    mThreadToUser.refreshInterface(position, OperateType.CLOSE);
                    // 结束当前进程
                    ThreadManager.getInstance().getThreadRunningQueue().remove(position);
                }

                @Override
                public void onError(Throwable t) {

                }
            });
        }
    }

    /**
     * 打开文件
     * 1. 查询内存是否够用
     * 2. 内存够用则分配内存，线程进入执行队列；否则线程进入阻塞队列
     * @param ufdId
     * @param position recyclerView item的位置
     */
    public void openFile(int ufdId, int position) {
        final CustomThread thread = this;
        final ArrayList<Integer> memoryBlocks = MemoryManager.getInstance().malloc();
        if (memoryBlocks != null && memoryBlocks.size() != 0) {     // 内存够用
            Repository.getInstance().openFile(ufdId, new IOSListener.IOpenFileListener() {
                @Override
                public void onOpenFileListener(int response) {
                    // 记录线程占用的内存块
                    thread.setMemoryBlocks(memoryBlocks);
                    // 线程进入执行队列
                    ThreadManager.getInstance().addThreadRunningQueue(position, thread);
                    // 返回当前线程占用的内存块号
                    mThreadToUser.getMemoryBlocks(position, memoryBlocks);
                }
                @Override
                public void onError(Throwable t) {
                    // 文件打开失败，释放内存
                    MemoryManager.getInstance().free(memoryBlocks);
                }
            });
        } else {    // 内存不够用，线程进入阻塞队列
            ThreadManager.getInstance().addThreadBlockedQueue(position, thread);
        }
    }
}
