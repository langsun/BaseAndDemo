package com.example.sun.demo.okHttp;

import org.json.JSONObject;

import okhttp3.Request;

/**
 * Created by sun on 17/8/27.
 */

public abstract class OkCallBack {
    public abstract void onResponse(JSONObject response);

    public void onError(Request call, Exception e) {

    }

    public static OkCallBack CALLBACK_DEFAULT = new OkCallBack() {
        @Override
        public void onResponse(JSONObject response) {

        }

        @Override
        public void onError(Request call, Exception e) {
            super.onError(call, e);
        }
    };

}
