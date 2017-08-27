package com.example.sun.demo.demo;

import android.view.View;
import android.widget.TextView;

import com.example.sun.demo.R;
import com.example.sun.demo.base.BaseActivity;
import com.example.sun.demo.okHttp.OkCallBack;
import com.example.sun.demo.okHttp.OkHttpUtils;
import com.example.sun.demo.util.ToastUtils;

import org.json.JSONObject;

import butterknife.Bind;
import okhttp3.Request;

/**
 * Created by sun on 17/8/27.
 */

public class G_OKHttpActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.tv_result)
    TextView mResult;
    @Bind(R.id.tv_btn)
    TextView mBtn;

    @Override
    public void setContent() {
        setContentView(R.layout.g_okhttp_activity);
    }

    @Override
    public void setModel() {

    }

    @Override
    public void setupView() {
        mBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_btn:
                get();
                break;
        }
    }

    private void get(){
        StringBuilder sb = new StringBuilder();
        sb.append("https://testapi.yunlaiwu.com:8088/").append("ip/topics");
        OkHttpUtils.getInstance().getAsyn(sb.toString(), null, new OkCallBack() {
            @Override
            public void onResponse(JSONObject response) {
                if (response!=null)
                response.toString();
                ToastUtils.toast(G_OKHttpActivity.this,"成功");
            }


            @Override
            public void onError(Request call, Exception e) {
                super.onError(call, e);
                ToastUtils.toast(G_OKHttpActivity.this,"失败");
            }
        });
    }
}
