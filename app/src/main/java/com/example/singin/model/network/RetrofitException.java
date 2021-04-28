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

    /** Identifies the event kind which triggered a {@link RetrofitException}. */
    public enum Kind {
        /** An {@link IOException} occurred while communicating to the server. */
        NETWORK,
        /** A non-200 HTTP status code was received from the server. */
        HTTP,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
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

    /** The request URL which produced the error. */
    public String getUrl() {
        return url;
    }

    /** Response object containing status code, headers, body, etc. */
    public Response getResponse() {
        return response;
    }

    /** The event kind which triggered this error. */
    public Kind getKind() {
        return kind;
    }

    /** The Retrofit this request was executed on */
    public Retrofit getRetrofit() {
        return retrofit;
    }

    /**
     * HTTP response body converted to specified {@code type}. {@code null} if there is no
     * response.
     *
     * @throws IOException if unable to convert the body to the specified {@code type}.
     */
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

