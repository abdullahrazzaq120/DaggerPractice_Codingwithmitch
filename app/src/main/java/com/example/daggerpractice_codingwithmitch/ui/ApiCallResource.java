package com.example.daggerpractice_codingwithmitch.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ApiCallResource<T> {

    @NonNull
    public final AuthStatus status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;

    public ApiCallResource(@NonNull AuthStatus status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> ApiCallResource<T> authenticated(@Nullable T data) {
        return new ApiCallResource<>(AuthStatus.AUTHENTICATED, data, null);
    }

    public static <T> ApiCallResource<T> error(@NonNull String msg, @Nullable T data) {
        return new ApiCallResource<>(AuthStatus.ERROR, data, msg);
    }

    public static <T> ApiCallResource<T> loading(@Nullable T data) {
        return new ApiCallResource<>(AuthStatus.LOADING, data, null);
    }

    public static <T> ApiCallResource<T> logout() {
        return new ApiCallResource<>(AuthStatus.NOT_AUTHENTICATED, null, null);
    }

    public static <T> ApiCallResource<T> success(@Nullable T data) {
        return new ApiCallResource<>(AuthStatus.SUCCESS, data, null);
    }

    public enum AuthStatus {
        AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED, SUCCESS
    }
}