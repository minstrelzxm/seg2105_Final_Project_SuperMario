package com.example.yuxuan.supermario;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.UnsupportedEncodingException;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ServiceProviderMainPage extends AppCompatActivity {

    private Button spInfoBtn;
    private Button spAddServiceBtn;
    private Button spAvailabilitiesBtn;
    DatabaseReference databaseProviderService;
    ListView listViewServices;
    Intent intentss;
    String username;
    List<ProSer> services;
    ArrayAdapter<String> adapter;
    ArrayList myList = new ArrayList();
    ArrayList<String> timeList = new ArrayList<>();
    String day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_main_page);
        intentss = getIntent();
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        String provider = intentss.getStringExtra("username");
        String reference = null;
        try {
            reference = "ProviderServices/"+Sha1.hash(provider.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        databaseProviderService = FirebaseDatabase.getInstance().getReference(reference);
        services = new ArrayList<>();

        listViewServices = (ListView)findViewById(R.id.serviceProviderServiceList);

        //adding an onClickListener to the item in the item view list
        listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ProSer service = services.get(position);
                showUpdateDeleteDialog(service);

                return true;
            }
        });

        spInfoBtn = (Button)findViewById(R.id.serviceProviderMainInfoBtn);
        spAddServiceBtn = (Button)findViewById(R.id.serviceProviderMainAddServiceBtn);
        spAvailabilitiesBtn = (Button)findViewById(R.id.serviceProviderMainAvailabtn);

        ListView timeslotlistview = (ListView) findViewById(R.id.serviceProviderTimeSlot);

        intentss = getIntent();
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");

        if (args != null) {
            timeList = (ArrayList<String>) args.getSerializable("ARRAYLIST");
            day = (String)args.getSerializable("Day");
            myList.add(day);
            myList.add(timeList);

        }
        username = intent.getStringExtra("username");

        spInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoJump();
            }
        });

        spAddServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addServiceJump();
            }
        });

        spAvailabilitiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                availJump();
            }
        });

        if (timeList != null) {
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    myList);
//        setListAdapter(adapter);
            timeslotlistview.setAdapter(adapter);
        }

    }

    public void onStart() {
        super.onStart();

        databaseProviderService.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                services.clear();
                //update every Service Snapshot
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){


                    String date =snapshot.child("date").getValue(String.class);
                    String endTime =snapshot.child("endTime").getValue(String.class);
                    String startTime =snapshot.child("startTime").getValue(String.class);
                    String proID =snapshot.child("proID").getValue(String.class);

                    String serviceId =snapshot.child("serID").child("serviceId").getValue(String.class);
                    String typeOfService =snapshot.child("serID").child("typeOfService").getValue(String.class);
                    Double hourRate =snapshot.child("serID").child("hourRate").getValue(Double.class);
                    Service servicess = new Service(serviceId,typeOfService,hourRate);
                    Log.d("DateInfo", snapshot.toString());

                    ProSer service = new ProSer(proID,servicess,date,startTime,endTime);
                    services.add(service);
                }
                ProSerList servicesAdapter = new ProSerList(ServiceProviderMainPage.this, services);
                listViewServices.setAdapter(servicesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void infoJump(){
        Intent intent = new Intent(getApplicationContext(), ServiceProviderInfoPageActivity.class);
        startActivityForResult(intent, 0);

    }

    public void addServiceJump(){
        Intent intent = new Intent(getApplicationContext(), ServiceProviderAddServiceActivity.class);
        intent.putExtra("username",intentss.getStringExtra("username"));
        startActivityForResult(intent, 0);

    }

    public void availJump(){
        Intent intent = new Intent(getApplicationContext(), ServiceProviderAvailabilitiesActivity.class);
        startActivityForResult(intent, 0);

    }

    //showing the service is updated or deleted
    private void showUpdateDeleteDialog(ProSer service){
        final Service service1 = service.getSerID();
        final String sId = service.getProID();
        String serv = service1.getTypeOfService();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.servicesprovider_services_add,null);
        dialogBuilder.setView(dialogView);

        final TextView textViewProductname = (TextView) dialogView.findViewById(R.id.textViewServices);
        textViewProductname.setText("Services Name:"+serv);
        final TextView textViewPrices = (TextView) dialogView.findViewById(R.id.textViewPrices);
        final Button buttonAddtoserver = (Button) dialogView.findViewById(R.id.buttonAddtoService);
        buttonAddtoserver.setEnabled(false);
        textViewPrices.setText("prices: "+service1.getHourRate());
        final EditText editTextStart = (EditText) dialogView.findViewById(R.id.editTextStart);
        final EditText editTextEnd = (EditText) dialogView.findViewById(R.id.editTextEnd);
        final Button buttonUpdateService = (Button) dialogView.findViewById(R.id.buttonUpdateService);
        final Button buttonDeleteService = (Button) dialogView.findViewById(R.id.buttonDeleteService);
        final Spinner spinner = (Spinner) dialogView.findViewById(R.id.spinner_date) ;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        dialogBuilder.setTitle(serv);
        final AlertDialog alert = dialogBuilder.create();
        alert.show();

        buttonUpdateService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String StartTime = editTextStart.getText().toString().trim();
                String EndTime = editTextEnd.getText().toString().trim();
                String dateService = editTextStart.getText().toString().trim();
                if(!TextUtils.isEmpty(StartTime)){
                    updateService(sId,dateService,StartTime,EndTime);
                    alert.dismiss();
                }
            }
        });

        buttonDeleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteService(sId);
                alert.dismiss();
            }
        });
    }

    private void updateService(String sId, String dateService, String StartTime,String EndTime) {
        DatabaseReference dR = databaseProviderService.child(sId);
        dR.child("date").setValue(dateService);
        dR.child("startTime").setValue(StartTime);
        dR.child("endTime").setValue(EndTime);
        Toast.makeText(getApplicationContext(), "Services Updated", Toast.LENGTH_LONG).show();
    }

    //Delete a service

    private void deleteService(String sId) {
        DatabaseReference dR = databaseProviderService.child(sId);
        //Product product = new Product(id, name, price);
        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Services Deleted", Toast.LENGTH_LONG).show();
        //return true;
    }
}


