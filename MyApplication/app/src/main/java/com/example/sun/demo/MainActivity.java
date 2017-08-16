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
import com.example.sun.demo.databinding.Person;
import com.example.sun.demo.databinding.PersonActivity;
import com.example.sun.demo.demo.DemoActivity;
import com.example.sun.demo.event.DataBindingEvent;
import com.example.sun.demo.home.HomeActivity;
import com.example.sun.demo.javaDesignPattern.FactoryActivity;

import java.util.Random;

import butterknife.Bind;
import org.greenrobot.eventbus.EventBus;

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
    @Bind(R.id.tv_demo)
    TextView mDemoActivity;
    @Bind(R.id.tv_Person)
    TextView mPersonActivity;
    @Bind(R.id.tv_factory_activity)
    TextView mFactoryActivity;
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
        mDemoActivity.setOnClickListener(this);
        mPersonActivity.setOnClickListener(this);
        mFactoryActivity.setOnClickListener(this);
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
            case R.id.tv_demo:
                startActivity(new Intent(MainActivity.this, DemoActivity.class));
                break;
            case R.id.tv_Person:
                sendDataBindingEvent();
                startActivity(new Intent(MainActivity.this, PersonActivity.class));
                break;
            case R.id.tv_factory_activity:
                startActivity(new Intent(MainActivity.this, FactoryActivity.class));
                break;
        }
    }

    private void sendDataBindingEvent() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                Person person = new Person();
                for (int i=0;i<10;i++){
                    int age = random.nextInt(100) + 1;
                    person.setName("李四" + age);
                    person.setGender(age % 2 == 0 ? "女" : "男");
                    person.setAge(age);
                    person.setLight(random.nextInt(2));
                    EventBus.getDefault().post(new DataBindingEvent(DemoActivity.class, person));
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }


}
