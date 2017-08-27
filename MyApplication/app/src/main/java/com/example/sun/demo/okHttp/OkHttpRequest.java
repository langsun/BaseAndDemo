package com.example.sun.demo.okHttp;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by sun on 17/8/27.
 */

public class OkHttpRequest {
    protected String url;
    protected Object tag;
    protected static final String TAG = "OKHttpRequest";

    public Request mRequest;

    public static final MediaType JSONTYPE = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType PNGTYPE = MediaType.parse("image/png; charset=utf-8");
    public static final MediaType JPEGTYPE = MediaType.parse("image/jpeg; charset=utf-8");

    public OkHttpRequest(String url, Object tag, JSONObject postParams) {
        this.url = url;
        this.tag = tag == null ? TAG : tag;
        RequestBody requestBody = RequestBody.create(JSONTYPE, postParams == null ? "{}" : postParams.toString());
        mRequest = new Request.Builder()
                .url(url)
                .headers(Headers.of(getHeaders()))
                .tag(tag)
                .post(requestBody)
                .build();
    }

    public OkHttpRequest(String url, Object tag) {
        this.url = url;
        this.tag = tag == null ? TAG : tag;

        mRequest  = new Request.Builder()
                .url(url)
                .headers(Headers.of(getHeaders()))
                .tag(tag)
                .build();
    }

//    public OkHttpRequest(String url, Object tag, MediaType mimeType, Uri uri) {
//        this.url = url;
//        this.tag = tag == null ? TAG : tag;
//
//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MediaType.parse("multipart/form-data"))
//                .addFormDataPart("picture", "user_avatar.jpg", RequestBody.create(mimeType, UriHelper.uriToFile(uri)))
//                .build();
//        mRequest = new Request.Builder()
//                .url(url)
//                .headers(Headers.of(getHeaders()))
//                .tag(tag)
//                .post(requestBody)
//                .build();
//    }

    public static Map<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        //headers.put("X-AVOSCloud-Application-Id", AppEngine.getLCAppId());
        //headers.put("X-AVOSCloud-Application-key", AppEngine.getLCAppKey());
        headers.put("Cache-Control", "no-cache");
//        AVUser user = AppEngine.getInstance().getCurrentUser();
//        if (user != null) {
//            headers.put("X-AVOSCloud-Session-Token", user.getSessionToken());
//        }

        return headers;
    }
}
