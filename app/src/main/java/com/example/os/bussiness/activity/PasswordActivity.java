package com.example.os.bussiness.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.os.R;
import com.example.os.base.BaseActivity;
import com.example.os.bussiness.IOSListener;
import com.example.os.bussiness.Repository;
import com.example.os.bussiness.bean.OpenTypeBean;
import com.example.os.bussiness.bean.SignUpRequestBody;
import com.example.os.utils.CacheUtil;
import com.example.os.utils.ToastUtil;

import butterknife.BindView;


public class PasswordActivity extends BaseActivity implements IOSListener.LoadListener {

    @BindView(R.id.txt_password)
    TextView mTxtPassword;
    @BindView(R.id.img_password_eye_open)
    ImageView mImgPasswordEyeOpen;
    @BindView(R.id.view_password_blind)
    View mViewPasswordBlind;
    @BindView(R.id.edt_pwd)
    EditText mEdtPwd;
    @BindView(R.id.tv_password_or_sign_up)
    TextView mTxtPasswordOrSignUp;
    @BindView(R.id.relative_pwd)
    RelativeLayout mRelativePwd;

    private OpenTypeBean bean;

    public static final int SIGNUP = 0;
    public static final int SIGNIN = 1;

    @Override
    protected void initView() {
        bean = (OpenTypeBean)getIntent().getSerializableExtra(LoginActivity.TYPE);

        if (bean.getType() == SIGNUP) {
            mTxtPassword.setText("注册");
        }

        mViewPasswordBlind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPasswordBlind.setVisibility(View.GONE);
                mImgPasswordEyeOpen.setVisibility(View.VISIBLE);
                mEdtPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });

        mImgPasswordEyeOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPasswordBlind.setVisibility(View.VISIBLE);
                mImgPasswordEyeOpen.setVisibility(View.GONE);
                mEdtPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        mRelativePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (! "".equals(mEdtPwd.getText().toString().trim())) {
                   if (bean.getType() == SIGNUP) {
                       Repository.getInstance().createUser(PasswordActivity.this,
                              new SignUpRequestBody(bean.getUserName(), mEdtPwd.getText().toString().trim()));
                   } else {
                       Repository.getInstance().login(PasswordActivity.this,
                               new SignUpRequestBody(bean.getUserName(), mEdtPwd.getText().toString().trim()));
                   }
               } else {
                   ToastUtil.showToast("密码不能为空");
               }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_password;
    }


    @Override
    public void onError(Throwable t) {
        if (bean.getType() == 0) {
            ToastUtil.showToast("注册失败");
        } else {
            ToastUtil.showToast("登陆失败");
        }

    }

    @Override
    public void onLoadListener(int id) {
        CacheUtil.put(bean.getUserName(), id);
        Intent intent = new Intent(PasswordActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
