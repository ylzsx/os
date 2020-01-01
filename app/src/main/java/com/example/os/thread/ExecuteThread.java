package com.example.os.thread;

import com.example.lib_memory.service.MemoryManager;
import com.example.lib_thread.bean.CustomThread;
import com.example.lib_thread.service.ThreadManager;
import com.example.os.bussiness.IOSListener;
import com.example.os.bussiness.Repository;

import java.util.ArrayList;

/**
 * @author YangZhaoxin.
 * @since 2019/12/30 11:00.
 * email yangzhaoxin@hrsoft.net.
 */

public class ExecuteThread extends CustomThread {

    // 为展示LRU置换过程，设置的一个缓存区
    private ArrayList<String> buffer = new ArrayList<>();

    private IThreadToUser mThreadToUser;

    public void setThreadToUser(IThreadToUser threadToUser) {
        mThreadToUser = threadToUser;
    }

    public int readDisk(int ufdId) {
        //  int page
        // 发送网络请求前查看缓存区是否有
        // 有的话不发送请求，检查该线程对应的内存块是否沾满
        // 若沾满则一次调入一个数据 LRU
        // 若为空调用 malloc
        // 缓存区无，则发送网络请求，加入缓存区
        // 查看缓存区是否有
        // 有的话不发送请求，检查该线程对应的内存块是否沾满
        // 若沾满则一次调入一个数据 LRU
        // 若为空调用 malloc

        return -1;
    }

    /**
     * 打开文件
     * 1. 查询内存是否够用
     * 2. 内存够用则分配内存，线程进入执行队列；否则线程进入阻塞队列
     * @param ufdId
     * @param position recyclerView item的位置
     */
    public void openFile(int ufdId, int position) {
        final int threadId = this.threadId;
        final CustomThread thread = this;
        final ArrayList<Integer> memoryBlocks = MemoryManager.getInstance().malloc(threadId);
        if (memoryBlocks != null && memoryBlocks.size() != 0) {     // 内存够用
            Repository.getInstance().openFile(ufdId, new IOSListener.IOpenFileListener() {
                @Override
                public void onOpenFileListener(int response) {
                    // 线程进入执行队列
                    ThreadManager.getInstance().addThreadRunningQueue(position, thread);
                    // 返回当前线程占用的内存块号
                    mThreadToUser.getMemoryBlocks(memoryBlocks);
                }
                @Override
                public void onError(Throwable t) {
                    // 文件打开失败，释放内存
                    MemoryManager.getInstance().free(threadId, memoryBlocks);
                }
            });
        } else {    // 内存不够用，线程进入阻塞队列
            ThreadManager.getInstance().addThreadBlockedQueue(position, thread);
        }
    }
}
