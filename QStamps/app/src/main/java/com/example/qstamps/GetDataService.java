package com.example.qstamps;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GetDataService {

    @POST("/login")
    Call<Boolean> getLoginResult(@Body String uname);

    @POST("/scan")
    Call<scanResult> getScanResult(@Body ScanParams param);

}
