package com.example.yuxuan.supermario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.common.internal.AccountType;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceProviderInfoPageActivity extends AppCompatActivity {

    @Override
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

    //TODO: add this service provider to database
}
