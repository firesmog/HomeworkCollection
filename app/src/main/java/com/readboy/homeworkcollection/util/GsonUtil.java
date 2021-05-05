package com.readboy.homeworkcollection.util;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

public class GsonUtil {

    static final Gson gson = new Gson();

    @NonNull
    public static <T> T fromObject(@NonNull Object src, @NonNull Class<T> classOfT) {
        String s = gson.toJson(src);
        return gson.fromJson(s, classOfT);
    }
}
