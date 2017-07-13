package com.example.sun.demo.activity;

import android.content.IntentFilter;
import android.widget.EditText;

import com.example.sun.demo.R;
import com.example.sun.demo.base.BaseActivity;
import com.example.sun.demo.broadcastReceiver.GetMessageBroadcastReceiver;

import butterknife.Bind;

/**
 * Created by sun on 16/6/30.
 */
public class AchieveShortMessageVerifyCodeActivity extends BaseActivity {

    @Bind(R.id.tv_code)
    EditText mCode;
    private GetMessageBroadcastReceiver mGetMessageBroadcastReceiver;


    @Override
    public void setContent() {
        setContentView(R.layout.achieve_short_message_verify_code_activity);
    }

    @Override
    public void setModel() {

    }

    @Override
    public void setupView() {
        mCenterTitle.setText("aaaa");

        //获取短信验证码自动填写
        if (mGetMessageBroadcastReceiver == null) {
            mGetMessageBroadcastReceiver = new GetMessageBroadcastReceiver(mCode);
            IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
            registerReceiver(mGetMessageBroadcastReceiver, filter);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mGetMessageBroadcastReceiver);
    }
}
