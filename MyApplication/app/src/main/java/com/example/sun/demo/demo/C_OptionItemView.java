package com.example.sun.demo.demo;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sun.demo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sun on 17/7/29.
 */

public class C_OptionItemView extends C_RippleView implements View.OnClickListener, View.OnLongClickListener {
    private Context mContext;

    @Bind(R.id.tv_center)
    TextView mTextDec;

    @Bind(R.id.iv_left)
    ImageView mImageLeft;

    @Bind(R.id.iv_rigth)
    ImageView mImageRight;

    @Bind(R.id.iv_new_flag)
    ImageView mImageNewFlag;

    @Bind(R.id.tv_right_text)
    TextView mTvRightText;

    private OnClickListener mOnClickListener;
    private OnLongClickListener mOnLongClickListener;

    public C_OptionItemView(Context context) {
        super(context);
        this.mContext = context;
        bindView();
    }

    public C_OptionItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        bindView();
    }

    public C_OptionItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        bindView();
    }

    private void bindView() {
        ButterKnife.bind(View.inflate(mContext, R.layout.c_option_view, this));
        this.setOnClickListener(this);
        this.setOnLongClickListener(this);
    }

    public void setOptionItemView(int left_icon_res_id, String dec, int right_icon_res_id, OnClickListener onClickListener) {
        mImageLeft.setImageResource(left_icon_res_id);
        mTextDec.setText(dec);
        mImageRight.setImageResource(right_icon_res_id);
        this.mOnClickListener = onClickListener;
    }

    public void setOptionItemView(String url, String dec, int right_icon_res_id, OnClickListener onClickListener) {
        Glide.with(mContext).load(url).into(mImageLeft);
        mTextDec.setText(dec);
        mImageRight.setImageResource(right_icon_res_id);
        this.mOnClickListener = onClickListener;
    }
    public void setOptionItemViewLongClickListener(String url, String dec, int right_icon_res_id, OnLongClickListener onLongClickListener) {
        Glide.with(mContext).load(url).into(mImageLeft);
        mTextDec.setText(dec);
        mImageRight.setImageResource(right_icon_res_id);
        this.mOnLongClickListener = onLongClickListener;
    }
    public void setOptionItemView2ClickListener(String url, String dec, int right_icon_res_id, OnClickListener onClickListener, OnLongClickListener onLongClickListener) {
        Glide.with(mContext).load(url).into(mImageLeft);
        mTextDec.setText(dec);
        mImageRight.setImageResource(right_icon_res_id);
        this.mOnClickListener = onClickListener;
        this.mOnLongClickListener = onLongClickListener;
    }

    public void setMoreIconHide() {
        mImageRight.setVisibility(GONE);
    }

    public void showNewFlag(boolean show) {
        mImageNewFlag.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void showRightText(String text, int color) {
        mTvRightText.setText(text);
        if (color != 0) {
            mTvRightText.setTextColor(color);
        }
        mTvRightText.setVisibility(TextUtils.isEmpty(text) ? GONE : VISIBLE);
    }

    @Override
    public void onClick(View view) {
        mOnClickListener.onClick(view);
    }

    @Override
    public boolean onLongClick(View v) {
        mOnLongClickListener.onLongClick(v);
        return true;
    }
}
