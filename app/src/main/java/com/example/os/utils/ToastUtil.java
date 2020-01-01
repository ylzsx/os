package com.example.os.utils;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.example.os.App;


/**
 * @author YangZhaoxin.
 * @since 2019/10/8 18:05.
 * email yangzhaoxin@hrsoft.net.
 */

public class ToastUtil {

    private static Toast sToast;

    private ToastUtil() {}

    public static void showToast(final String msg) {
        if (sToast == null) {
            sToast = Toast.makeText(App.getInstance(), msg, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(msg);
        }
        // TODO: 解决线程问题
        sToast.show();
    }

    public static void showToast(@StringRes final int resId) {
        showToast(resId);
    }
}
