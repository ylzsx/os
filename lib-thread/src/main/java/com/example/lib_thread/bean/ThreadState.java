package com.example.lib_thread.bean;

/**
 * @author YangZhaoxin.
 * @since 2019/12/30 10:21.
 * email yangzhaoxin@hrsoft.net.
 */

public enum ThreadState {

    NEW,         // 初始状态
    RUNNABLE,    // 运行状态
    BLOCKED,     // 阻塞态
    WAITING,     // 等待态
    TERMINATED   // 终止态
}
