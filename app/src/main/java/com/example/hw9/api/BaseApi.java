package com.example.hw9.api;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.example.hw9.network.JSONObjectRequest;
import com.example.hw9.network.ResponseCallBack;
import com.example.hw9.util.RequestManager;

import org.json.JSONObject;

import java.util.HashMap;

public class BaseApi {

    private final static String BASE_URL = "https://congress.api.sunlightfoundation.com/";
    private final static String GET_LEGISLATORS = BASE_URL + "legislators?per_page=all";
    private final static String GET_BILLS = BASE_URL + "bills?per_page=50";
    private final static String GET_COMMITTEES = BASE_URL + "committees?per_page=all";

    public static void getLegislators(Context context, String filter, ResponseCallBack<JSONObject> callBack) {
        HashMap<String, String> hashMap = new HashMap<>();
        String url = GET_LEGISLATORS;
        if (!TextUtils.isEmpty(filter)) {
            url = GET_LEGISLATORS + "&chamber=" + filter;
        }
        RequestManager.getInstance(context).cancelRequests(url);
        RequestManager.getInstance(context).addToRequestQueue(new JSONObjectRequest(Request.Method.GET, url, hashMap, callBack), url);
    }

    public static void getBills(Context context, String filter, ResponseCallBack<JSONObject> callBack) {
        HashMap<String, String> hashMap = new HashMap<>();
        String url = GET_BILLS;
        if (!TextUtils.isEmpty(filter)) {
            url = GET_BILLS + "&history.active=" + filter;
        }
        RequestManager.getInstance(context).cancelRequests(url);
        RequestManager.getInstance(context).addToRequestQueue(new JSONObjectRequest(Request.Method.GET, url, hashMap, callBack), url);
    }

    public static void getCommittees(Context context, String filter, ResponseCallBack<JSONObject> callBack) {
        HashMap<String, String> hashMap = new HashMap<>();
        String url = GET_COMMITTEES;
        if (!TextUtils.isEmpty(filter)) {
            url = GET_COMMITTEES + "&chamber=" + filter;
        }
        RequestManager.getInstance(context).cancelRequests(url);
        RequestManager.getInstance(context).addToRequestQueue(new JSONObjectRequest(Request.Method.GET, url, hashMap, callBack), url);
    }
}
