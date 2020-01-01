package com.example.lib_memory.bean;

/**
 * 内存块
 * @author YangZhaoxin.
 * @since 2019/12/29 20:33.
 * email yangzhaoxin@hrsoft.net.
 */

public class MemoryBlock {

    private int memoryId;
    private String content;
    private int threadId;
    private int AccessTime;
    private int occupySize;
    private int remainSize;
    private boolean isOccupied;

    public MemoryBlock() {
        this.threadId = -1;
        this.AccessTime = 0;
        this.isOccupied = false;
    }

    public int getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(int memoryId) {
        this.memoryId = memoryId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getThreadId() {
        return threadId;
    }

    public void setThreadId(int threadId) {
        this.threadId = threadId;
    }

    public int getAccessTime() {
        return AccessTime;
    }

    public void setAccessTime(int accessTime) {
        AccessTime = accessTime;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
