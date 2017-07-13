package com.example.sun.demo.home;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sun.demo.R;
import com.example.sun.demo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by sun on 16/7/26.
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    @Bind(R.id.tv_title)
    TextView mTitle;
    @Bind(R.id.rl_tab1)
    RelativeLayout mTab1;
    @Bind(R.id.rl_tab2)
    RelativeLayout mTab2;
    @Bind(R.id.rl_tab3)
    RelativeLayout mTab3;
    @Bind(R.id.rl_tab4)
    RelativeLayout mTab4;
    @Bind(R.id.home_vp)
    ViewPager mViewPager;
    @Bind(R.id.iv_image1)
    ImageView mIvHome;
    @Bind(R.id.iv_image2)
    ImageView mIvMessage;
    @Bind(R.id.iv_image3)
    ImageView mIvFind;
    @Bind(R.id.iv_image4)
    ImageView mIvMine;
    @Bind(R.id.tv_text1)
    TextView mTvHome;
    @Bind(R.id.tv_text2)
    TextView mTvMessage;
    @Bind(R.id.tv_text3)
    TextView mTvFind;
    @Bind(R.id.tv_text4)
    TextView mTvMine;

    private List<Fragment> mFragmentList;
    private HomeVpAdapter mHomeVpAdatper;


    private static final int HOME = 0, MESSAGE = 1, FIND = 2, MINE = 3;

    private static final String unSelectedTextColor = "#82858b";
    private static final String selectedTextColor = "#00acff";

    private Animation mViewShakeAnimation;
    private Animation mViewScaleAnimation;
    private AnimationSet mViewSelectedAnimation;
    @Override
    public void setContent() {
        setContentView(R.layout.home_activity);
    }

    @Override
    public void setModel() {

    }

    @Override
    public void setupView() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new MessageFragment());
        mFragmentList.add(new FindFragment());
        mFragmentList.add(new MineFragment());

        mHomeVpAdatper = new HomeVpAdapter(getSupportFragmentManager(), mFragmentList);

        mViewPager.setAdapter(mHomeVpAdatper);
        mViewPager.setOffscreenPageLimit(mFragmentList.size());
        mViewPager.setOnPageChangeListener(this);

        mViewShakeAnimation = AnimationUtils.loadAnimation(this, R.anim.view_shake);
        mViewScaleAnimation = AnimationUtils.loadAnimation(this, R.anim.view_scale);
        mViewSelectedAnimation = new AnimationSet(false);
        mViewSelectedAnimation.addAnimation(mViewShakeAnimation);
        mViewSelectedAnimation.addAnimation(mViewScaleAnimation);

        mTab1.setOnClickListener(this);
        mTab2.setOnClickListener(this);
        mTab3.setOnClickListener(this);
        mTab4.setOnClickListener(this);
        setTabSelected(HOME);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_tab1:
                setTabSelected(HOME);
                mViewPager.setCurrentItem(HOME);
                break;
            case R.id.rl_tab2:
                setTabSelected(MESSAGE);
                mViewPager.setCurrentItem(MESSAGE);
                break;
            case R.id.rl_tab3:
                setTabSelected(FIND);
                mViewPager.setCurrentItem(FIND);
                break;
            case R.id.rl_tab4:
                setTabSelected(MINE);
                mViewPager.setCurrentItem(MINE);
                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setTabSelected(position);
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setTabSelected(int position) {
        clearSelectedState();
        switch (position) {
            case HOME:
                mIvHome.setImageResource(R.drawable.ip_btn_selected);
                mTvHome.setTextColor(Color.parseColor(selectedTextColor));
                mTitle.setText(mTvHome.getText());
                setViewAnimation(mIvHome);
                break;
            case MESSAGE:
                mIvMessage.setImageResource(R.drawable.message_btn_selected);
                mTvMessage.setTextColor(Color.parseColor(selectedTextColor));
                mTitle.setText(mTvMessage.getText());
                setViewAnimation(mIvMessage);
                break;
            case FIND:
                mIvFind.setImageResource(R.drawable.discovery_btn_selected);
                mTvFind.setTextColor(Color.parseColor(selectedTextColor));
                mTitle.setText(mTvFind.getText());
                setViewAnimation(mIvFind);
                break;
            case MINE:
                mIvMine.setImageResource(R.drawable.mine_btn_selected);
                mTvMine.setTextColor(Color.parseColor(selectedTextColor));
                mTitle.setText(mTvMine.getText());
                setViewAnimation(mIvMine);
                break;
        }
    }

    private void setViewAnimation(View view) {
        view.setAnimation(mViewSelectedAnimation);
        view.startAnimation(mViewSelectedAnimation);
    }

    private void clearSelectedState() {
        mIvHome.setImageResource(R.drawable.ip_btn_unselected);
        mTvHome.setTextColor(Color.parseColor(unSelectedTextColor));
        mIvMessage.setImageResource(R.drawable.message_btn_unselected);
        mTvMessage.setTextColor(Color.parseColor(unSelectedTextColor));
        mIvFind.setImageResource(R.drawable.discovery_btn_unselected);
        mTvFind.setTextColor(Color.parseColor(unSelectedTextColor));
        mIvMine.setImageResource(R.drawable.mine_btn_unselected);
        mTvMine.setTextColor(Color.parseColor(unSelectedTextColor));
    }

    //点两次退出
    private long exitTime = 0;
    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() - exitTime > 2000){
            Toast.makeText(this,"再按一次退出", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
            return;
        }
        this.finish();
    }
}
