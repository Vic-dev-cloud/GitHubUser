package com.example.githubuser;

import com.google.gson.Gson;

public class GSONUtil {
    private Gson gson;

    private static class Holder {
        private static GSONUtil instance = new GSONUtil();
    }

    public static GSONUtil getInstance() {
        return Holder.instance;
    }

    private GSONUtil() {
        gson = new Gson();
    }

    public String toJson(Object object) {
        return gson.toJson(object);
    }

    public <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

}
