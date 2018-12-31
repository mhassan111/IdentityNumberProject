package com.identitynumber.app.interfaces;

import com.squareup.okhttp.Request;


import java.io.IOException;

public interface Response {
    public void onSuccess(com.squareup.okhttp.Response response);
    public void onFailed(Request request, IOException e);
}
