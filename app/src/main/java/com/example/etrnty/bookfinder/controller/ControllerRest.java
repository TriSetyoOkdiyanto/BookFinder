package com.example.etrnty.bookfinder.controller;

import android.app.Application;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ControllerRest {
    public static ControllerEndpoint ServiceApi (Application myApplication){

        Cache okHttpCache = new Cache(myApplication.getCacheDir(), 10 * 1024 * 1024);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(getLogInterceptor());
        client.cache(okHttpCache);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ControllerEndpoint.BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ControllerEndpoint.class);
    }

    private static HttpLoggingInterceptor getLogInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}
