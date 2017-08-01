package com.example.sun.demo.demo;

import android.content.Intent;
import android.widget.TextView;

import com.example.sun.demo.R;
import com.example.sun.demo.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by sun on 17/7/14.
 */

public class DemoActivity extends BaseActivity {
    @Bind(R.id.tv_pop_activity)
    TextView mPopupActivity;
    @Bind(R.id.tv_ImageLoader_activity)
    TextView mImageLoaderActivity;
    @Bind(R.id.tv_Ripple_Activity)
    TextView mRippleActivity;
    @Override
    public void setContent() {
        setContentView(R.layout.demo_activity);
    }

    @Override
    public void setModel() {

    }

    @Override
    public void setupView() {

    }

    @OnClick(R.id.tv_pop_activity)
    public void PopActivity(){
        startActivity(new Intent(DemoActivity.this,A_MyPopupWindowActivity.class));
    }
    @OnClick(R.id.tv_ImageLoader_activity)
    public void ImageLoaderActivity(){
        startActivity(new Intent(DemoActivity.this,B_ImageLoaderActivity.class));

    }
    @OnClick(R.id.tv_Ripple_Activity)
    public void RippleActivity(){
        startActivity(new Intent(DemoActivity.this,C_RippleActivity.class));
    }
    @OnClick(R.id.tv_Timer_Activity)
    public void TimerActivity(){
        startActivity(new Intent(DemoActivity.this,D_TimerActivity.class));
    }
}
