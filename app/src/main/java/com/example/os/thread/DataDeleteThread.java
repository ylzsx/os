package com.example.os.thread;

import com.example.lib_thread.bean.CustomThread;
import com.example.lib_thread.service.ThreadManager;
import com.example.os.bussiness.IOSListener;
import com.example.os.bussiness.Repository;
import com.example.os.utils.ToastUtil;

import java.util.Map;

/**
 * 删除数据线程
 * @author YangZhaoxin.
 * @since 2019/12/30 10:52.
 * email yangzhaoxin@hrsoft.net.
 */

public class DataDeleteThread extends CustomThread {

    private IThreadToUser mIThreadToUser;

    public void setIThreadToUser(IThreadToUser IThreadToUser) {
        mIThreadToUser = IThreadToUser;
    }

    /**
     * 删除文件
     * @param ufdId
     * @param position
     */
    public void deleteData(int ufdId, int position) {
        DataDeleteThread thread = this;
        // 1. 查看该文件是否被打开
        Map<Integer, CustomThread> threadRunningQueue = ThreadManager.getInstance().getThreadRunningQueue();
        if (threadRunningQueue.get(position) != null) {    // 说明该文件被打开
            ToastUtil.showToast("文件被打开，删除请先关闭文件");
        } else {
            // 2. 查询阻塞队列中是否有该文件，有则移除
            Map<Integer, CustomThread> threadBlockedQueue = ThreadManager.getInstance().getThreadBlockedQueue();
            for (Integer key : threadBlockedQueue.keySet()) {
                if (position == key) {
                    threadBlockedQueue.remove(key);
                    break;
                }
            }

            // 3. 执行删除操作
            Repository.getInstance().deleteFile(ufdId, new IOSListener.IDeleteFile() {
                @Override
                public void onDeleteFile(int response) {
                    mIThreadToUser.deleteRefresh(position);
                    // 删除完毕，停止关闭线程
                    thread.stop();
                }

                @Override
                public void onError(Throwable t) {
                    ToastUtil.showToast("删除失败");
                }
            });
        }

    }
}
