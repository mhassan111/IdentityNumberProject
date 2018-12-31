package com.identitynumber.app.interfaces;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface ServerResponse {
    public void onSuccess(JSONObject response);
    public void onFailure(VolleyError error);
}
