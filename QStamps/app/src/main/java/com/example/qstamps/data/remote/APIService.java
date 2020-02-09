package com.example.qstamps.data.remote;
import com.example.qstamps.ScanParams;
import com.example.qstamps.data.model.LoginParams;
import com.example.qstamps.scanResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {


    @POST("/scan")
    Call<scanResult> getScanResult(@Body ScanParams param);

    @POST("/login")
    @FormUrlEncoded
    Call<LoginParams> getLoginResult(@Field("username") String uname);

    /*
    Call<LoginParams> savePost(@Field("username") String uname,
                               @Field("body") String body,
                               @Field("userId") long userId);
                               */
}
