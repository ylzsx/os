package com.example.os.bussiness;

import com.example.os.bussiness.bean.FileResponse;

import java.util.List;

/**
 * @author YangZhaoxin.
 * @since 2020/1/1 13:32.
 * email yangzhaoxin@hrsoft.net.
 */

public interface IOSListener {

    void onError(Throwable t);

    public interface IGetAllFileListener extends IOSListener {

        void onGetAllFileListener(List<FileResponse> fileList);
    }

    public interface IOpenFileListener extends IOSListener {
        void onOpenFileListener(int response);
    }
}
