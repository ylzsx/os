package com.example.lib_thread.bean;

import com.example.lib_thread.service.ThreadManager;

import java.util.ArrayList;

/**
 * @author YangZhaoxin.
 * @since 2019/12/30 9:17.
 * email yangzhaoxin@hrsoft.net.
 */

public class CustomThread {

    protected int threadId;
    protected ArrayList<Integer> memoryBlocks;
    protected ThreadState threadState;

    public CustomThread() {
        this.threadState = ThreadState.NEW;
    }

    /**
     * 启动线程
     */
    public void start() {
        int id = ThreadManager.getInstance().start();
        this.threadId = id;
        this.threadState = ThreadState.RUNNABLE;
    }

    /**
     * 终止线程
     */
    public void stop() {
        ThreadManager.getInstance().stop();
    }

    public int getThreadId() {
        return threadId;
    }

    public void setThreadId(int threadId) {
        this.threadId = threadId;
    }

    public ArrayList<Integer> getMemoryBlocks() {
        return memoryBlocks;
    }

    public void setMemoryBlocks(ArrayList<Integer> memoryBlocks) {
        this.memoryBlocks = memoryBlocks;
    }
}
