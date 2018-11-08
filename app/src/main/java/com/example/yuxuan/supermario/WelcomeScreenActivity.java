package com.example.yuxuan.supermario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeScreenActivity extends AppCompatActivity {


    String username;
    String roleName;

    private Button welcomeAdminBtn;
    private Button welcomeProviderBtn;
    private Button welcomeOwnerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        //TODO: set userRole and userName to its corresponding type

        welcomeAdminBtn = (Button)findViewById(R.id.welcomeAdminButton);
        welcomeProviderBtn = (Button)findViewById(R.id.welcomeProviderButton);
        welcomeOwnerBtn = (Button)findViewById(R.id.welcomeOwnerButton);

        TextView err = (TextView)findViewById(R.id.welcomeTextViewUserName);
        TextView kkk = (TextView)findViewById(R.id.welcomeTextViewUserRole);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        roleName = intent.getStringExtra("accounttype");
        kkk.setText(roleName);
        err.setText(username);

        if(roleName =="administrator"){
            //roleName from MyAccountType
            welcomeProviderBtn.setEnabled(false);
            welcomeOwnerBtn.setEnabled(false);

        }else if(roleName =="serviceProviders"){
            //roleName from MyAccountType
            welcomeAdminBtn.setEnabled(false);
            welcomeOwnerBtn.setEnabled(false);

        }else if(roleName =="homeOwners"){
            //roleName from MyAccountType
            welcomeProviderBtn.setEnabled(false);
            welcomeAdminBtn.setEnabled(false);

        }
    }
}
