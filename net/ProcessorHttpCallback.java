package com.rt.cxl.netprocessor.processor;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by CXL on 2018/7/16.
 */

public abstract class ProcessorHttpCallback<T> implements DataICallBack {

    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        Type type = getType(this);
        T obj = gson.fromJson(result, type);
        onSuccess(obj);
    }

    public abstract String onSuccess(T t);
    public abstract String onFail(String s);

    @Override
    public void onFailure(String result) {
        onFail(result);
    }

    private static Type getType(Object obj) {
        Type types = obj.getClass().getGenericSuperclass();
        Type[] type_arr = ((ParameterizedType) types).getActualTypeArguments();
        return type_arr[0];
    }
}
