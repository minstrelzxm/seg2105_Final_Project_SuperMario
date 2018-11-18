package com.example.yuxuan.supermario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ServiceProviderMainPage extends AppCompatActivity {

    private Button spInfoBtn;
    private Button spAddServiceBtn;
    private Button spAvailabilitiesBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_main_page);

        spInfoBtn = (Button)findViewById(R.id.serviceProviderMainInfoBtn);
        spAddServiceBtn = (Button)findViewById(R.id.serviceProviderMainAddServiceBtn);
        spAvailabilitiesBtn = (Button)findViewById(R.id.serviceProviderMainAvailabtn);

        spInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoJump();
            }
        });

        spAddServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addServiceJump();
            }
        });

        spAvailabilitiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                availJump();
            }
        });
    }

    public void infoJump(){
        Intent intent = new Intent(getApplicationContext(), ServiceProviderInfoPageActivity.class);
        startActivityForResult(intent, 0);

    }

    public void addServiceJump(){
        Intent intent = new Intent(getApplicationContext(), ServiceProviderAddServiceActivity.class);
        startActivityForResult(intent, 0);

    }

    public void availJump(){
        Intent intent = new Intent(getApplicationContext(), ServiceProviderAvailabilitiesActivity.class);
        startActivityForResult(intent, 0);

    }
}
