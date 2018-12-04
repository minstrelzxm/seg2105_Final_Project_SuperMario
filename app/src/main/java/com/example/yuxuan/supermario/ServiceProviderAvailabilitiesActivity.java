package com.example.yuxuan.supermario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class ServiceProviderAvailabilitiesActivity extends AppCompatActivity {

    private Button BackButton;
    TextView final_text;
    RadioGroup radioGroup;
    RadioButton radioButton;
    ArrayList<String> selection = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_availabilities);

        radioGroup=findViewById(R.id.radioGroup);
        final_text = findViewById(R.id.Test);
        final_text.setEnabled(false);

        BackButton=findViewById(R.id.BackButton);
        // jump to ChooseAccountTypeActivity
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnBackButton();
            }
        });

    }
    public void OnBackButton(){
        Intent intent=new Intent(getApplicationContext(),ServiceProviderMainPage.class);
        startActivityForResult(intent,0);
    }

    public void finalSelection(View view){

        String final_time_selection="";

        for(String Selections: selection){
            final_time_selection = final_time_selection + Selections + "\n";
        }
        final_text.setText(radioButton.getText()+ "\n" +final_time_selection);
        final_text.setEnabled(true);

        Intent intent = new Intent(ServiceProviderAvailabilitiesActivity.this, ServiceProviderMainPage.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)selection);;
        args.putSerializable("Day",(Serializable)radioButton.getText());;

        intent.putExtra("BUNDLE", args);

        startActivity(intent);
    }

    public void selectTime(View view){

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()){
            case R.id.checkBoxEightToTen:
                if (checked){
                    selection.add("8am to 10am");
                }
                else{
                    selection.remove("8am to 10am");
                }
                break;
        }
        switch (view.getId()){
            case R.id.checkBoxTenToTweleve:
                if (checked){
                    selection.add("10am to 12am");
                }
                else{
                    selection.remove("10am to 12am");
                }
                break;
        }
        switch (view.getId()){
            case R.id.checkBoxTwelveToTwo:
                if (checked){
                    selection.add("12am to 2pm");
                }
                else{
                    selection.remove("12am to 2pm");
                }
                break;
        }
        switch (view.getId()){
            case R.id.checkBoxTwoToFour:
                if (checked){
                    selection.add("2pm to 4pm");
                }
                else{
                    selection.remove("2pm to 4pm");
                }
                break;
        }
        switch (view.getId()){
            case R.id.checkBoxFourToSix:
                if (checked){
                    selection.add("4pm to 6pm");
                }
                else{
                    selection.remove("4pm to 6pm");
                }
                break;
        }
        switch (view.getId()){
            case R.id.checkBoxSixToEight:
                if (checked){
                    selection.add("6pm to 8pm");
                }
                else{
                    selection.remove("6pm to 8pm");
                }
                break;
        }

    }
    public void selectDay(View view){
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton=findViewById(radioId);

        Toast.makeText(this, "Selected Radio Button"+radioButton.getText(),Toast.LENGTH_SHORT).show();

    }

}
