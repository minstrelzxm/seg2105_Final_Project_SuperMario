package com.example.yuxuan.supermario;

/*
Create an admin account and push it to Firebase.
 */

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


public class CreateAccountActivity extends AppCompatActivity {
    private Button BackMainButton;
    private FirebaseDatabase mRef;

    DatabaseReference databaseAccounts;
    EditText createAccAccName;
    EditText createAccAccPassword;
    EditText createAccReAccPassword;
    MyAccountType types = MyAccountType.administrator;
    Button buttonAddAccount;
    ListView listViewAccounts;

    List<Account> accounts;

    protected void onCreate(Bundle savedInstanceState) {
        databaseAccounts = FirebaseDatabase.getInstance().getReference("Accounts");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        createAccAccName = (EditText) findViewById(R.id.createAccAccName);
        createAccAccPassword = (EditText) findViewById(R.id.createAccAccPassword);
        createAccReAccPassword = (EditText) findViewById(R.id.createAccReAccPassword);
        //types = (MyAccountType) findViewById(R.id.)
        //buttonAddAccount = (Button) findViewById(R.id.createAccCreateBtn);

        accounts = new ArrayList<>();

        /*TODO: If we already has an admin account we need to tell user that
            TODO: he cannot create another admin account.
        */

        BackMainButton=(Button)findViewById(R.id.createAccCreateBtn);

        //adding an onclicklistener to button
        BackMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: might need a validation when creating account
                //TODO: an correct email address.? also for provider and owner.
                if(createAccAccPassword.getText().toString().equals(createAccReAccPassword.getText().toString())){
                    if(checkEmail()){
                        if(AccountCheck(createAccAccPassword.getText().toString(),createAccReAccPassword.getText().toString())){
                            addAccount();
                            OnBackMainActivity();
                        }
                        else{
                            OnBackMainActivityFailEmail();
                        }
                    }
                    else{
                        OnBackMainActivityFailEmail();
                    }
                }
                else{
                    OnBackMainActivityFail();
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

    // successfully created an account and return to main activity.
    public void OnBackMainActivity(){
        Toast.makeText(this,"Successfully created an Account",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivityForResult(intent,0);
    }

    // Failed to created an Account.
    public void OnBackMainActivityFail(){
        Toast.makeText(this,"Fail to created an Account",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(getApplicationContext(),ChooseAccountTypeActivity.class);
        startActivityForResult(intent,0);
    }

    public void OnBackMainActivityFailEmail(){
        Toast.makeText(this,"Invaild Email",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(getApplicationContext(),ChooseAccountTypeActivity.class);
        startActivityForResult(intent,0);
    }

    // Add account to firebase
    private void addAccount() {

        //Toast.makeText(this, "NOT IMPLEMENTED YET", Toast.LENGTH_LONG).show();
        String username = createAccAccName.getText().toString().trim();
        String password = createAccAccPassword.getText().toString().trim();

        if(!TextUtils.isEmpty(username)){
            String id = databaseAccounts.push().getKey();
            //Update the empty account we created before.
            Account account = new Account(username,password,types);

            databaseAccounts.child(id).setValue(account);
            createAccAccName.setText("");
            createAccAccPassword.setText("");
            Toast.makeText(this, "Account added", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_LONG).show();
        }
    }

    //Update Account
    private void updateAccount(String username, String password) {

        //Toast.makeText(getApplicationContext(), "NOT IMPLEMENTED YET", Toast.LENGTH_LONG).show();
        DatabaseReference dA = FirebaseDatabase.getInstance().getReference("Accounts").child(username);
        Account product = new Account(username, password, types);
        dA.setValue(product);
        Toast.makeText(getApplicationContext(),"Account Updated", Toast.LENGTH_LONG).show();
    }

    //delete Account
    private boolean deleteAccount(String id) {

        //Toast.makeText(getApplicationContext(), "NOT IMPLEMENTED YET", Toast.LENGTH_LONG).show();
        DatabaseReference dA = FirebaseDatabase.getInstance().getReference("Accounts").child(id);
        dA.removeValue();
        Toast.makeText(getApplicationContext(),"Account Delected",Toast.LENGTH_LONG).show();
        return true;
    }
    private boolean checkEmail(){
        String validate =

                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+";

        String email = createAccAccName.getText().toString();
        Matcher matcher = Pattern.compile(validate).matcher(email);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean AccountCheck(String username,String passwords,MyAccountType myAccountType){
        Account account = new Account(username, passwords,myAccountType);
        Toast.makeText(getApplicationContext(),accounts.get(4).toString(),Toast.LENGTH_LONG).show();
        for(int i=0;i<accounts.size();i++){
            if(accounts.get(i).equals(account)){
                Toast.makeText(getApplicationContext(),"Cannot use same email",Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return true;
    }
    public boolean AccountCheck(String username,String passwords){
        if(AccountCheck(username,passwords,MyAccountType.administrator)||AccountCheck(username,passwords,MyAccountType.serviceProviders)||AccountCheck(username,passwords,MyAccountType.homeOwners)){
            return true;
        }
        return false;
    }
    
}
