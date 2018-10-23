package com.example.yuxuan.supermario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class WelcomeScreenActivity extends AppCompatActivity {

    TextView welcomeAccName;
    TextView welcomeAccType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        //TODO: The welcome screen should show the type of account and its's user name;
        welcomeAccName = findViewById(R.id.welcomeTextViewUserName);
        welcomeAccType = findViewById(R.id.welcomeTextViewUserRole);

    }





}
