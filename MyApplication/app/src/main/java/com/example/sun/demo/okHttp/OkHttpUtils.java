package com.example.sun.demo.okHttp;

import android.os.Handler;
import android.os.Looper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sun on 17/8/27.
 */

public class OkHttpUtils {
    private static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;

    public static OkHttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtils();
                }
            }
        }
        return mInstance;
    }

    private OkHttpUtils() {
        mHandler = new Handler(Looper.getMainLooper());
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new StethoInterceptor())
                .build();
    }

    /**
     * 异步的get请求
     *
     * @param url
     * @param callBack
     */

    public void getAsyn(String url, Object tag, final OkCallBack callBack) {
        OkHttpRequest request = new OkHttpRequest(url, tag);
        deliveryResultAsyn(callBack == null ? OkCallBack.CALLBACK_DEFAULT : callBack, request.mRequest);

    }

    /**
     * 异步的post请求
     *
     * @param url
     * @param callBack
     * @param params
     */
    public void postAsyn(String url, Object tag, OkCallBack callBack, JSONObject params) {
        OkHttpRequest request = new OkHttpRequest(url, tag, params);
        deliveryResultAsyn(callBack == null ? OkCallBack.CALLBACK_DEFAULT : callBack, request.mRequest);
    }

    /**
     * 同步的get请求
     *
     * @param url
     * @param callBack
     */
    public void getSync(String url, Object tag, OkCallBack callBack) {
        OkHttpRequest request = new OkHttpRequest(url, tag);
        deliveryResultSync(callBack == null ? OkCallBack.CALLBACK_DEFAULT : callBack, request.mRequest);
    }

    /**
     * 同步的post请求
     *
     * @param url
     * @param callBack
     * @param params
     */
    public void postSync(String url, Object tag, OkCallBack callBack, JSONObject params) {
        OkHttpRequest request = new OkHttpRequest(url, tag, params);
        deliveryResultSync(callBack == null ? OkCallBack.CALLBACK_DEFAULT : callBack, request.mRequest);
    }

    /**
     * 同步请求
     * @param callback
     * @param request
     */
    private void deliveryResultSync(final OkCallBack callback, final Request request) {
        try {
            Response response = mOkHttpClient.newCall(request).execute();
            String str = response.body().string();
            if (callback != null) {
                callback.onResponse(new JSONObject(str));
            }
        } catch (IOException e) {
            if (callback != null)
                callback.onError(request, e);
        } catch (Exception e) {
            if (callback != null) {
                callback.onError(request, e);
            }
        }
    }

    /**
     * 异步请求
     * @param callBack
     * @param request
     */

    private void deliveryResultAsyn(final OkCallBack callBack, final Request request) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedStringCallback(request, e, callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    sendsuccessResultCallback(new JSONObject(response.body().string()), callBack);
                } catch (JSONException e) {
                    sendFailedStringCallback(request, e, callBack);
                    e.printStackTrace();
                }
            }
        });
    }

    private void sendFailedStringCallback(final Request request, final Exception e, final OkCallBack callBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onError(request, e);
            }
        });
    }

    private void sendsuccessResultCallback(final JSONObject result, final OkCallBack callBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onResponse(result);
            }
        });
    }
}
