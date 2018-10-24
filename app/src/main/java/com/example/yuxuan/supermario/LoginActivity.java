package com.example.yuxuan.supermario;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private Button LoginButton;
    EditText mail, password;
    DatabaseReference databaseAccounts;
    List<Account> accounts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseAccounts = FirebaseDatabase.getInstance().getReference("Provider_Accounts").getDatabase().getReference("Account");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accounts = new ArrayList<>();
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

    @Override
    protected void onStart() {
        super.onStart();
        databaseAccounts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                accounts.clear();
                Toast.makeText(getApplicationContext(),"one",Toast.LENGTH_LONG).show();
                for(DataSnapshot postSnapShot : dataSnapshot.getChildren()){
                    Toast.makeText(getApplicationContext(),"account1",Toast.LENGTH_LONG).show();
                    if (dataSnapshot.exists()) {

                        Account account = postSnapShot.getValue(Account.class);
                        Toast.makeText(getApplicationContext(),"account1",Toast.LENGTH_LONG).show();
                        //Toast.makeText(getApplicationContext(),account.toString(),Toast.LENGTH_LONG).show();
                        accounts.add(account);
                    } else {
                        Toast.makeText(getApplicationContext(),"no account",Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
