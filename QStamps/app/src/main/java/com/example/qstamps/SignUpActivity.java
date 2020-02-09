package com.example.qstamps;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    EditText fname = findViewById(R.id.input_firstName);
    EditText lname = findViewById(R.id.input_lastName);
    final EditText uname = findViewById(R.id.input_username);

    @Override
    //oncreate function
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);   //set the content view to the signup activity


        Button submit = findViewById(R.id.btn_signup);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vv) {


                if (uname.getText() != null)
                {

                    //Create handle for the RetrofitInstance interface
                    GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                    Call<Boolean> call = service.getLoginResult(uname.getText().toString());
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


                    String newString = uname.getText().toString();
                }
                else
                {

                }


                finish();
            }
        });









        /*
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<scanResult> call = service.getScanResult(new ScanParams("fname", "lname"));
        call.enqueue(new Callback<scanResult>() {
            @Override
            public void onResponse(Call<scanResult> call, Response<scanResult> response) {
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<scanResult> call, Throwable t) {
                System.out.println("failure\n");
            }
        });

         */
    }





}
