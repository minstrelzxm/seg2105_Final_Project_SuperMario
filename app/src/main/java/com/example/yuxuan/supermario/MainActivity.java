package com.example.yuxuan.supermario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button CreateAccountButton;
    private Button MainLoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CreateAccountButton=findViewById(R.id.mainCreateAccBtn);
        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnAccountTypeButton();
            }
        });
        MainLoginButton=findViewById(R.id.mainLoginBtn);
        MainLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnAccountLoginButton();
            }
        });

    }
    public void OnAccountTypeButton(){
        Intent intent=new Intent(getApplicationContext(),ChooseAccountTypeActivity.class);

        startActivityForResult(intent,0);
    }
    public void OnAccountLoginButton(){
        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
        startActivityForResult(intent,0);
    }
}
