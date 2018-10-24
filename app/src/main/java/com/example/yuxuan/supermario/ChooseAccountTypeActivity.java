package com.example.yuxuan.supermario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseAccountTypeActivity extends AppCompatActivity {
    private Button Adminbutton;
    private Button Providerbutton;
    private Button Ownerbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account_type);

        Adminbutton=findViewById(R.id.chooseTypeAdminBtn);
        Adminbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account adminAccount = new Account(" "," ",MyAccountType.administrator);
                CreateAccountButton(MyAccountType.administrator);

            }
        });
        Providerbutton=findViewById(R.id.chooseTypeProviderBtn);
        Providerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account providerAccount = new Account(" "," ",MyAccountType.serviceProviders);
                CreateAccountButton(MyAccountType.serviceProviders);
            }
        });
        Ownerbutton=findViewById(R.id.chooseTypeOwnerBtn);
        Ownerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account ownerAccount = new Account(" "," ",MyAccountType.homeOwners);
                CreateAccountButton(MyAccountType.homeOwners);
            }
        });
    }
    public void CreateAccountButton(MyAccountType currentType){
        if(currentType == MyAccountType.administrator){
            Intent intent=new Intent(getApplicationContext(),CreateAccountActivity.class);
            startActivityForResult(intent,0);
        }
        else if(currentType == MyAccountType.serviceProviders){
            Intent intent=new Intent(getApplicationContext(),create_provider_account.class);
            startActivityForResult(intent,0);
        }
        else{
            Intent intent=new Intent(getApplicationContext(),create_owner_account.class);
            startActivityForResult(intent,0);
        }
    }



}
