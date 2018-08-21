package com.example.hw9.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestManager {
    private static RequestManager mInstance;
    private RequestQueue mRequestQueue;

    private Context mContext;

    public RequestManager(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized RequestManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new RequestManager(context);
        }
        return mInstance;
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req, Object tag) {
        req.setTag(tag);
        getRequestQueue().add(req);
    }

    public void cancelRequests(Object tag) {
        getRequestQueue().cancelAll(tag);
    }
}
