package com.example.qstamps.data.remote;
import com.example.qstamps.ScanParams;
import com.example.qstamps.data.model.LoginParams;
import com.example.qstamps.data.model.PostStatus;
import com.example.qstamps.scanResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {


    @POST("/scan")
    Call<scanResult> getScanResult(@Body ScanParams param);

    //@Headers({"Content-Type: application/json"})
    @POST("/login")
    @FormUrlEncoded
    Call<PostStatus> getLoginResult(@Field("username") String uname,
                                    @Field("password") String password);

    /*
    Call<LoginParams> savePost(@Field("username") String uname,
                               @Field("body") String body,
                               @Field("userId") long userId);
                               */
}
