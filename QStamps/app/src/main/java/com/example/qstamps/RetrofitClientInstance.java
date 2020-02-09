package com.example.qstamps;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//create a retrofit instance

public class RetrofitClientInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://127.0.0.1:5000/";
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
