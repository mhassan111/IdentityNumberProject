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

import java.util.HashMap;
import java.util.Map;

public class GETCall {

    private Context context;
    private int type;
    private String mUrl;
    private ServerResponse mListener;

    public GETCall(Context context, int type, ServerResponse listener) {
        this.context = context;
        this.type = type;
        if (type == Utils.TYPE_GET_COUNTRIES) {
            mUrl = Utils.URL_COUNTRIES_LIST;
        }
        this.mListener = listener;
    }

    public void callService() {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                mUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        mListener.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mListener.onFailure(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                if (type == Utils.TYPE_GET_COUNTRIES)
                    return params;
                params.put("x-access-token", "");
                return params;
            }

            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError) {
                if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {
                    volleyError = new VolleyError(new String(volleyError.networkResponse.data));
                }
                return volleyError;
            }
        };

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        HttpCallManager.getInstance(context).addToRequestQueue(jsonObjReq);
    }

}
