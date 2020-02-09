package com.example.qstamps;

import android.content.Intent;
import android.util.Log;

import com.example.qstamps.data.model.PostStatus;
import com.example.qstamps.data.model.ScanStatus;
import com.example.qstamps.data.remote.APIService;
import com.example.qstamps.data.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Scanner {

    private APIService mApiService;
    protected void scan(String username, String scanned_key){
        mApiService = ApiUtils.getAPIService();


        mApiService.getScanResult(username, scanned_key).enqueue(new Callback<ScanStatus>() {
            @Override
            public void onResponse(Call<ScanStatus> call, Response<ScanStatus> response) {

                if(response.isSuccessful()) {
                    String sender = response.body().getSender().trim();
                    String reciever = response.body().getReciever().trim();
                    String status = response.body().getStatus().trim();
                    if (status.equals("sender-editing")) {
                        //TODO NOTIFY SENDER THAT THEIR MAIL HAS BEEN RECIEVED
                    }
                    if (status.equals("recieved")) {
                        //TODO NOTIFY SENDER THAT THEIR MAIL HAS BEEN RECIEVED
                    }
                    if (status.equals("intercepted")) {
                        //TODO NOTIFY USER THAT THEIR MAIL HAS BEEN FOUND
                    }
                    if (status.equals("prompt-to-link")) {
                        //TODO PROMPT USER TO LINK ADDRESS USING RECIPIENT ADDRESS
                    }

                }
            }

            @Override
            public void onFailure(Call<ScanStatus> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
