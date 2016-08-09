package com.example.sun.demo.lianxi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by sun on 16/8/8.
 */
public abstract class BaseFragmentNew extends Fragment {
    protected View mRootView;
    protected LayoutInflater mInflater;
    protected boolean isVisible;
    protected boolean isPrepare;
    protected boolean isLoaded;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            isVisible = true;
            load();
        }else {
            isVisible = false;
        }
    }
    private void load(){
        if(isVisible && isPrepare && !isLoaded){
            setModel();
            isLoaded = true;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = inflater;
        if(mRootView == null){
            mRootView = inflater.inflate(gerLayoutId(),container,false);
            ButterKnife.bind(this,mRootView);
            setupView();
            isPrepare = true;
            load();

        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if(parent != null){
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        mRootView = null;
    }

    protected abstract int gerLayoutId();
    protected abstract void setupView();
    protected abstract void setModel();
}
