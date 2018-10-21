package com.example.yuxuan.supermario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    private Button LoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton=findViewById(R.id.loginLoginBtn);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnLoginButton();
            }
        });

    }
    public void OnLoginButton(){
        Intent intent=new Intent(getApplicationContext(),WelcomeScreenActivity.class);
        startActivityForResult(intent,0);
    }
}
