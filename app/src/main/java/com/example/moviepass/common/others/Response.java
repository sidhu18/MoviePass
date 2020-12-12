package com.example.moviepass.common.others;

public class Response<T> {

    private final Status status;
    private final T data;
    private final String message;

    public Response(Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }


    public static <T> Response<T> error() {
        return error("");
    }

    public static <T> Response<T> error(String message) {
        return new Response<>(
                Status.ERROR,
                null,
                message
        );
    }

    public static <T> Response<T> loading() {
        return new Response<>(
                Status.LOADING,
                null,
                null
        );
    }

    public static <T> Response<T> success(T data) {
        return success(data, "Success");
    }

    public static <T> Response<T> success(T data, String message) {
        return new Response<>(
                Status.SUCCESS,
                data,
                message
        );
    }
}
