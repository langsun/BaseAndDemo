package com.example.sun.demo.demo;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sun.demo.R;
import com.example.sun.demo.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by sun on 17/7/29.
 */

public class C_RippleActivity extends BaseActivity {
    @Bind(R.id.tv_text)
    TextView mText;
    @Bind(R.id.c_ripple_view)
    C_RippleView mCRippleView;

    @Bind(R.id.c_option_item_view)
    C_OptionItemView mCOptionItemView;
    @Bind(R.id.c_option_item_view2)
    C_OptionItemView mCOptionItemViewSetOnClickListener;
    @Bind(R.id.c_option_item_view3)
    C_OptionItemView mCOptionItemViewSetOnLongClickListener;
    @Bind(R.id.c_option_item_view4)
    C_OptionItemView mCOptionItemViewSet2ClickListener;
    @Bind(R.id.c_option_item_view5)
    C_OptionItemView2 mView2;

    @Bind(R.id.tv)
    TextView mTextView;

    @Override
    public void setContent() {
        setContentView(R.layout.c_ripple_activity);
    }

    @Override
    public void setModel() {

    }

    @Override
    public void setupView() {
        mCRippleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(C_RippleActivity.this, "CRippleView", Toast.LENGTH_LONG).show();
            }
        });

        mCOptionItemView.setOptionItemView(R.drawable.ic_launcher, "测试", R.drawable.more, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(C_RippleActivity.this, "OptionItemView", Toast.LENGTH_LONG).show();
            }
        });

        mCOptionItemViewSetOnClickListener.setOptionItemView("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg", "测试", R.drawable.more, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(C_RippleActivity.this, "OptionItemView2使用Glide加载leftIcon", Toast.LENGTH_LONG).show();
            }
        });


        mCOptionItemViewSetOnLongClickListener.setOptionItemViewLongClickListener("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg", "onLongClickListener", R.drawable.more, new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(C_RippleActivity.this, "setOptionItemViewLongClickListener", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        mCOptionItemViewSet2ClickListener.setOptionItemView2ClickListener("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg", "2ClickListener", R.drawable.more, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(C_RippleActivity.this, "OptionItemView2ClickListener的OnClickListener", Toast.LENGTH_LONG).show();
                    }
                },
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Toast.makeText(C_RippleActivity.this, "OptionItemView2ClickListener的OnLongClickListener", Toast.LENGTH_LONG).show();
                        return true;
                    }
                });

        mView2.addView(useBuilderModle());
        mTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(C_RippleActivity.this, "textview", Toast.LENGTH_LONG).show();
                return true;
            }
        });

    }

    private C_OptionItemView2 useBuilderModle() {
        C_OptionItemView2.Builder builder = new C_OptionItemView2.Builder(C_RippleActivity.this);
       return builder.setLeftImage("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
                .setRightImage(R.drawable.more)
                .setDescription("使用Builder模式")
                .setOnClickListeners(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(C_RippleActivity.this, "使用Builder模式成功", Toast.LENGTH_LONG).show();
                    }
                }).create();
    }
}
