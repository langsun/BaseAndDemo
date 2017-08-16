package com.example.sun.demo.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.sun.demo.R;
import com.example.sun.demo.base.BaseActivity;
import com.example.sun.demo.event.TestEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;

/**
 * Created by sun on 16/7/1.
 */
public class GetEventBusActivity extends BaseActivity {
    @Bind(R.id.tv_event_bus)
    TextView mEventBus;

    @Override
    public void setContent() {
        setContentView(R.layout.get_enent_bus_activity);
        EventBus.getDefault().register(this);
    }

    @Override
    public void setModel() {

    }

    @Override
    public void setupView() {
        mCenterTitle.setText("GetEventBusActivity");
        mEventBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetEventBusActivity.this, EventBusActivity.class);
                startActivity(intent);
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void testEventBus(TestEvent event) {
        if (event == null) {
            return;
        }
        mEventBus.setText(event.mMessage);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
