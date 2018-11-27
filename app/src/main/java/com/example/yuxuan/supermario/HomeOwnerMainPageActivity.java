package com.example.yuxuan.supermario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

public class HomeOwnerMainPageActivity extends AppCompatActivity {

    private EditText mSearchField;
    private Button mSearchBtn;

    private RecyclerView mResultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_main_page);

        mSearchField = (EditText)findViewById(R.id.homeOwnerMainSearchField);
        mSearchBtn = (Button) findViewById(R.id.homeOwnerMainSearchBtn);
        mResultView = (RecyclerView) findViewById(R.id.homeOwnerMainResultList);
    }
}
