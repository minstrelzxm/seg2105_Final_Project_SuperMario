package com.example.yuxuan.supermario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private Button LoginButton;
    EditText mail, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mail=(EditText)findViewById(R.id.loginTextUsernameInput);
        password=(EditText)findViewById(R.id.loginTextUserPasswordInput);

        LoginButton=findViewById(R.id.loginLoginBtn);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String validate=

                        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+";

                String email=mail.getText().toString();
                Matcher matcher= Pattern.compile(validate).matcher(email);

                if(matcher.matches()){
                    Toast.makeText(getApplicationContext(),"Valid Email",Toast.LENGTH_LONG).show();
                    OnLoginButton();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invaild Email",Toast.LENGTH_LONG).show();
                    /*
                    Intent intent=new Intent(getApplicationContext(),WelcomeScreenActivity.class);
                    intent.putExtra("username",mail.getText().toString().trim());
                    intent.putExtra("pasword",password.getText().toString().trim());
                    startActivityForResult(intent,0);*/
                }
                if (password.getText().toString().equals("")){
                    password.setError("Enter correct password");
                }

            }
        });


    }
    public void OnLoginButton(){
        Toast.makeText(this,"Login Successfully",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(getApplicationContext(),WelcomeScreenActivity.class);
        intent.putExtra("username",mail.getText().toString().trim());
        intent.putExtra("pasword",password.getText().toString().trim());
        startActivityForResult(intent,0);

        /*TODO:pass account from firebase to WelcomeScreenActivity
            intent.putExtra()
        */
    }
}
