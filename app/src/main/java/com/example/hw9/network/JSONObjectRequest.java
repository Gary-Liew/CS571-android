package com.example.hw9.network;

import android.text.TextUtils;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class JSONObjectRequest extends BaseRequest<JSONObject> {

    public JSONObjectRequest(String url, String requestBody,
                             ResponseCallBack<JSONObject> listener) {
        super(url, requestBody, listener);
    }

    public JSONObjectRequest(int method, String url, ResponseCallBack<JSONObject> listener) {
        super(method, url, null, null, null, listener);
    }

    public JSONObjectRequest(String url, HashMap<String, String> params,
                             ResponseCallBack<JSONObject> listener) {
        super(Request.Method.POST, url, null, params, null, listener);
    }

    public JSONObjectRequest(int method, String url, HashMap<String, String> headers,
                             ResponseCallBack<JSONObject> listener) {
        super(method, url, headers, null, null, listener);
    }

    public JSONObjectRequest(int method, String url, String requestBody, ResponseCallBack<JSONObject> listener) {
        super(method, url, null, null, requestBody, listener);
    }

    public JSONObjectRequest(int method, String url, HashMap<String, String> headers, String requestBody, ResponseCallBack<JSONObject> listener) {
        super(method, url, headers, null, requestBody, listener);
    }


    @Override
    public String getBodyContentType() {
        if (mParams != null && mParams.size() > 0) {
            return "application/x-www-form-urlencoded; charset=utf-8";
        } else if (mRequestBody != null && !"".equals(mRequestBody)) {
            return "application/json; charset=utf-8";
        }
        return super.getBodyContentType();
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        super.parseNetworkResponse(response);
        try {

            String encoding = HttpHeaderParser.parseCharset(response.headers);
            if ("ISO-8859-1".equals(encoding)) {
                encoding = "UTF-8";
            }

            String jsonString =
                    new String(response.data, encoding);
            if (TextUtils.isEmpty(jsonString)) {
                return Response.success(null, HttpHeaderParser.parseCacheHeaders(response));
            }
            return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }
}
