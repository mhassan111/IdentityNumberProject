package com.identitynumber.app.retrofit;

import com.identitynumber.app.model.DeathNotice;
import com.identitynumber.app.model.IdentityVerification;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {

    @POST("id-verification")
    Call<ResponseBody> callIDVerificationAPI(@Body IdentityVerification identityVerification);

    @POST("deathnotice/dn-name-surname")
    Call<ResponseBody> callDeathNoticesAPI(@Body DeathNotice deathNotice);
}
