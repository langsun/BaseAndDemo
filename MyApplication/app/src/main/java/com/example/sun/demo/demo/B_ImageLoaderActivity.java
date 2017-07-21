package com.example.sun.demo.demo;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sun.demo.R;
import com.example.sun.demo.base.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

/**
 * Created by sun on 17/7/14.
 *
 * 参考：http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0327/2650.html
 */

public class B_ImageLoaderActivity extends BaseActivity {
    @Bind(R.id.iv_picasso)
    ImageView mPicasso;
    @Bind(R.id.iv_glide)
    ImageView mGlide;
    @Bind(R.id.iv_glide_gif)
    ImageView mGlideGif;

    @Override
    public void setContent() {
        setContentView(R.layout.b_animation_activity);
    }

    @Override
    public void setModel() {

    }

    @Override
    public void setupView() {
        Picasso.with(this)
                .load("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
                .into(mPicasso);

        Glide.with(this)
                .load("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
                .into(mGlide);

        Glide.with(this)
                .load(R.drawable.flower)
                .into(mGlideGif);
    }


}
