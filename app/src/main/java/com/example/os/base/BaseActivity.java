package com.example.os.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author YangZhaoxin.
 * @since 2019/12/29 19:22.
 * email yangzhaoxin@hrsoft.net.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mBind;
    protected ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        mBind = ButterKnife.bind(this);
        mProgressDialog = new ProgressDialog(this);
        initView();
        initData();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int getContentView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
