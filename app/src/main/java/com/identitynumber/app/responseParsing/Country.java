package com.identitynumber.app.responseParsing;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Country {

    @SerializedName("country_id")
    private String countryId;

    @SerializedName("country")
    private String country;

    @SerializedName("title")
    private String title;

    @SerializedName("tag")
    private int tag;

    @Nullable
    @SerializedName("status")
    private String status;

    public String getCountry() {
        return country;
    }

    public String getCountryId() {
        return countryId;
    }

    public String getStatus() {
        return status;
    }

    public int getTag() {
        return tag;
    }

    public String getTitle() {
        return title;
    }
}
