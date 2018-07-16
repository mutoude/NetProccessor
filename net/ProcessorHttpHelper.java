package com.rt.cxl.netprocessor.processor;

import java.util.Map;

/**
 * Created by CXL on 2018/7/16.
 */

public class ProcessorHttpHelper implements NetHttpProcessor {
    //这儿低耦合
    private static NetHttpProcessor mFrameHttpProcessor;
    private static ProcessorHttpHelper _instance;

    public ProcessorHttpHelper() {
    }

    public void init(NetHttpProcessor processor) {
        mFrameHttpProcessor = processor;
    }

    public static ProcessorHttpHelper getInstance() {
        synchronized (ProcessorHttpHelper.class) {
            if (_instance == null) {
                _instance = new ProcessorHttpHelper();
            }
            return _instance;
        }
    }

    @Override
    public void get(String url, Map<String, Object> parames, ProcessorHttpCallback callback) {
        //这里我们进行字符串的拼接
        String url_result = appendParamers(url, parames);
        mFrameHttpProcessor.get(url_result, parames, callback);
    }

    private static String appendParamers(String url, Map<String, Object> paramers) {
        if (paramers == null && paramers.isEmpty()) {
            return url;
        }
        StringBuilder urlBuider = new StringBuilder(url);

        if (urlBuider.indexOf("?") <= 0) {
            urlBuider.append("?");
        } else {
            if (!urlBuider.toString().endsWith("?")) {
                urlBuider.append("&");
            }
        }
        for (Map.Entry<String, Object> entry : paramers.entrySet()) {
            urlBuider.append(entry.getKey()).append("=").append(entry.getValue().toString());
        }
        return urlBuider.toString();
    }

    @Override
    public void post(String url, Map<String, Object> parames, ProcessorHttpCallback callback) {
        //这里我们进行字符串的拼接
        mFrameHttpProcessor.post(url, parames, callback);
    }

}
