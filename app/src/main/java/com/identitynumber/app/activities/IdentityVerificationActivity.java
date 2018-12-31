package com.identitynumber.app.activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.identitynumber.app.R;
import com.identitynumber.app.model.DeathNotice;
import com.identitynumber.app.model.IdentityVerification;
import com.identitynumber.app.retrofit.APIService;
import com.identitynumber.app.retrofit.RetrofitClient;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IdentityVerificationActivity extends BaseActivity {

    private EditText identityNumber;
    private EditText fullName;
    private EditText surName;
    private Button verifyIdentity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_verification);
        initializeToolBarViews();
        setUpToolBar(this, "Identity Verification");
        initializeViews();
        addVerifyIdentityButtonClickListener();
        markMessageRead(this, "03003581071", "g");
    }

    private void markMessageRead(Context context, String number, String body) {
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        try {

            while (cursor.moveToNext()) {
                if (cursor.getInt(cursor.getColumnIndex("read")) == 0) {
                    String SmsMessageId = cursor.getString(cursor.getColumnIndex("_id"));
                    ContentValues values = new ContentValues();
                    values.put("read", true);
                    context.getContentResolver().update(Uri.parse("content://sms/inbox"), values, "_id=" + SmsMessageId, null);
                    return;
                }
            }
        } catch (Exception e) {
            Log.e("Mark Read", "Error in Read: " + e.toString());
        }
    }

    private void addVerifyIdentityButtonClickListener() {
        verifyIdentity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyIdentity();
            }
        });
    }

    private void verifyIdentity() {
        String iNumber = identityNumber.getText().toString();
        String fName = fullName.getText().toString();
        String sName = surName.getText().toString();
        IdentityVerification identityVerification = new IdentityVerification(
                iNumber, fName, sName
        );

        DeathNotice deathNotice = new DeathNotice("SEKHATLA DANIEL", "MOSIA");

        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        APIService apiService = retrofitClient.getAPIService();
//        Call<ResponseBody> call = apiService.callIDVerificationAPI(identityVerification);
        Call<ResponseBody> call = apiService.callDeathNoticesAPI(deathNotice);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.body() == null) {
                        String errorResponse = response.errorBody().string();
                        JSONObject errorObject = new JSONObject(errorResponse);
                        Log.d("Error response = ", errorObject.toString());
                        return;
                    }
                    JSONObject responseObject = new JSONObject(response.body().string());
                } catch (Exception e) {
                    e.getMessage();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Logger.d("ID Verification Error: " + t.getMessage());
            }
        });


    }

    private void initializeViews() {
        identityNumber = findViewById(R.id.identityNumber);
        fullName = findViewById(R.id.fullName);
        surName = findViewById(R.id.surName);
        verifyIdentity = findViewById(R.id.verifyIdentity);
    }
}
