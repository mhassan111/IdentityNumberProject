package com.identitynumber.app.apiResponse;

import com.google.gson.JsonElement;
import com.identitynumber.app.enums.Status;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import static com.identitynumber.app.enums.Status.ERROR;
import static com.identitynumber.app.enums.Status.LOADING;
import static com.identitynumber.app.enums.Status.SUCCESS;

public class ApiResponse {

    public final Status status;

    @Nullable
    public final JsonElement data;

    @Nullable
    public final Throwable error;

    private ApiResponse(Status status, @Nullable JsonElement data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static ApiResponse loading() {
        return new ApiResponse(LOADING, null, null);
    }

    public static ApiResponse success(@NonNull JsonElement data) {
        return new ApiResponse(SUCCESS, data, null);
    }

    public static ApiResponse error(@NonNull Throwable error) {
        return new ApiResponse(ERROR, null, error);
    }

}
