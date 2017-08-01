package com.example.sun.demo.demo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sun.demo.R;
import com.example.sun.demo.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;

/**
 * Created by sun on 17/7/31.
 * <p>
 * 开启子线程  Timer TimerTask
 *           AsyncTask
 *           Thread
 *           Runnable
 */

public class D_TimerActivity extends BaseActivity {
    @Bind(R.id.tv_timer_test)
    TextView mTextView;
    @Bind(R.id.tv_timer_test1)
    TextView mTextView1;
    @Bind(R.id.tv_timer_test2)
    TextView mThread;
    @Bind(R.id.tv_timer_test3)
    TextView mRunnable;

    @Override
    public void setContent() {
        setContentView(R.layout.d_timer_activity);
    }

    @Override
    public void setModel() {

    }

    @Override
    public void setupView() {
        testAsyncTask();
        mTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(D_TimerActivity.this, "三秒后有惊喜", Toast.LENGTH_LONG).show();
                testTimer();
            }
        });
        mThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testThread();
            }
        });
        mRunnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRunnable();
            }
        });
    }

    private void testTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.e("..........", "Timer");
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("test", "你是sb");
                message.setData(bundle);
                handler.sendMessage(message);
            }
        }, 3 * 1000);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg != null) {

                mTextView.setText(msg.getData().getString("test"));
            }
        }
    };

    /**
     * AsyncTask
     */

    private void testAsyncTask() {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                for (int i = 0; i < 10; i++) {
                    netWork();
                    Log.e("。。。。。。。。。", "" + i);
                }
                return "测试";
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.e(".........", "onPreExecute");
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.e(".........", "onPostExecute" + s);
            }
        }.execute();
    }

    /**
     * http://mars914.iteye.com/blog/1508429
     */

    public class thread extends Thread {
        private int ticket = 10;
        private String name;

        public thread(String name) {
            this.name = name;
        }

        public void run() {
            for (int i = 0; i < 500; i++) {
                if (this.ticket > 0) {
                    Log.e("...........", "" + this.name + "卖票---->" + (this.ticket--));
                }
            }
        }
    }

    class runnable implements Runnable {

        private int ticket = 10;
        private String name;

        public void run() {
            for (int i = 0; i < 500; i++) {
                if (this.ticket > 0) {
                    Log.e(".........", "" + Thread.currentThread().getName() + "卖票---->" + (this.ticket--));
                }
            }
        }
    }

    private void testThread() {
        thread t1 = new thread("一号窗口");
        thread t2 = new thread("二号窗口");
        thread t3 = new thread("三号窗口");
        t1.start();
        t2.start();
        t3.start();
    }

    private void testRunnable() {
        runnable r = new runnable();
        Thread thread1 = new Thread(r,"一号窗口");
        Thread thread2 = new Thread(r,"二号窗口");
        Thread thread3 = new Thread(r,"三号窗口");
        thread1.start();
        thread2.start();
        thread3.start();
    }

    //模拟网络请求
    private void netWork() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
