package com.example.os.thread;

import com.example.lib_thread.bean.CustomThread;

/**
 * 数据生成线程
 * @author YangZhaoxin.
 * @since 2019/12/30 10:13.
 * email yangzhaoxin@hrsoft.net.
 */

public class DataGenerationThread extends CustomThread {

    /**
     * 文件创建
     * @param MFDId
     * @param dataSize
     * @param fileName
     * @param content
     * @return
     */
    public int generateData(int MFDId, int dataSize, String fileName, String content) {
        // controller
        // 磁盘管理：查询够不够dataSize块磁盘块
        // 目录管理：生成目录id
        // 磁盘管理: 分配两次磁盘索引
        // 成功
        return -1;
    }
}
