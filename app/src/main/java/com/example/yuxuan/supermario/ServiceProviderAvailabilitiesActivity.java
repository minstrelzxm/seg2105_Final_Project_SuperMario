package com.example.yuxuan.supermario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;

public class ServiceProviderAvailabilitiesActivity extends AppCompatActivity {

    ArrayList<String> selection = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_availabilities);
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
