package com.rt.cxl.netprocessor.processor;

import java.util.Map;

/**
 * Created by CXL on 2018/7/16.
 */

public interface NetHttpProcessor {

    void post(String url, Map<String, Object> parames, ProcessorHttpCallback callback);

    void get(String url, Map<String, Object> parames, ProcessorHttpCallback callback);
}
