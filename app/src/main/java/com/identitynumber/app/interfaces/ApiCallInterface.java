package com.identitynumber.app.interfaces;

import com.google.gson.JsonElement;
import com.identitynumber.app.util.Utils;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiCallInterface {

    @FormUrlEncoded
    @POST(Utils.URL_LOGIN)
    Observable<JsonElement> login(@Field("username") String mobileNumber, @Field("password") String password);
}
