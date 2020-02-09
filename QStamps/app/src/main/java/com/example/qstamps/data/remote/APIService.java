package com.example.qstamps.data.remote;
import com.example.qstamps.ScanParams;
import com.example.qstamps.data.model.LoginParams;
import com.example.qstamps.data.model.PostStatus;
import com.example.qstamps.data.model.ScanStatus;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {



    @POST("/scan")
    @FormUrlEncoded
    Call<ScanStatus> getScanResult(@Field("username") String uname,
                                   @Field("scanned_key") String key);


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
