package com.readboy.homeworkcollection.net;


import com.readboy.homeworkcollection.util.log.LogUtils;

import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseRequest {

    public static final String DOMAIN = "http://diagnose-api.dev.dreamdev.cn/";
    public static final String PREFIX = "v1/";
    public static final String API_TOKEN = "CFIsGgvkonYEoVURomNZCk1HwshSQhDw";

    public BaseRequest() {
    }

    public static RequestInterface getServer() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NotNull String s) {
                LogUtils.d("request ===> " + s);
            }
        });

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DOMAIN + PREFIX)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);

        return request;
    }
}
