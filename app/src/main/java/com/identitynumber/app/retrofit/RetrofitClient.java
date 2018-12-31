package com.identitynumber.app.retrofit;

import com.identitynumber.app.util.SnappyDBUtil;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://178.128.14.110:3000/";
    private static RetrofitClient mInstance = null;
    private Retrofit retrofit;

    private RetrofitClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("x-access-token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoyMzMzOCwicGFzc3dvcmQiOiJ3YXNzaWtoYXNkc3Fld24xMjMxMiIsImZOYW1lIjoid2Fzc2kxMjMyMyIsImxOYW1lIjoia2hhbjEyMyIsImVtYWlsIjoid2Fzc2lxZXdhZHNraGFuMTIzQGdtYWlsLmNvbSIsInByb2ZpbGVJbWdVcmwiOiJodHRwOi8vMTc4LjEyOC4xNC4xMTAvdXBsb2Fkcy8zZmZlYzI4YjFiZDgwODJiZDJiODJjNmU5YzIwYmQzNC5wbmcifSwiaWF0IjoxNTM0NDk4MjM3LCJleHAiOjE1NjYwMzQyMzd9.zkFMEhvqol0pEBUsEhxQqHhGy10slDH3-gophFs6VMU")
                        .build();
                return chain.proceed(request);
            }
        });
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public APIService getAPIService() {
        return retrofit.create(APIService.class);
    }
}
