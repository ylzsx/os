package com.example.os.bussiness.activity;

import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Intent;
import android.os.Bundle;
import android.os.SharedMemory;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.os.R;
import com.example.os.base.BaseActivity;
import com.example.os.bussiness.IOSListener;
import com.example.os.bussiness.Repository;
import com.example.os.bussiness.bean.OpenTypeBean;
import com.example.os.utils.ToastUtil;

import butterknife.BindView;


public class LoginActivity extends BaseActivity implements IOSListener.OnIdentifyUserListener {

    @BindView(R.id.edt_login)
    EditText mEdtLogin;
    @BindView(R.id.relative_login)
    RelativeLayout mRelativeLogin;

    public static String TYPE = "openType";

    private String userName;
    private int userId;

    @Override
    protected void initView() {
        mRelativeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(mEdtLogin.getText().toString().trim())) {
                    ToastUtil.showToast("请填写用户名");
                } else {
                    userName = mEdtLogin.getText().toString().trim();
                    Repository.getInstance().identifyUser(LoginActivity.this, mEdtLogin.getText().toString().trim());
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }


    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onIdentifyUser(int data) {

        Intent intent = new Intent(LoginActivity.this, PasswordActivity.class);

        if (data == 0) {

            ToastUtil.showToast("不存在此用户");
            intent.putExtra(TYPE, new OpenTypeBean(0, userName));

        } else {

            ToastUtil.showToast("存在用户信息，请登陆");
            intent.putExtra(TYPE, new OpenTypeBean(1, userName));
        }

        startActivity(intent);
    }
}
