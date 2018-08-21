package com.example.hw9.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.lang.reflect.Type;

public class JsonUtils {

    //private static final Gson gson = new Gson();
    private static final Gson gson = new GsonBuilder().serializeNulls().create();

    public static String toJson(Object src) {
        if (src instanceof JSONObject)
            return src.toString();
        return gson.toJson(src);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        try {
            return gson.fromJson(json, typeOfT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return gson.fromJson(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
