package com.example.os.thread;

import com.example.lib_thread.bean.CustomThread;

/**
 * 删除数据线程
 * @author YangZhaoxin.
 * @since 2019/12/30 10:52.
 * email yangzhaoxin@hrsoft.net.
 */

public class DataDeleteThread extends CustomThread {

    public int deleteData(int MFDId, String fileName) {
        //发送网络请求 Controller
        // 目录管理：文件是否打开
        // 磁盘管理：文件未打开，删除两个表索引项
        return -1;
    }
}
