package com.example.qstamps;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qstamps.data.model.LoginParams;
import com.example.qstamps.data.model.PostStatus;
import com.example.qstamps.data.remote.APIService;
import com.example.qstamps.data.remote.ApiUtils;
import com.example.qstamps.data.remote.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private APIService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button signUp = findViewById(R.id.btn_signup);
        final EditText input_userName = findViewById(R.id.input_username);
        final EditText input_password = findViewById(R.id.input_password);



        signUp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                OnSignUpButton(v);
            }
        });

        final Button signIn = findViewById(R.id.btn_signin);
        signIn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                //Checking if user clicks sign in without entering any value
                String userName = input_userName.getText().toString();
                String password = input_password.getText().toString();
                if(!IsValidated(userName)){
                    input_userName.setError("Please enter a username!");
                }
                if(!IsValidated(password)){
                    input_password.setError("Please enter a password!");
                }

                OnSignInButton(v,
                        input_userName.getText().toString(),
                        input_password.getText().toString());

            }
        });

    }

    //IsValidated method
    public boolean IsValidated(String input){
        if(TextUtils.isEmpty(input))
            return false;
        return true;
    }


    public void OnSignUpButton(View view){
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivityForResult (intent,0);
    }

    public void OnSignInButton(View view, final String userName, final String userPassword){
        mApiService = ApiUtils.getAPIService();


        mApiService.getLoginResult(userName, userPassword).enqueue(new Callback<PostStatus>() {
            @Override
            public void onResponse(Call<PostStatus> call, Response<PostStatus> response) {

                if(response.isSuccessful()) {
                    String status = response.body().getStatus().trim();

                    if (status.equals("success")) {

                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivityForResult (intent,0);
                    }
                    if (status.equals("user_not_found")) {


                        //TODO DISPLAY HERE USER NOT FOUND
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<PostStatus> call, Throwable t) {
                Log.e(TAG, "\n\nUnable to submit post to API.\n\n");
                t.printStackTrace();
            }
        });

    }



    public void getLoginResult(String userName, String password) {

    }

    public void showResponse(String response) {
        System.out.println(response);
    }


}