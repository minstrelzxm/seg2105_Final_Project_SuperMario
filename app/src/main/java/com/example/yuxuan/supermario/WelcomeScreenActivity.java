package com.example.yuxuan.supermario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class WelcomeScreenActivity extends AppCompatActivity {


    String username;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

<<<<<<< HEAD
=======
        adminBtn=findViewById(R.id.welcomeAdminButton);
        // jump to AdminPageActivity
        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminBtn();
            }
        });
//        providerBtn=findViewById(R.id.welcomeProviderButton);
//        // jump to ChooseAccountTypeActivity
//        providerBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                providerBtn();
//            }
//        });
//
//        ownerBtn=findViewById(R.id.welcomeOwnerButton);
//        // jump to ChooseAccountTypeActivity
//        ownerBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                OwnerBtn();
//            }
//        });

<<<<<<< HEAD
>>>>>>> 97578b9b1bc97b2c01003e6d676dfa28d59e300e
=======
>>>>>>> 97578b9b1bc97b2c01003e6d676dfa28d59e300e
        //TODO: set userRole and userName to its corresponding type
        TextView err = (TextView)findViewById(R.id.welcomeTextViewUserName);
        TextView kkk = (TextView)findViewById(R.id.welcomeTextViewUserRole);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("accounttype");
        kkk.setText(password);
        err.setText(username);
    }
<<<<<<< HEAD
=======
    //TODO: provider to provider page;

    //Using intent to jump to create_provider_account
//    public void providerBtn() {
//        Intent intent = new Intent(getApplicationContext(), create_provider_account.class);
//        startActivityForResult(intent, 0);
//    }
    //TODO: Owner to owner page;

    //Using intent to jump to create_owner_account
//    public void OwnerBtn() {
//        Intent intent = new Intent(getApplicationContext(), create_owner_account.class);
//        startActivityForResult(intent, 0);
//    }




>>>>>>> 97578b9b1bc97b2c01003e6d676dfa28d59e300e
}
