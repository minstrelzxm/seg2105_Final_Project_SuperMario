package com.example.yuxuan.supermario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;

public class ServiceProviderAvailabilitiesActivity extends AppCompatActivity {

//    private Button EneterButton;
    TextView final_text;
    ArrayList<String> selection = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_availabilities);
        final_text = findViewById(R.id.Test);
        final_text.setEnabled(false);

//        EneterButton=findViewById(R.id.EnterButton);
//        // jump to ChooseAccountTypeActivity
//        EneterButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                OnEntereButton();
//            }
//        });
//
//    }
//    public void OnEntereButton(){
//        Intent intent=new Intent(getApplicationContext(),ServiceProvider.class);
//        startActivityForResult(intent,0);
    }

    public void finalSelection(View view){

        String final_time_selection="";

        for(String Selections: selection){
            final_time_selection = final_time_selection + Selections + "\n";
        }
        final_text.setText(final_time_selection);
        final_text.setEnabled(true);
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
                    selection.add("12 to 2");
                }
                else{
                    selection.remove("12 to 2");
                }
                break;
        }
        switch (view.getId()){
            case R.id.checkBoxTwoToFour:
                if (checked){
                    selection.add("2 to 4");
                }
                else{
                    selection.remove("2 to 4");
                }
                break;
        }
        switch (view.getId()){
            case R.id.checkBoxFourToSix:
                if (checked){
                    selection.add("4 to 6");
                }
                else{
                    selection.remove("4 to 6");
                }
                break;
        }
        switch (view.getId()){
            case R.id.checkBoxSixToEight:
                if (checked){
                    selection.add("6 to 8");
                }
                else{
                    selection.remove("6 to 8");
                }
                break;
        }

    }
    public void selectDay(View view){

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()){
            case R.id.RadioButtonMon:
                if (checked){
                      selection.add("Monday");
                }
                else{
                    selection.remove("Monday");
                }
                break;
        }
        switch (view.getId()){
            case R.id.RadioButtonTue:
                if (checked){
                    selection.add("Tuesday");
                }
                else{
                    selection.remove("Tuesday");
                }
                break;
        }
        switch (view.getId()){
            case R.id.RadioButtonWed:
                if (checked){
                    selection.add("Wednesday");
                }
                else{
                    selection.remove("Wednesday");
                }
                break;
        }
        switch (view.getId()){
            case R.id.RadioButtonThurs:
                if (checked){
                    selection.add("Thursday");
                }
                else{
                    selection.remove("Thursday");
                }
                break;
        }
        switch (view.getId()){
            case R.id.RadioButtonFri:
                if (checked){
                    selection.add("Friday");
                }
                else{
                    selection.remove("Friday");
                }
                break;
        }
        switch (view.getId()){
            case R.id.RadioButtonSat:
                if (checked){
                    selection.add("Saturday");
                }
                else{
                    selection.remove("Saturday");
                }
                break;
        }
        switch (view.getId()){
            case R.id.RadioButtonSun:
                if (checked){
                    selection.add("Sunday");
                }
                else{
                    selection.remove("Sunday");
                }
                break;
        }

    }

    public void selection(View view){
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()){
            case R.id.checkBoxEightToTen:
                if(checked)
                selection.add("8-10AM");
                else{
                    selection.remove("8-10AM");
                }
                break;
            case R.id.checkBoxTenToTweleve:
                if(checked)
                    selection.add("10-12AM");
                else{
                    selection.remove("10-12AM");
                }
                break;
            case R.id.checkBoxTwelveToTwo:
                if(checked)
                    selection.add("12-2PM");
                else{
                    selection.remove("12-2PM");
                }
                break;
            case R.id.checkBoxTwoToFour:
                if(checked)
                    selection.add("2-4PM");
                else{
                    selection.remove("2-4PM");
                }
                break;
            case R.id.checkBoxFourToSix:
                if(checked)
                    selection.add("4-6PM");
                else{
                    selection.remove("4-6PM");
                }
                break;
            case R.id.checkBoxSixToEight:
                if(checked)
                    selection.add("6-8PM");
                else{
                    selection.remove("6-8PM");
                }
                break;
        }
    }
}
