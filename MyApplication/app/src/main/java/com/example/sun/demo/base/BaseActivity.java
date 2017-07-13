package com.example.sun.demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.example.sun.demo.R;
import com.example.sun.demo.activity.ActivityPageSetting;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sun on 16/7/1.
 */
public abstract class BaseActivity extends FragmentActivity implements ActivityPageSetting {

    @Nullable
    @Bind(R.id.center_title)
    public TextView mCenterTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContent();
        ButterKnife.bind(this);
        setupView();
        setModel();

    }

    @Nullable
    @OnClick(R.id.left_icon)
    public void onBack(){
        onBackPressed();
    }
}
