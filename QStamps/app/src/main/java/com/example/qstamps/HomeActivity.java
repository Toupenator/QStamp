package com.example.qstamps;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private String username;
    TextView showUserName = findViewById(R.id.loggedin_name);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        username = getIntent().getExtras().getString("id");

        final Button btn_signout = findViewById(R.id.btn_signout);
        final Button btn_scan = findViewById(R.id.btn_scan);

        btn_scan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), CameraScannerActivity.class);
                startActivityForResult (intent,0);

            }
            });


        btn_signout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                OnSignOutButton(v);
            }
        });

        //FROM THE USERNAME GET THE FIRST & LAST NAME
        //THIS WILL SHOW IT TO THE WELCOME
        showUserName.setText(getIntent().getExtras().getString("WE HAVE TO PASS THE FIRST AND LAST NAME)"));


    }

    public void OnSignOutButton(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult (intent,0);
    }

    //HERE TO DISABLE THE HARDWARE BACK BOTTON SINCE THERE IS A BUTTON CALLED SIGN OUT TO BE PRESSED
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
