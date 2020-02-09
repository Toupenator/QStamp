package com.example.qstamps.data.remote;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://9df62ee4.ngrok.io";

    public static APIService getAPIService() {

        return RetrofitClientInstance.getClient(BASE_URL).create(APIService.class);
    }



}
