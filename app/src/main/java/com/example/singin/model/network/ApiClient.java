package com.example.singin.model.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static  final String URL="https://gorest.co.in";
    private static Retrofit retrofit=null;
    private static final OkHttpClient.Builder httpclientBuilder= new OkHttpClient.Builder();
    public static Retrofit getclient(){
        if(retrofit==null){
            retrofit= new Retrofit.Builder().baseUrl(URL).client(httpclientBuilder.build()).addConverterFactory(GsonConverterFactory.create()).build();
        }return retrofit;
    }

}
