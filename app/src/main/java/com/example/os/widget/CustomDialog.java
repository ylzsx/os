package com.example.os.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.os.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author YangZhaoxin.
 * @since 2020/1/2 2:39.
 * email yangzhaoxin@hrsoft.net.
 */

public class CustomDialog extends Dialog {

    @BindView(R.id.edt_fileName)
    EditText mEdtFileName;
    @BindView(R.id.edt_content)
    EditText mEdtContent;
    @BindView(R.id.btn_add_file)
    Button mBtnAddFile;

    private ClickBottomListener mClickBottomListener;

    public CustomDialog(Context context) {
        super(context, R.style.CustomDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
        ButterKnife.bind(this);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //初始化界面控件
        initView();
    }

    private void initView() {
        mBtnAddFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickBottomListener.onClickBottomListener(mEdtFileName.getText().toString(),
                        mEdtContent.getText().toString());

            }
        });
    }

    public interface ClickBottomListener {
        /**
         * 点击确定按钮事件
         */
        public void onClickBottomListener(String fileName, String content);
    }

    public void setOnClickBottomListener(ClickBottomListener clickBottomListener) {
        mClickBottomListener = clickBottomListener;
    }
}
