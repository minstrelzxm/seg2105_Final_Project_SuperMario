package com.example.yuxuan.supermario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ServiceProviderMainPage extends AppCompatActivity {

    private Button spInfoBtn;
    private Button spAddServiceBtn;
    private Button spAvailabilitiesBtn;
    String username;
    ArrayAdapter<String> adapter;
    ArrayList myList = new ArrayList();
    ArrayList<String> timeList = new ArrayList<>();
    String day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_main_page);

        spInfoBtn = (Button)findViewById(R.id.serviceProviderMainInfoBtn);
        spAddServiceBtn = (Button)findViewById(R.id.serviceProviderMainAddServiceBtn);
        spAvailabilitiesBtn = (Button)findViewById(R.id.serviceProviderMainAvailabtn);
        ListView timeslotlistview = (ListView) findViewById(R.id.serviceProviderTimeSlot);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");

        if (args != null) {
            timeList = (ArrayList<String>) args.getSerializable("ARRAYLIST");
            day = (String)args.getSerializable("Day");
            myList.add(day);
            myList.addAll(timeList);

        }
        username = intent.getStringExtra("username");

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

        if (timeList != null) {
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    myList);
//        setListAdapter(adapter);
            System.out.println("=========" + (timeslotlistview == null));
            timeslotlistview.setAdapter(adapter);
        }

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
