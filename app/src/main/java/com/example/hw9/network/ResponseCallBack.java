package com.example.hw9.network;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.orhanobut.logger.Logger;

public abstract class ResponseCallBack<T> implements Response.Listener<T>, Response.ErrorListener {

    @Override
    public abstract void onResponse(T response);

    @Override
    public void onErrorResponse(VolleyError error) {
        Logger.e(error.toString());
    }

    public void onLoading() {

    }
}

