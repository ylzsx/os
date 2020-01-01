package com.example.os.bussiness;


import com.example.os.bussiness.bean.BitMapData;
import com.example.os.bussiness.bean.FileContentResponse;
import com.example.os.bussiness.bean.FileResponse;

import java.util.ArrayList;
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


    public interface ISearchFileListener extends IOSListener {
        void onSearchFileListener(ArrayList<FileContentResponse> fileContentResponses);
    }

    public interface ICloseFileListener extends IOSListener {
        void onCloseFileListener(int response);
    }

    /**
     * 获取磁盘位示图回调
     */
    public interface OnGetDiskUseInfoListener extends IOSListener {
        void onGetDiskUseInfo(List<BitMapData> bitMapDataList);
    }

    /**
     * 获取用户名验证信息回调
     */
    public interface OnIdentifyUserListener extends IOSListener {
        void onIdentifyUser(int userId);
    }

    /**
     * 登陆或者注册的回调
     */
    public interface LoadListener extends IOSListener {
        void onLoadListener(int id);
    }

    /**
     * 创建文件目录项
     */
    public interface ICreateFileDir extends IOSListener {
        void onCreateFileDir(int ufdId);
    }

    /**
     * 创建文件
     */
    public interface ICreateFile extends IOSListener {
        void onCreateFile(int response);
    }

    public interface IDeleteFile extends IOSListener {
        void onDeleteFile(int response);
    }
}
