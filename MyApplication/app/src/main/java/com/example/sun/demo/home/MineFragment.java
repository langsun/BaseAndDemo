package com.example.sun.demo.home;

import android.content.Intent;
import android.widget.TextView;

import com.example.sun.demo.R;
import com.example.sun.demo.base.BaseFragment;
import com.example.sun.demo.demo.DemoActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by sun on 16/7/26.
 */
public class MineFragment extends BaseFragment {
    @Bind(R.id.tv_mine_fragment)
    TextView mTextView;

    @Override
    protected void setModel() {

    }

    @Override
    protected void setupView() {


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fargment_mine;
    }

    @OnClick(R.id.tv_mine_fragment)
    public void myOnClick() {
    }
}
