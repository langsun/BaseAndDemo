package com.example.sun.demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by sun on 16/7/26.
 */
public abstract class BaseFragment extends Fragment {
    protected View mRootView;
    protected LayoutInflater mInflater;
    protected boolean isVisible;
    protected boolean isPrepare;
    protected boolean isLoaded;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            load();
        }else {
            isVisible = false;
        }
    }

    private void load() {
        if(isVisible && isPrepare&& !isLoaded){
            isLoaded = true;
            setModel();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, mRootView);
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
    }

    protected abstract void setModel();

    protected abstract void setupView();

    protected abstract int getLayoutId();

}
