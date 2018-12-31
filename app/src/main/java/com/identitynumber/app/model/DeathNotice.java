package com.identitynumber.app.model;

import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

public class DeathNotice {

    @NonNull
    @SerializedName("term")
    private String name;

    @NonNull
    @SerializedName("term2")
    private String surName;

    public DeathNotice(@NonNull String name, @NonNull String surName) {
        this.name = name;
        this.surName = surName;
    }
}
