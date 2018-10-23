package com.example.yuxuan.supermario;
/*
We use setter to set the username(Email) and password
from the UI(xml) and create account.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.common.internal.AccountType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;**/

public class CreateAccountActivity extends AppCompatActivity {
    private Button BackMainButton;

    DatabaseReference databaseAccounts;
    EditText createAccAccName;
    EditText createAccAccPassword;
    EditText createAccReAccPassword;
    MyAccountType selectedType;
    Button buttonAddAccount;
    ListView listViewAccounts;

    List<Account> accounts;

    protected void onCreate(Bundle savedInstanceState) {
        databaseAccounts = FirebaseDatabase.getInstance().getReference("account");
        super.onCreate(savedInstanceState);
        //types = ChooseAccountTypeActivity.currentType;
        setContentView(R.layout.activity_create_account);
        selectedType = (MyAccountType) getIntent().getSerializableExtra("selectedType");

        createAccAccName = (EditText) findViewById(R.id.createAccAccName);
        createAccAccPassword = (EditText) findViewById(R.id.createAccAccPassword);
        createAccReAccPassword = (EditText) findViewById(R.id.createAccReAccPassword);
        //types = (MyAccountType) findViewById(R.id.)
        //buttonAddAccount = (Button) findViewById(R.id.createAccCreateBtn);

        accounts = new ArrayList<>();

        BackMainButton=(Button)findViewById(R.id.createAccCreateBtn);
        BackMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(R.id.createAccAccPassword!=R.id.createAccReAccPassword){//need add checking email in firebase.
                    OnBackMainActivityFail();
                }
                else{

                    OnBackMainActivity();
                }
            }
        });

    }
    
    public void OnBackMainActivity(){
        Toast.makeText(this,"Successfully created an Account",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivityForResult(intent,0);
    }
    public void OnBackMainActivityFail(){
        Toast.makeText(this,"Fail to created an Account",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivityForResult(intent,0);
    }

    private void addAccount() {

        //Toast.makeText(this, "NOT IMPLEMENTED YET", Toast.LENGTH_LONG).show();
        String username = createAccAccName.getText().toString().trim();
        String password = createAccAccPassword.getText().toString().trim();

        if(!TextUtils.isEmpty(username)){
            String id = databaseAccounts.push().getKey();
            Account myAccount = new Account(username,password,selectedType);

            databaseAccounts.child(id).setValue(myAccount);
            createAccAccName.setText("");
            createAccAccPassword.setText("");
            Toast.makeText(this, "Account added", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_LONG).show();
        }
    }
    
}
