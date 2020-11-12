package com.tetravalstartups.anandsuper.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofitRest;
    private static final String BASE_URL = "http://vedanshtechnologies.com/AnandSuper100/";
    public static Retrofit getRetrofitInstance() {
        if (retrofitRest == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofitRest = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofitRest;
    }
}
