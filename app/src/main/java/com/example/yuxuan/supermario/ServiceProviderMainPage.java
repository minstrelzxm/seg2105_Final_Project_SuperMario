package com.example.yuxuan.supermario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ServiceProviderMainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_main_page);
    }

    public void infoJump(){
        Intent intent = new Intent(getApplicationContext(), ServiceProviderInfoPageActivity.class);
        startActivityForResult(intent, 0);

    }

    public void addServiceJump(){
        Intent intent = new Intent(getApplicationContext(), AdminPage.class);
        startActivityForResult(intent, 0);

    }

    public void availJump(){
        Intent intent = new Intent(getApplicationContext(), AdminPage.class);
        startActivityForResult(intent, 0);

    }
}
