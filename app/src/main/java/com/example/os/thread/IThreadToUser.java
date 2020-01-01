package com.example.os.thread;

import java.util.ArrayList;

/**
 * @author YangZhaoxin.
 * @since 2020/1/1 21:04.
 * email yangzhaoxin@hrsoft.net.
 */

public interface IThreadToUser {

    // 线程将分配的内存块给用户
    void getMemoryBlocks(ArrayList<Integer> memoryBlocks);
}
