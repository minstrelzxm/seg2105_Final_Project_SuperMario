package com.example.yuxuan.supermario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class ServiceProviderInfoShown extends AppCompatActivity {
    private TextView editAddress;
    private TextView editPhoneNum;
    private TextView editNameOfCompany;
    private TextView editGeneralInfo;
    private TextView editLicensed;
    private Button changeInfo;
    DatabaseReference databaseInfo;
    String username;
    String roleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_info_shown);



    }
}
