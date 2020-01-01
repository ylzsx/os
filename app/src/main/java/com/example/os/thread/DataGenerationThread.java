package com.example.os.thread;

import com.example.lib_thread.bean.CustomThread;
import com.example.os.bussiness.IOSListener;
import com.example.os.bussiness.Repository;
import com.example.os.bussiness.bean.FileDirRequest;
import com.example.os.bussiness.bean.FileResponse;

/**
 * 数据生成线程
 * @author YangZhaoxin.
 * @since 2019/12/30 10:13.
 * email yangzhaoxin@hrsoft.net.
 */

public class DataGenerationThread extends CustomThread {

    private IThreadToUser mIThreadToUser;

    public void setIThreadToUser(IThreadToUser IThreadToUser) {
        mIThreadToUser = IThreadToUser;
    }

    /**
     * 文件创建
     * 1. 创建目录项
     * 2. 创建文件
     * @return
     */
    public void generateData(FileDirRequest fileDirRequest) {
        DataGenerationThread thread = this;
        // 创建目录项
        Repository.getInstance().createFileDir(fileDirRequest, new IOSListener.ICreateFileDir() {
            @Override
            public void onCreateFileDir(int ufdId) {
                fileDirRequest.setUfdId(ufdId);
                // 创建文件
                Repository.getInstance().createFile(fileDirRequest, new ICreateFile() {
                    @Override
                    public void onCreateFile(int response) {
                        // TODO: 为防止内存信息丢失，不可全部刷新界面。之后可做优化
                        FileResponse file = new FileResponse();
                        file.setUfdId(ufdId);
                        file.setFileName(fileDirRequest.getFileName());
                        file.setUserId(fileDirRequest.getUserId());
                        file.setFileLength(fileDirRequest.getFileLength());
                        file.setIsOpenFlag(0);

                        mIThreadToUser.insertRefresh(file);
                        // 执行结束，数据生成线程停止
                        thread.stop();
                    }

                    @Override
                    public void onError(Throwable t) {

                    }
                });
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }
}
