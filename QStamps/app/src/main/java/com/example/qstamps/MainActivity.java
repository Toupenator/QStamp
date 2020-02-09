package com.example.qstamps;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

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

    public void OnSignInButton(View view, final
    String userName, final String userPassword){

        //Create handle for the RetrofitInstance interface
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Boolean> call = service.getLoginResult(userName);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                System.out.println("failure\n");
            }
        });

        /*
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.putExtra("firstName",firstName);
        intent.putExtra("firstName",lastName);

        intent.putExtra("userType","employee");
        intent.putExtra("id",ds.getKey());
        startActivityForResult(intent, 0);
        */
    }






}