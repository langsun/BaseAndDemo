package com.example.sun.demo.demo;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sun.demo.R;

/**
 * Created by sun on 17/8/10.
 */

public class C_OptionItemView2 extends C_RippleView {
    public C_OptionItemView2(Context context) {
        this(context,null);
    }

    public C_OptionItemView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        LinearLayout layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.c_option_view, this,false);
        addView(layout);
    }

    public static class Builder {
        private Context mContext;

        private TextView mDescription;
        private String mDescriptionString;

        private ImageView mLeftImage;
        private String mLeftImageUrl;
        private boolean mIsSetLeftImageRes = false;
        private int mLeftImageRes;

        private ImageView mRightImage;
        private String mRightImageUrl;
        private boolean mIsSetRightImageRes = false;
        private int mRightImageRes;

        private OnClickListener mOnClickListener;

        public Builder(Context mContext) {
            this.mContext = mContext;
        }

        /**
         * 设置左边图片
         *
         * @return
         */
        public Builder setLeftImage(int res) {
            this.mIsSetLeftImageRes = true;
            this.mLeftImageRes = res;
            return this;
        }

        public Builder setLeftImage(String imageUrl) {
            this.mLeftImageUrl = imageUrl;
            return this;
        }

        /**
         * 设置右边图片
         *
         * @return
         */
        public Builder setRightImage(int res) {
            this.mIsSetRightImageRes = true;
            this.mRightImageRes = res;
            return this;
        }

        public Builder setRightImage(String imageUrl) {
            this.mRightImageUrl = imageUrl;
            return this;
        }

        /**
         * 设置Item的描述信息
         *
         * @param res
         * @return
         */
        public Builder setDescription(int res) {
            this.mDescriptionString = (String) mContext.getText(res);
            return this;
        }

        public Builder setDescription(String description) {
            this.mDescriptionString = description;
            return this;
        }

        /**
         * 设置点击事件
         *
         * @param onClickListeners
         * @return
         */
        public Builder setOnClickListeners(OnClickListener onClickListeners) {
            this.mOnClickListener = onClickListeners;
            return this;
        }


        public C_OptionItemView2 create() {
            C_OptionItemView2 c_optionItemView2 = new C_OptionItemView2(mContext);
            LinearLayout layout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.c_option_view, c_optionItemView2,false);
            mLeftImage = (ImageView) layout.findViewById(R.id.iv_left);
            mRightImage = (ImageView) layout.findViewById(R.id.iv_rigth);
            mDescription = (TextView) layout.findViewById(R.id.tv_center);
            if (!TextUtils.isEmpty(mLeftImageUrl)) {
                Glide.with(mContext).load(mLeftImageUrl).into(mLeftImage);
            } else if (mIsSetLeftImageRes) {
                mLeftImage.setImageResource(mLeftImageRes);
            }

            if (!TextUtils.isEmpty(mDescriptionString)) {
                mDescription.setText(mDescriptionString);
            }

            if (!TextUtils.isEmpty(mRightImageUrl)) {
                Glide.with(mContext).load(mRightImageUrl).into(mRightImage);
            } else if (mIsSetRightImageRes) {
                mRightImage.setImageResource(mRightImageRes);
            }

            if (mOnClickListener != null) {
                this.setOnClickListeners(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnClickListener.onClick(v);
                    }
                });
            }

            c_optionItemView2.addView(layout ,new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            return c_optionItemView2;
        }


    }

}
