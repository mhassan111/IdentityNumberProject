package com.identitynumber.app.model;

import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

public class IdentityVerification {

    @NonNull
    @SerializedName("term")
    private String identityNumber;

    @NonNull
    @SerializedName("term2")
    private String fullName;

    @NonNull
    @SerializedName("term3")
    private String surName;

    public IdentityVerification(@NonNull String identityNumber, @NonNull String fullName, @NonNull String surName) {
        this.identityNumber = identityNumber;
        this.fullName = fullName;
        this.surName = surName;
    }

    @NonNull
    public String getIdentityNumber() {
        return identityNumber;
    }

    @NonNull
    public String getFullName() {
        return fullName;
    }

    @NonNull
    public String getSurName() {
        return surName;
    }
}
