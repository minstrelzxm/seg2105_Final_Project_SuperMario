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
    MyAccountType types;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseAccounts = FirebaseDatabase.getInstance().getReference("Accounts");

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
                    if(Accountlogin(mail.getText().toString().trim(),password.getText().toString().trim())){
                        OnLoginButton();
                    }else {
                        Toast.makeText(getApplicationContext(),"uncurrent password",Toast.LENGTH_LONG).show();
                    }
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
        accounts = new ArrayList<>();
        databaseAccounts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                accounts.clear();
                for(DataSnapshot postSnapShot : dataSnapshot.getChildren()){
                    if (dataSnapshot.exists()) {
                        MyAccountType eee =postSnapShot.child("accountTypes").getValue(MyAccountType.class);
                        String user = postSnapShot.child("username").getValue(String.class);
                        String pas= postSnapShot.child("password").getValue().toString();
                        Account account = new Account(user,pas,eee);
                        accounts.add(account);
                    } else {
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public boolean Accountlogin(String username,String passwords){
        if(Accountlogin(username,passwords,MyAccountType.administrator)||Accountlogin(username,passwords,MyAccountType.serviceProviders)||Accountlogin(username,passwords,MyAccountType.homeOwners)){
            return true;
        }
        return false;
    }

    public boolean Accountlogin(String username,String passwords,MyAccountType myAccountType){
        Account account = new Account(username, passwords,myAccountType);
        Toast.makeText(getApplicationContext(),accounts.get(4).toString(),Toast.LENGTH_LONG).show();
        for(int i=0;i<accounts.size();i++){
            if(accounts.get(i).equals(account)){
                types = accounts.get(i).getAccountTypes();
                Toast.makeText(getApplicationContext(),"currect",Toast.LENGTH_LONG).show();
                return true;
            }
        }
        return false;
    }



    public void OnLoginButton(){
        Toast.makeText(this,"Login Successfully",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(getApplicationContext(),WelcomeScreenActivity.class);
        intent.putExtra("username",mail.getText().toString().trim());
        intent.putExtra("accounttype",types.toString().trim());
        intent.putExtra("pasword",password.getText().toString().trim());
        startActivityForResult(intent,0);

        /*TODO:pass account from firebase to WelcomeScreenActivity
            intent.putExtra()
        */
    }
}
