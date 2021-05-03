package com.example.singin.model.network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;

import retrofit2.Response;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class RetrofitException extends RuntimeException {

    public static RetrofitException httpError(String url, Response response, Retrofit retrofit) {
        String message = response.code() + " " + response.message();
        return new RetrofitException(message, url, response, Kind.HTTP, null, retrofit);
    }

    public static RetrofitException networkError(IOException exception) {
        return new RetrofitException(exception.getMessage(), null, null, Kind.NETWORK, exception, null);
    }

    public static RetrofitException unexpectedError(Throwable exception) {
        return new RetrofitException(exception.getMessage(), null, null, Kind.UNEXPECTED, exception, null);
    }


    public enum Kind {

        NETWORK,
        HTTP,
        UNEXPECTED
    }

    private final String url;
    private final Response response;
    private final Kind kind;
    private final Retrofit retrofit;
    private String errorBodyString;

    RetrofitException(String message, String url, Response response, Kind kind, Throwable exception, Retrofit retrofit) {
        super(message, exception);
        this.url = url;
        this.response = response;
        this.kind = kind;
        this.retrofit = retrofit;
    }


    public String getUrl() {
        return url;
    }

    public Response getResponse() {
        return response;
    }
    public Kind getKind() {
        return kind;
    }
    public Retrofit getRetrofit() {
        return retrofit;
    }
    public <T> T getErrorBodyAs(Class<T> type){
        if (response == null || response.errorBody() == null) {
            return null;
        }
        Converter<ResponseBody, T> converter = retrofit.responseBodyConverter(type, new Annotation[0]);

        try {
            return converter.convert(response.errorBody());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getErrorBodyAsString() throws IOException {
        if (errorBodyString == null) {
            errorBodyString = response.errorBody().string();
        }
        return errorBodyString;
    }

    public String getUserFacingMessage() {
        if (response == null || response.errorBody() == null) {
            return null;
        }
        StringBuilder messageBuilder = new StringBuilder();
        try {
            HashMap<String, List<String>> errorsMap =
                    new Gson().fromJson(getErrorBodyAsString(),
                            new TypeToken<HashMap<String, List<String>>>() {
                            }.getType());
            for (List<String> errors : errorsMap.values()) {
                for (String error : errors) {
                    messageBuilder.append(error);
                    messageBuilder.append(" ");
                }
            }
        } catch (Exception ignored) {
        }
        return messageBuilder.toString();
    }
}

