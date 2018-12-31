package com.identitynumber.app.util;

import android.content.Context;
import android.content.Intent;

import com.identitynumber.app.activities.FamilyMembersActivity;
import com.identitynumber.app.activities.FamilyTreeActivity;
import com.identitynumber.app.activities.ForgetPasswordActivity;
import com.identitynumber.app.activities.IdentityVerificationActivity;
import com.identitynumber.app.activities.MainActivity;
import com.identitynumber.app.activities.SignInActivity;
import com.identitynumber.app.activities.SignUpActivity;

public class ActivityUtil {

    public static void launchSignUpActivity(Context context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public static void launchSignInActivity(Context context) {
        Intent intent = new Intent(context, SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public static void launchForgetPasswordActivity(Context context) {
        Intent intent = new Intent(context, ForgetPasswordActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public static void launchMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public static void launchIdentityVerificationActivity(Context context) {
        Intent intent = new Intent(context, IdentityVerificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public static void launchFamilyMembersActivity(Context context) {
        Intent intent = new Intent(context, FamilyMembersActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
}
