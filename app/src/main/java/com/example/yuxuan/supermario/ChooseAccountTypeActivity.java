package com.example.yuxuan.supermario;
/*
After click the button for Admin, provider, and owner, we create an empty
Account for the corresponding type.

The empty account need to have an empty string " " for both username(Email) and
password, but the Account type is specified.We then pass the Account type(currentType)
we created by using
intent.putExtra("selectedType", currentType);
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
//import MyAccountType;

import com.google.android.gms.common.internal.AccountType;

public class ChooseAccountTypeActivity extends AppCompatActivity {
    private Button Adminbutton;
    private Button Providerbutton;
    private Button Ownerbutton;
    public MyAccountType currentType;

    Account[] adminAccs = new Account[1];
    Account[] providerAccs = new Account[200];
    Account[] ownerAccs = new Account[200];
    /*
    I try to use array to specify the number of accounts for each type of account
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account_type);

        Adminbutton=findViewById(R.id.chooseTypeAdminBtn);
        Adminbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // can only have 1 admin account
                try{
                    Account adminAccount = new Account("admin","admin",MyAccountType.administrator);
                    currentType = MyAccountType.administrator;
                    CreateAccountButton();
                }
                catch(Exception E){

                }

            }
        });
        Providerbutton=findViewById(R.id.chooseTypeProviderBtn);
        Providerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account providerAccount = new Account(" "," ",MyAccountType.serviceProviders);
                currentType = MyAccountType.serviceProviders;
                CreateAccountButton();
            }
        });
        Ownerbutton=findViewById(R.id.chooseTypeOwnerBtn);
        Ownerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account ownerAccount = new Account(" "," ",MyAccountType.homeOwners);
                currentType = MyAccountType.homeOwners;
                CreateAccountButton();
            }
        });
    }
    public void CreateAccountButton(){
        Intent intent=new Intent(getApplicationContext(),CreateAccountActivity.class);
        intent.putExtra("selectedType", currentType);
        startActivityForResult(intent,0);
    }
//    public MyAccountType getCurrentType() {
//        return currentType;
//    }



}
