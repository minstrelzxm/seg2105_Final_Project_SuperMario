package com.example.yuxuan.supermario;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ServiceProviderInfoPageActivity extends AppCompatActivity {


    private EditText editAddress;
    private EditText editPhoneNum;
    private EditText editNameOfCompany;
    private EditText editGeneralInfo;
    private EditText editLicensed;
    private Button saveButton;

    DatabaseReference databaseInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_info_page);

        databaseInfo = FirebaseDatabase.getInstance().getReference("ServiceProviderInfo");

        editAddress = (EditText) findViewById(R.id.EditAddress);
        editPhoneNum = (EditText) findViewById(R.id.EditPhoneNum);
        editNameOfCompany = (EditText) findViewById(R.id.EditNameOfCompany);
        editGeneralInfo = (EditText) findViewById(R.id.EditGeneralInfo);
        editLicensed = (EditText) findViewById(R.id.BooleanLicensed);
        saveButton = (Button) findViewById(R.id.SaveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = editAddress.getText().toString();
                String phoneNum = editPhoneNum.getText().toString();
                String nameOfCompany = editNameOfCompany.getText().toString();
                String generalInfo = editGeneralInfo.getText().toString();
                String licensed = editLicensed.getText().toString();
                String id = getIntent().getExtras().getString("ID");

                Intent intent = new Intent(ServiceProviderInfoPageActivity.this, ServiceProviderInfoShown.class);
                intent.putExtra("Address",address);
                intent.putExtra("Phone",phoneNum);
                intent.putExtra("Company",nameOfCompany);
                intent.putExtra("Info",generalInfo);
                intent.putExtra("Licensed",licensed);
                //intent.putExtra("ID",id);

                addInfo();
                //showUpdateDeleteDialog(id);
                OnBackServiceProviderActivity();
            }
        });

    }


    //TODO: add this service provider to database
    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showUpdateDeleteDialog(final String productId) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_service_provider_info_shown, null);
        dialogBuilder.setView(dialogView);

        final EditText editAddressInfo = (EditText) findViewById(R.id.EditAddress);
        final EditText editPhoneNumInfo = (EditText) findViewById(R.id.EditPhoneNum);
        final EditText editNameOfCompanyInfo = (EditText) findViewById(R.id.EditNameOfCompany);
        final EditText editGeneralInfoInfo = (EditText) findViewById(R.id.EditGeneralInfo);
        final EditText editLicensedInfo = (EditText) findViewById(R.id.BooleanLicensed);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.SaveButton);


        //dialogBuilder.setTitle(productName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = editAddressInfo.getText().toString().trim();
                String phoneNum = editPhoneNumInfo.getText().toString().trim();
                String nameOfCompany = editNameOfCompanyInfo.getText().toString().trim();
                String generalInfo = editGeneralInfoInfo.getText().toString().trim();
                String licensed = editLicensedInfo.getText().toString().trim();
                if (!TextUtils.isEmpty(address)||!TextUtils.isEmpty(phoneNum)||!TextUtils.isEmpty(nameOfCompany)||
                        !TextUtils.isEmpty(generalInfo)||!TextUtils.isEmpty(licensed)) {
                    updateInfo(productId, address, phoneNum, nameOfCompany, generalInfo, licensed);
                    b.dismiss();
                }
            }
        });


    }

    private void addInfo() {
        //getting the values to save
        String address = editAddress.getText().toString().trim();
        String phoneNum = editPhoneNum.getText().toString().trim();
        String nameOfCompany = editNameOfCompany.getText().toString().trim();
        String generalInfo = editGeneralInfo.getText().toString().trim();
        String licensed = editLicensed.getText().toString().trim();

        //checking if the value is provided
        if (!TextUtils.isEmpty(address)||!TextUtils.isEmpty(phoneNum)||!TextUtils.isEmpty(nameOfCompany)||
                !TextUtils.isEmpty(generalInfo)||!TextUtils.isEmpty(licensed)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Product
            String id = databaseInfo.push().getKey();
            //String id = username;

            //creating an Object
            ServiceProviderInfo info = new ServiceProviderInfo(id,address,phoneNum,nameOfCompany,generalInfo,licensed);

            //Saving the Product
            databaseInfo.child(id).setValue(info);

            //setting edittext to blank again
            /**editAddress.setText("");
            editPhoneNum.setText("");
            editNameOfCompany.setText("");
            editGeneralInfo.setText("");
            editLicensed.setText("");**/


            //displaying a success toast
            Toast.makeText(this, "Info Added", Toast.LENGTH_LONG).show();
            //OnBackServiceProviderActivity();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a necessary text(s)", Toast.LENGTH_LONG).show();
        }
    }

    private void updateInfo(String id, String address, String phoneNum, String nameOfCompany, String generalInfo, String licensed){
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("ServiceProviderInfo").child(id);
        ServiceProviderInfo info = new ServiceProviderInfo(id, address,phoneNum,nameOfCompany,generalInfo,licensed);
        dR.setValue(info);
        Toast.makeText(getApplicationContext(),"Info Updated",Toast.LENGTH_LONG).show();
    }

    private void deleteInfo(String id){
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("ServiceProviderInfo").child(id);
        dR.removeValue();
        Toast.makeText(getApplicationContext(),"Info Deleted",Toast.LENGTH_LONG).show();

    }

    public void OnBackServiceProviderActivity(){
        //Toast.makeText(this,"Successfully created an Account",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(getApplicationContext(),ServiceProviderMainPage.class);
        startActivityForResult(intent,0);
    }
}
