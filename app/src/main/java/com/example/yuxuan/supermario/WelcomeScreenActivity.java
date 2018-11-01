package com.example.yuxuan.supermario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class WelcomeScreenActivity extends AppCompatActivity {


    String username;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        //TODO: set userRole and userName to its corresponding type
        TextView err = (TextView)findViewById(R.id.welcomeTextViewUserName);
        TextView kkk = (TextView)findViewById(R.id.welcomeTextViewUserRole);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("accounttype");
        kkk.setText(password);
        err.setText(username);
    }

    //TODO: Admin to activity_admin_page; disable other buttons
    //TODO: provider to provider page;
    //TODO: Owner to owner page;
}
