package com.example.yuxuan.supermario;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ServiceProviderInfoShown extends AppCompatActivity {
    private TextView editAddress;
    private TextView editPhoneNum;
    private TextView editNameOfCompany;
    private TextView editGeneralInfo;
    private TextView editLicensed;
    private Button changeInfo;
    private Button back;

    //DatabaseReference databaseInfo;
    String address;
    String phoneNum;
    String nameOfCompany;
    String generalInfo;
    String licensed;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_info_shown);

        //address=getIntent().getExtras().getString("address");
        address = getIntent().getExtras().getString("Address");
        phoneNum = getIntent().getExtras().getString("Phone");
        generalInfo = getIntent().getExtras().getString("Info");
        nameOfCompany = getIntent().getExtras().getString("Company");
        licensed = getIntent().getExtras().getString("Licensed");

        changeInfo = (Button) findViewById(R.id.ChangeButton);
        back = (Button) findViewById(R.id.BackButton);

        //id = getIntent().getExtras().getString("ID");
        //databaseInfo = FirebaseDatabase.getInstance().getReference("ServiceProviderInfo").child(id);
        /**databaseInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ServiceProviderInfo sInfo = dataSnapshot.getValue(ServiceProviderInfo.class);
                System.out.println(sInfo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
            String address = sInfo.get
            String phoneNum;
            String nameOfCompany;
            String generalInfo;
            String licensed;
            String id;
        })**/
        editAddress = (TextView)findViewById(R.id.EditAddress);
        editAddress.setText(address);
        editPhoneNum = (TextView)findViewById(R.id.EditPhoneNum);
        editPhoneNum.setText(phoneNum);
        editGeneralInfo = (TextView)findViewById(R.id.EditGeneralInfo);
        editGeneralInfo.setText(generalInfo);
        editNameOfCompany = (TextView)findViewById(R.id.EditNameOfCompany);
        editNameOfCompany.setText(nameOfCompany);
        editLicensed = (TextView)findViewById(R.id.BooleanLicensed);
        editLicensed.setText(licensed);

        changeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ServiceProviderInfoPageActivity.class);
                startActivityForResult(intent,0);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ServiceProviderMainPage.class);
                startActivityForResult(intent,0);
            }
        });

    }

}
