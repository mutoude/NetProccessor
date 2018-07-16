package com.rt.cxl.netprocessor.processor;

/**
 * Created by CXL on 2018/7/16.
 */

public interface DataICallBack {

    //成功时候回调
    void onSuccess(String result);
    //失败时候回调
    void onFailure(String result);
}
