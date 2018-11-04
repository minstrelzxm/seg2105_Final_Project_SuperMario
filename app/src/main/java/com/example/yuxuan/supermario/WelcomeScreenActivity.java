package com.example.yuxuan.supermario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeScreenActivity extends AppCompatActivity {


    String userName;
    String roleName;
    Button adminBtn = (Button)findViewById(R.id.welcomeAdminButton);
    Button providerBtn = (Button)findViewById(R.id.welcomeProviderButton);
    Button ownerBtn = (Button)findViewById(R.id.welcomeOwnerButton);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        //TODO: set userRole and userName to its corresponding type
        TextView userNameTextView = (TextView)findViewById(R.id.welcomeTextViewUserName);
        TextView userRoleTextView = (TextView)findViewById(R.id.welcomeTextViewUserRole);
        Intent intent = getIntent();
        userName = intent.getStringExtra("username");
        roleName = intent.getStringExtra("accounttype");
        userNameTextView.setText(userName);
        userRoleTextView.setText(roleName);

        if(roleName =="administrator"){
            //roleName from MyAccountType
            providerBtn.setEnabled(false);
            ownerBtn.setEnabled(false);

        }else if(roleName =="serviceProviders"){
            //roleName from MyAccountType
            adminBtn.setEnabled(false);
            ownerBtn.setEnabled(false);

        }else if(roleName =="homeOwners"){
            //roleName from MyAccountType
            providerBtn.setEnabled(false);
            adminBtn.setEnabled(false);

        }

    }

    //TODO: Admin to activity_admin_page; disable other buttons
    //TODO: provider to provider page;
    //TODO: Owner to owner page;

}
