package com.example.sun.demo.demo;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupWindow;

import com.example.sun.demo.R;

/**
 * Created by sun on 17/7/14.
 */

public class A_MyPopupWindow extends PopupWindow {
    private Context mContext;
    private View.OnClickListener mOnClickListener;

    public A_MyPopupWindow(Context context, View view, View.OnClickListener onClickListener) {

        this.mContext = context;
        this.mOnClickListener = onClickListener;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.a_my_popup_window, null);
        ImageButton imageButton = (ImageButton) contentView.findViewById(R.id.ib_pop);
        imageButton.setOnClickListener(onClickListener);
        this.setContentView(contentView);
        this.setWidth((int) (ViewGroup.LayoutParams.WRAP_CONTENT));
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(false);
        this.setOutsideTouchable(true);
    }

    public void showPopupWindow(View view) {
//        this.showAsDropDown(view);
        this.showAtLocation(view, Gravity.NO_GRAVITY,100,100);
    }

}
