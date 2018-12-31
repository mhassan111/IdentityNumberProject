package com.identitynumber.app.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.identitynumber.app.R;

public class ForgetPasswordActivity extends BaseActivity {

    EditText resetEmailAddress;
    Button resetPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        initializeToolBarViews();
        setUpToolBar(this, "Forget Password");
        resetEmailAddress = findViewById(R.id.emailAddress);
        resetPassword = findViewById(R.id.resetPassword);
        resetPasswordButtonClickListener();
    }

    private void resetPasswordButtonClickListener() {
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
