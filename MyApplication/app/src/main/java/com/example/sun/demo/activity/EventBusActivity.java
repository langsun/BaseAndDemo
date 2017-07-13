package com.example.sun.demo.activity;

import android.view.View;
import android.widget.TextView;

import com.example.sun.demo.R;
import com.example.sun.demo.base.BaseActivity;
import com.example.sun.demo.event.TestEvent;

import butterknife.Bind;
import de.greenrobot.event.EventBus;

/**
 * Created by sun on 16/7/1.
 */
public class EventBusActivity extends BaseActivity {
    @Bind(R.id.tv_event_bus)
    TextView mEventBus;

    @Override
    public void setContent() {
        setContentView(R.layout.enent_bus_activity);
    }

    @Override
    public void setModel() {

    }

    @Override
    public void setupView() {
        mCenterTitle.setText("EventBus");
        mEventBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new TestEvent(EventBusActivity.class,"这是得到的EventBus数据"));
                finish();
            }
        });
    }

}
