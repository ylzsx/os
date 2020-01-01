package com.example.os.thread;

import com.example.os.bussiness.bean.OperateType;

import java.util.ArrayList;

/**
 * @author YangZhaoxin.
 * @since 2020/1/1 21:04.
 * email yangzhaoxin@hrsoft.net.
 */

public interface IThreadToUser {

    // 线程将分配的内存块给用户
    void getMemoryBlocks(int position, ArrayList<Integer> memoryBlocks);

    // LRU替换后通知界面更新
    void refreshInterface(int position, OperateType operateType);
}
