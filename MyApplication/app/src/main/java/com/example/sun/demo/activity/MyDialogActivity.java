package com.example.sun.demo.activity;

import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import com.example.sun.demo.R;
import com.example.sun.demo.base.BaseActivity;
import com.example.sun.demo.view.MyDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;

/**
 * Created by sun on 16/7/1.
 */
public class MyDialogActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.tv_event_bus)
    TextView mEventBus;
    @Bind(R.id.tv_dialog1)
    TextView mDialog1;
    @Bind(R.id.tv_dialog2)
    TextView mDialog2;
    @Bind(R.id.tv_dialog3)
    TextView mDialog3;
    @Bind(R.id.tv_dialog4)
    TextView mDialog4;

    @Override
    public void setContent() {
        setContentView(R.layout.my_dialog_activity);
//        EventBus.getDefault().register(this);
    }

    @Override
    public void setModel() {

    }

    @Override
    public void setupView() {
        mCenterTitle.setText("MyDialog");

        mDialog1.setOnClickListener(this);
        mDialog2.setOnClickListener(this);
        mDialog3.setOnClickListener(this);
        mDialog4.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }


//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//
//        Toast.makeText(MyDialogActivity.this,"ceshi",Toast.LENGTH_SHORT).show();
//    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void helloEventBus(String message){
        mEventBus.setText(message);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_dialog1:
                showDialog1();
                break;
            case R.id.tv_dialog2:
                showDialog2();
                break;
            case R.id.tv_dialog3:
                showDialog3();
                break;
            case R.id.tv_dialog4:
                showDialog4();
                break;
        }
    }

    private void showDialog1() {


        MyDialog.Builder builder = new MyDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定退出登录吗");
        builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );


        builder.create().show();

    }
    private void showDialog2() {


        MyDialog.Builder builder = new MyDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("隐藏确定按钮或者取消按钮");
        builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );

//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                }
//        );


        builder.create().show();

    }
    private void showDialog3() {


        MyDialog.Builder builder = new MyDialog.Builder(this);
//        builder.setTitle("提示");
        builder.setMessage("隐藏title");
        builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );


        builder.create().show();

    }
    private void showDialog4() {


        MyDialog.Builder builder = new MyDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("改变title和message的字体大小颜色");
        builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );


        builder.create().show();

    }


}
