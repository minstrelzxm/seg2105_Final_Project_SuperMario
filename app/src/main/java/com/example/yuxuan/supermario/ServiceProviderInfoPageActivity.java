package com.example.yuxuan.supermario;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

public class ServiceProviderInfoPageActivity extends AppCompatActivity {

    private EditText address;
    private EditText phoneNum;
    private EditText nameOfCompany;
    private EditText generalInfo;
    private EditText licensed;

    //FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_info_page);
        address = (EditText) findViewById(R.id.EditAddress);
        phoneNum = (EditText) findViewById(R.id.EditPhoneNum);
        nameOfCompany = (EditText) findViewById(R.id.EditNameOfCompany);
        generalInfo = (EditText) findViewById(R.id.EditGeneralInfo);
        
    }

}
