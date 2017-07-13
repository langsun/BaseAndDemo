package com.example.sun.demo;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.sun.demo.activity.AchieveShortMessageVerifyCodeActivity;
import com.example.sun.demo.activity.GetEventBusActivity;
import com.example.sun.demo.activity.MyDialogActivity;
import com.example.sun.demo.activity.NotificationActivity;
import com.example.sun.demo.activity.PullToRefreshActivity;
import com.example.sun.demo.base.BaseActivity;
import com.example.sun.demo.home.HomeActivity;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.tv_get_message_code)
    TextView mGetMessageCode;
    @Bind(R.id.tv_pull_to_refresh)
    TextView mPullToRefresh;
    @Bind(R.id.tv_my_dialog)
    TextView mMyDialog;
    @Bind(R.id.tv_event_bus)
    TextView mEventBus;
    @Bind(R.id.tv_notification)
    TextView mNotification;
    @Bind(R.id.tv_home_activity)
    TextView mHomeActivity;
    @Override
    public void setContent() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void setModel() {

    }

    @Override
    public void setupView() {

        mCenterTitle.setText("MainActivity");

        mGetMessageCode.setOnClickListener(this);
        mPullToRefresh.setOnClickListener(this);
        mMyDialog.setOnClickListener(this);
        mEventBus.setOnClickListener(this);
        mNotification.setOnClickListener(this);
        mHomeActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_get_message_code:
                Intent intent1 = new Intent(MainActivity.this, AchieveShortMessageVerifyCodeActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_pull_to_refresh:
                Intent intent2 = new Intent(MainActivity.this, PullToRefreshActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv_my_dialog:
                Intent intent3 = new Intent(MainActivity.this, MyDialogActivity.class);
                startActivity(intent3);
                break;
            case R.id.tv_event_bus:
                Intent intent4 = new Intent(MainActivity.this, GetEventBusActivity.class);
                startActivity(intent4);
                break;
            case R.id.tv_notification:
                Intent intent5 = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent5);
                break;
            case R.id.tv_home_activity:
                Intent intent6 = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent6);
                break;
        }
    }


}
