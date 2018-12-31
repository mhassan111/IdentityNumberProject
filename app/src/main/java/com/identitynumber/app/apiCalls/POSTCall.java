package com.identitynumber.app.apiCalls;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.identitynumber.app.httpManager.HttpCallManager;
import com.identitynumber.app.interfaces.ServerResponse;
import com.identitynumber.app.util.Utils;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class POSTCall {

    private ServerResponse responseListener;
    private Context mContext;
    private String mUrl = null;

    public POSTCall(Context mContext, int callType, ServerResponse listener) {
        this.mContext = mContext;
        this.responseListener = listener;
        if (callType == Utils.TYPE_LOGIN_CALL) {
            mUrl = Utils.URL_LOGIN;
        } else if (callType == Utils.TYPE_STATES_CALL) {
            mUrl = Utils.URL_GET_STATES;
        }
    }

    public void callService(final JSONObject object) {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, mUrl, new JSONObject(), new Response
                        .Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        responseListener.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseListener.onFailure(error);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type ", "application/json");
                return params;
            }

            @Override
            public byte[] getBody() {
                try {
                    return object.toString().getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError) {
                if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {
                    volleyError = new VolleyError(new String(volleyError.networkResponse.data));
                }
                return volleyError;
            }
        };
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        HttpCallManager.getInstance(mContext).addToRequestQueue(jsObjRequest);
    }
}
