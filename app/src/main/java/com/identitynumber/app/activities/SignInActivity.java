package com.identitynumber.app.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.identitynumber.app.MyApplication;
import com.identitynumber.app.R;
import com.identitynumber.app.apiCalls.POSTCall;
import com.identitynumber.app.apiResponse.ApiResponse;
import com.identitynumber.app.dialogs.ErrorDialog;
import com.identitynumber.app.dialogs.LoaderDialog;
import com.identitynumber.app.interfaces.CustomListener;
import com.identitynumber.app.interfaces.ServerResponse;
import com.identitynumber.app.presenter.ILoginView;
import com.identitynumber.app.responseParsing.SignUpResponse;
import com.identitynumber.app.responseParsing.UserInfo;
import com.identitynumber.app.util.ActivityUtil;
import com.identitynumber.app.util.Utils;
import com.identitynumber.app.util.SnappyDBUtil;
import com.identitynumber.app.viewModel.LoginViewModel;
import com.identitynumber.app.viewModel.ViewModelFactory;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION = 1000;
    static String[] permissionsList = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    @Inject
    ViewModelFactory viewModelFactory;
    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.inputEmailLayout)
    TextInputLayout emailLayout;
    @BindView(R.id.inputPasswordLayout)
    TextInputLayout passwordLayout;
    @BindView(R.id.signUpLink)
    TextView signUpLink;

    private LoaderDialog mLoaderDialog = null;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ButterKnife.bind(this);

        addSignUpLinkClickListener();
        checkRunTimePermissions();
        ((MyApplication) getApplication()).getAppComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
        viewModel.loginResponse().observe(this, this::consumeResponse);
    }

    @OnClick(R.id.forgetPasswordText)
    void onForgetPasswordClicked() {
        ActivityUtil.launchForgetPasswordActivity(SignInActivity.this);
    }

    @OnClick(R.id.signIn)
    void onLoginClicked() {
        String userEmail = userName.getText().toString().trim();
        String userPassword = password.getText().toString().trim();
        boolean isEmailValidated = validateEmailAddress(userEmail);
        boolean isPasswordValidated = validate(passwordLayout, userPassword);
        if (!isEmailValidated || !isPasswordValidated)
            return;
        viewModel.hitLoginApi(userEmail, userPassword);
    }

    private void consumeResponse(ApiResponse apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                showLoader();
                break;
            case SUCCESS:
                onLoginSuccess(apiResponse);
                break;
            case ERROR:
                onLoginError();
                break;
            default:
                break;
        }
    }

    private void onLoginSuccess(ApiResponse apiResponse) {
        hideLoader();
        Logger.d("User LoggedIn Successfully :");
        try {
            JsonObject jsonObject = new JsonParser().parse(apiResponse.data.toString()).getAsJsonObject();
            JSONObject response = new JSONObject(jsonObject.toString());
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            SignUpResponse signUpResponse = gson.fromJson(response.toString(), SignUpResponse.class);
            if (signUpResponse.isSuccess()) {
                UserInfo userInfo = signUpResponse.getUserInfo();
                SnappyDBUtil.addUserInfo(this, userInfo);
                SnappyDBUtil.saveUserToken(this, signUpResponse.getToken());
                ActivityUtil.launchMainActivity(SignInActivity.this);
            }
        } catch (Exception e) {
            Logger.d("OnLoginSuccess Error: " + e.getMessage());
        }
    }

    private void onLoginError() {
        hideLoader();
        showLoginErrorDialog();
    }

    private void addSignUpLinkClickListener() {
        String text = getResources().getString(R.string.sign_up_text);
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                ActivityUtil.launchSignUpActivity(SignInActivity.this);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };
        ss.setSpan(clickableSpan, text.lastIndexOf("Sign"), text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signUpLink.setText(ss);
        signUpLink.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void checkRunTimePermissions() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return;
        requestRunTimePermissions();
    }

    private void showLoader() {
        if (mLoaderDialog == null) {
            mLoaderDialog = new LoaderDialog(SignInActivity.this);
            mLoaderDialog.setCanceledOnTouchOutside(false);
            mLoaderDialog.setCancelable(false);
        }
        mLoaderDialog.show();
    }

    private void hideLoader() {
        if (mLoaderDialog != null && mLoaderDialog.isShowing())
            mLoaderDialog.dismiss();
    }

    private void showLoginErrorDialog() {
        ErrorDialog errorDialog = new ErrorDialog(SignInActivity.this, new CustomListener() {
            @Override
            public void onRetry() {

            }
        }, "Error", "Invalid Credentials");
        errorDialog.setCancelable(true);
        errorDialog.setCanceledOnTouchOutside(true);
        errorDialog.show();
    }

    private boolean validate(TextInputLayout inputLayout, String text) {
        if (!TextUtils.isEmpty(text)) {
            inputLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.edittext_background));
            return true;
        } else {
            inputLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.error_background));
        }
        return false;
    }

    private boolean validateEmailAddress(String emailAddress) {
        if (Utils.isEmailAddressValid(emailAddress)) {
            emailLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.edittext_background));
            return true;
        } else {
            emailLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.error_background));
            return false;
        }
    }

    private void requestRunTimePermissions() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (checkSelfPermission(permissionsList[0]) != PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(permissionsList[1]) != PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(permissionsList[2]) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(SignInActivity.this, permissionsList[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(SignInActivity.this, permissionsList[1])
                        || ActivityCompat.shouldShowRequestPermissionRationale(SignInActivity.this, permissionsList[2])) {
                    displayPermissionRequestDialog();
                } else {
                    ActivityCompat.requestPermissions(SignInActivity.this,
                            permissionsList
                            , REQUEST_PERMISSION);
                }
            }
        }
    }

    @Override
    public int checkSelfPermission(String permission) {
        return ActivityCompat.checkSelfPermission(SignInActivity.this, permission);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION:
                boolean allGranted = false;
                for (int result : grantResults) {
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        allGranted = true;
                    } else {
                        allGranted = false;
                        break;
                    }
                }

                if (allGranted) {

                } else if (ActivityCompat.shouldShowRequestPermissionRationale(SignInActivity.this, permissionsList[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(SignInActivity.this, permissionsList[1])
                        || ActivityCompat.shouldShowRequestPermissionRationale(SignInActivity.this, permissionsList[2])) {
                    displayPermissionRequestDialog();
                }
        }
    }

    private void displayPermissionRequestDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);
        builder.setTitle("Need Permissions");
        builder.setMessage("Identity Number need Multiple permissions. Please Grant.");
        builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                ActivityCompat.requestPermissions(SignInActivity.this, permissionsList, REQUEST_PERMISSION);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}
