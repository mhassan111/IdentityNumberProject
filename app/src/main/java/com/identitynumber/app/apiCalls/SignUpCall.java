package com.identitynumber.app.apiCalls;

import android.content.Context;

import com.identitynumber.app.util.Utils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SignUpCall {

    private Context context;
    private com.identitynumber.app.interfaces.Response responseListener;

    public SignUpCall(Context context, com.identitynumber.app.interfaces.Response serverResponse) {
        this.context = context;
        this.responseListener = serverResponse;
    }

    public void callService(JSONObject jsonObject) {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(60, TimeUnit.SECONDS);
        client.setReadTimeout(60, TimeUnit.SECONDS);
        client.setWriteTimeout(60, TimeUnit.SECONDS);

        try {
            String filePath = jsonObject.getString("file");
            MultipartBuilder requestBodyBuilder = new MultipartBuilder()
                    .type(MultipartBuilder.FORM)
                    .addFormDataPart(
                            "file",
                            filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length()),
                            RequestBody.create(MediaType.parse("octet-stream"), new File(filePath)))
                    .addFormDataPart("firstName", jsonObject.getString("firstName"))
                    .addFormDataPart("lastName", jsonObject.getString("lastName"))
                    .addFormDataPart("emailaddress", jsonObject.getString("emailaddress"))
                    .addFormDataPart("password", jsonObject.getString("password"))
                    .addFormDataPart("dateOfBirth", jsonObject.getString("dateOfBirth"))
                    .addFormDataPart("mobile", jsonObject.getString("mobile"))
                    .addFormDataPart("country", jsonObject.getString("country"))
                    .addFormDataPart("isMale", jsonObject.getString("isMale"))
                    .addFormDataPart("state", jsonObject.getString("state"))
                    .addFormDataPart("deviceType", jsonObject.getString("deviceType"));

            RequestBody requestBody = requestBodyBuilder.build();
            Request request = new Request.Builder()
                    .url(Utils.URL_SIGN_UP)
                    .addHeader("Content-Type", "multipart/form-data")
                    .post(requestBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    responseListener.onFailed(request, e);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    responseListener.onSuccess(response);
                }
            });
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
