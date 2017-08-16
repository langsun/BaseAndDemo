package com.example.sun.demo.databinding;

import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.Toast;

import com.example.sun.demo.R;
import com.example.sun.demo.base.BaseActivity;
import com.example.sun.demo.event.DataBindingEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Random;


/**
 * Created by sun on 17/7/21.
 * <p>
 * 使用databinding
 */

public class PersonActivity extends BaseActivity {
    private Person person = new Person();
    private PersonActivityBinding binding;

    @Override
    public void setContent() {
        binding = DataBindingUtil.setContentView(this, R.layout.person_activity);
        binding.setPersonActivity(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void setModel() {

    }

    @Override
    public void setupView() {
        person.setName("张三");
        person.setGender("男");
        person.setAge(20);
        person.setLight(0);
        binding.setPerson(person);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_change:
                Random random = new Random();
                int age = random.nextInt(100) + 1;
                person.setName("李四" + age);
                person.setGender(age % 2 == 0 ? "女" : "男");
                person.setAge(age);
                person.setLight(random.nextInt(2));
                Toast.makeText(PersonActivity.this, person.toString(), Toast.LENGTH_LONG).show();
                updateView();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changePersonData(DataBindingEvent dataBindingEvent) {
        if (dataBindingEvent != null)
            person = dataBindingEvent.person;
        updateView();
    }

    private void updateView() {
        binding.setPerson(person);
        binding.executePendingBindings();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
