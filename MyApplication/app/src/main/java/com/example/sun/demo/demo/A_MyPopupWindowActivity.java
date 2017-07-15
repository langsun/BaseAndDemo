package com.example.sun.demo.demo;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sun.demo.R;
import com.example.sun.demo.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by sun on 17/7/14.
 */

public class A_MyPopupWindowActivity extends BaseActivity {
    @Bind(R.id.tv_pop_activity)
    TextView mTextView;
    @Bind(R.id.ib_pop_activity)
    ImageButton mImageButton;

    @Override
    public void setContent() {
        setContentView(R.layout.a_my_popup_window_activity);
    }

    @Override
    public void setModel() {

    }

    @Override
    public void setupView() {
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.ib_pop_activity:
                        A_MyPopupWindow a_myPopupWindow = new A_MyPopupWindow(A_MyPopupWindowActivity.this, mTextView, this);
                        a_myPopupWindow.showPopupWindow(mTextView);
                        break;
                    case R.id.ib_pop:
                        Toast.makeText(A_MyPopupWindowActivity.this, "tet", Toast.LENGTH_LONG).show();
                        break;
                }

            }
        });
    }
}
