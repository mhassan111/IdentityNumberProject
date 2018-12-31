package com.identitynumber.app.httpManager;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class HttpCallManager  {

    private static HttpCallManager mInstance;
    private RequestQueue mRequestQueue;
    private Context mContext;

    private HttpCallManager(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized HttpCallManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new HttpCallManager(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
