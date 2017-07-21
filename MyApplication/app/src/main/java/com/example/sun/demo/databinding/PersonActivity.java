package com.example.sun.demo.databinding;

import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.Toast;

import com.example.sun.demo.R;
import com.example.sun.demo.base.BaseActivity;

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
    }

    @Override
    public void setModel() {

    }

    @Override
    public void setupView() {
        person.setName("张三");
        person.setGender("男");
        person.setAge(20);
        binding.setPerson(person);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_change:
                person.setName("李四");
                person.setGender("女");
                person.setAge(23);
                Toast.makeText(PersonActivity.this, person.toString(), Toast.LENGTH_LONG).show();
                updateView();
                break;
        }
    }

    private void updateView(){
        binding.setPerson(person);
        binding.executePendingBindings();
    }

}
