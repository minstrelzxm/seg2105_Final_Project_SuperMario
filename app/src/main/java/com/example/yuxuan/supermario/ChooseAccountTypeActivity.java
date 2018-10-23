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
                CreateAccountButton();

            }
        });
        Providerbutton=findViewById(R.id.chooseTypeProviderBtn);
        Providerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccountButton();
            }
        });
        Ownerbutton=findViewById(R.id.chooseTypeOwnerBtn);
        Ownerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccountButton();
            }
        });
    }
    public void CreateAccountButton(){
        Intent intent=new Intent(getApplicationContext(),CreateAccountActivity.class);
        startActivityForResult(intent,0);
    }



}
