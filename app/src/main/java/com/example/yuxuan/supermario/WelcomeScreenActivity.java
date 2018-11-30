package com.example.yuxuan.supermario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeScreenActivity extends AppCompatActivity {


    String username;
    String roleName;

    private Button welcomeAdminBtn;
    private Button welcomeProviderBtn;
    private Button welcomeOwnerBtn;
    Intent intentss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        //TODO: set userRole and userName to its corresponding type

        welcomeAdminBtn = (Button)findViewById(R.id.welcomeAdminButton);
        welcomeProviderBtn = (Button)findViewById(R.id.welcomeProviderButton);
        welcomeOwnerBtn = (Button)findViewById(R.id.welcomeOwnerButton);

        TextView welcomeUserName = (TextView)findViewById(R.id.welcomeTextViewUserName);
        TextView welcomeUserRole = (TextView)findViewById(R.id.welcomeTextViewUserRole);
        intentss = getIntent();

        username = intentss.getStringExtra("username");
        roleName = intentss.getStringExtra("accounttype");

        welcomeUserName.setText(username);
        welcomeUserRole.setText(roleName);

        if(roleName.equals("administrator") ){
            welcomeProviderBtn.setEnabled(false);
            welcomeOwnerBtn.setEnabled(false);
        }else if(roleName.equals("serviceProviders")){
            welcomeAdminBtn.setEnabled(false);
            welcomeOwnerBtn.setEnabled(false);

        }else if(roleName.equals("homeOwners")){
            welcomeProviderBtn.setEnabled(false);
            welcomeAdminBtn.setEnabled(false);

        }

        //TODO: add onlicklistener for 3 button
        //TODO: jump only to the corresponding userRole
        welcomeAdminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminJump();
            }
        });

        welcomeProviderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceProviderJump();
            }
        });
        welcomeOwnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeOwnerJump();
            }
        });


//        if(roleName =="administrator"){
//            //roleName from MyAccountType
//
//            welcomeProviderBtn.setClickable(false);
//            welcomeOwnerBtn.setClickable(false);
//
//        }else if(roleName =="serviceProviders"){
//            //roleName from MyAccountType
//
//            welcomeAdminBtn.setClickable(false);
//            welcomeOwnerBtn.setClickable(false);
//
//        }else if(roleName =="homeOwners"){
//            //roleName from MyAccountType
//
//            welcomeProviderBtn.setClickable(false);
//            welcomeAdminBtn.setClickable(false);
//
//        }
    }

    public void adminJump() {
        Intent intent = new Intent(getApplicationContext(), AdminPage.class);
        startActivityForResult(intent, 0);
    }

    public void serviceProviderJump(){
        Intent intent = new Intent(getApplicationContext(),ServiceProviderMainPage.class);
        intent.putExtra("username",intentss.getStringExtra("username"));
        startActivityForResult(intent,0);
    }
    public void homeOwnerJump(){
        Intent intent = new Intent(getApplicationContext(),HomeOwnerMainPageActivity.class);
        intent.putExtra("username",intentss.getStringExtra("username"));
        startActivityForResult(intent,0);
    }
}
