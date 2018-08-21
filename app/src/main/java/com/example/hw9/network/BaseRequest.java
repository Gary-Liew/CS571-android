package com.example.hw9.network;

import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class BaseRequest<T> extends Request<T> {
    private final ResponseCallBack<T> mListener;
    protected HashMap<String, String> mHeaders, mParams;
    protected String mRequestBody;

    public BaseRequest(String url, HashMap<String, String> headers, String requestBody, ResponseCallBack<T> listener) {
        this(url, headers, null, requestBody, listener);
    }

    public BaseRequest(String url, String requestBody, ResponseCallBack<T> listener) {
        this(url, null, null, requestBody, listener);
    }

    public BaseRequest(String url, HashMap<String, String> headers, HashMap<String, String> params, String requestBody, ResponseCallBack<T> listener) {
        this(Method.DEPRECATED_GET_OR_POST, url, headers, params, requestBody, listener);
    }

    public BaseRequest(int method, String url, HashMap<String, String> headers, HashMap<String, String> params, String requestBody, ResponseCallBack<T> listener) {
        super(method, url, listener);
        mRequestBody = requestBody;
        mParams = params;
        mListener = listener;
        mHeaders = headers;
        setRetryPolicy(new DefaultRetryPolicy(1000 * 30, 1, 1f));
        if (mListener != null)
            mListener.onLoading();
    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> tempHashMap = mHeaders != null ? mHeaders : super.getHeaders();
        return tempHashMap;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams != null ? mParams : super.getParams();
    }

    @Override
    public byte[] getPostBody() throws AuthFailureError {
        return getBody();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        try {
            return TextUtils.isEmpty(mRequestBody) ? super.getBody() : mRequestBody.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void deliverResponse(T response) {
        if (mListener != null)
            mListener.onResponse(response);
    }

    private String formatParmas(HashMap<String, String> params) {
        if (params == null)
            return null;
        StringBuilder result = new StringBuilder();
        for (Entry<String, String> entry : params.entrySet()) {
            if (result.length() > 0)
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
        }
        return result.toString();
    }
}
