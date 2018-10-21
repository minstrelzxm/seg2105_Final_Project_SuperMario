package com.example.yuxuan.supermario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseAccountTypeActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;
    private Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account_type);

        button1=findViewById(R.id.createAdminTypeBtn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccountButton();
            }
        });
        button2=findViewById(R.id.createProviderTypeBtn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccountButton();
            }
        });
        button3=findViewById(R.id.createOwnerTypeBtn);
        button3.setOnClickListener(new View.OnClickListener() {
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
