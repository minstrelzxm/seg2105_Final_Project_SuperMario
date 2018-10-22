package com.example.yuxuan.supermario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CreateAccountActivity extends AppCompatActivity {
    private Button BackMainButton;

    DatabaseReference databaseProducts;

    protected void onCreate(Bundle savedInstanceState) {
        databaseProducts = FirebaseDatabase.getInstance().getReference("account");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);




        BackMainButton=findViewById(R.id.createAccCreateBtn);
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
    
}
