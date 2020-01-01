package com.example.lib_thread.service;

import com.example.lib_thread.bean.CustomThread;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YangZhaoxin.
 * @since 2019/12/30 9:18.
 * email yangzhaoxin@hrsoft.net.
 */

public class ThreadManager {


    private int threadNumber;
    // 临时为线程分配的id
    private int threadId;

    // 运行队列
    private Map<Integer, CustomThread> threadRunningQueue;
    // 阻塞队列
    private Map<Integer, CustomThread> threadBlockedQueue;

    private ThreadManager() {}

    private static class InstanceHolder {
        private static final ThreadManager INSTANCE = new ThreadManager();
    }

    public static ThreadManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void init() {
        threadNumber = 0;
        threadId = 0;
        threadRunningQueue = new HashMap<>();
        threadBlockedQueue = new HashMap<>();
    }

    public int start() {
        threadNumber = threadNumber + 1;
        threadId = threadId + 1;
        return threadId;
    }

    public void stop() {
        threadNumber = threadNumber - 1;
    }

    public Map<Integer, CustomThread> getThreadRunningQueue() {
        return threadRunningQueue;
    }

    public Map<Integer, CustomThread> getThreadBlockedQueue() {
        return threadBlockedQueue;
    }

    public void addThreadRunningQueue(int position, CustomThread thread) {
        threadRunningQueue.put(position, thread);
    }

    public void addThreadBlockedQueue(int position, CustomThread thread) {
        threadBlockedQueue.put(position, thread);
    }
}
