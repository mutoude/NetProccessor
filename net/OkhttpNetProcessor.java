package com.rt.cxl.netprocessor.processor;

import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by CXL on 2018/7/16.
 */

public class OkhttpNetProcessor implements NetHttpProcessor {
    private OkHttpClient mOkhttClient;
    private Handler mHandler;

    public OkhttpNetProcessor() {
        mOkhttClient = new OkHttpClient();
        mHandler = new Handler();
    }

    private RequestBody appendBody(Map<String, Object> paramers) {
        FormBody.Builder body = new FormBody.Builder();
        if (paramers == null || paramers.isEmpty()) {
            return body.build();
        }
        for (Map.Entry<String, Object> entry : paramers.entrySet()) {
            body.add(entry.getKey(), entry.getValue().toString());
        }
        return body.build();
    }

    @Override
    public void get(String url, Map<String, Object> parames, final ProcessorHttpCallback callback) {
        final Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        mOkhttClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailure(e.toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String result = response.body().string();
                    Log.e("resutlxx", result.toString());
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(result);
                        }
                    });
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFailure("访问失败！");
                        }
                    });
                }
            }
        });


    }

    @Override
    public void post(String url, Map<String, Object> parames, final ProcessorHttpCallback callback) {
        RequestBody requestBody = appendBody(parames);
        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .header("User-Agent", "a")
                .build();
        mOkhttClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailure(e.toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String result = response.body().string();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(result);
                        }
                    });
                } else {

                }

            }
        });
    }
}
