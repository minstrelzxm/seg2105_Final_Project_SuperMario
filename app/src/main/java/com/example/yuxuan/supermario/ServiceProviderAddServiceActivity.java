package com.example.yuxuan.supermario;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import java.util.ArrayList;
import java.util.List;

/*
We are going to use Lab5 productCatalog code as our source code
 */


public class ServiceProviderAddServiceActivity extends AppCompatActivity {
    Button buttonAddService;
    ListView listViewServices;

    Intent intentss;
    List<Service> services;
    DatabaseReference databaseServices;
    DatabaseReference databaseProviderService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_add_service);

        //FirebaseOptions options = new FirebaseOptions.Builder().setApplicationId("1:361969841457:android:3614f17d290290cc").setApiKey("AIzaSyA4IKqllj3j0Bv1gjDjIrlQBIS8AYSuasE").setDatabaseUrl("https://seg2105finalprojectsupermario.firebaseio.com").build();
        //FirebaseApp.initializeApp(this,options,"Services");
        //FirebaseApp secondary = FirebaseApp.getInstance("Services");
        //databaseServices = FirebaseDatabase.getInstance(secondary).getReference("Services");
        databaseServices = FirebaseDatabase.getInstance().getReference("Services");
        databaseProviderService = FirebaseDatabase.getInstance().getReference("ProviderServices");
        listViewServices = (ListView)findViewById(R.id.listViewServices);
        intentss = getIntent();

        services = new ArrayList<>();

        //adding an onClickListener to the item in the item view list
        listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Service service = services.get(position);
                showUpdateDeleteDialog(service);

                return true;
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                services.clear();
                //update every Service Snapshot
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Log.d("DateInfo", snapshot.toString());

                    String serviceId =snapshot.child("serviceId").getValue(String.class);
                    String typeOfService =snapshot.child("typeOfService").getValue(String.class);
                    Double hourRate =snapshot.child("hourRate").getValue(Double.class);
                    Service service = new Service(serviceId,typeOfService,hourRate);
                    //Service service = snapshot.getValue(Service.class);
                    services.add(service);
                }

                ServiceList servicesAdapter = new ServiceList(ServiceProviderAddServiceActivity.this, services);
                listViewServices.setAdapter(servicesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseProviderService.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //update every Service Snapshot
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    //showing the service is updated or deleted
    private void showUpdateDeleteDialog(Service service){
        final Service service1 = service;
        final String sId = service.getServiceId();
        String serv = service.getTypeOfService();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.servicesprovider_services_add,null);
        dialogBuilder.setView(dialogView);
        final TextView textViewProductname = (TextView) dialogView.findViewById(R.id.textViewServices);
        textViewProductname.setText("Services Name:"+serv);
        final TextView textViewPrices = (TextView) dialogView.findViewById(R.id.textViewPrices);
        textViewPrices.setText("prices: "+service.getHourRate());
        final EditText editTextStart = (EditText) dialogView.findViewById(R.id.editTextStart);
        final EditText editTextEnd = (EditText) dialogView.findViewById(R.id.editTextEnd);
        final Button buttonUpdateService = (Button) dialogView.findViewById(R.id.buttonUpdateService);
        final Button buttonDeleteService = (Button) dialogView.findViewById(R.id.buttonDeleteService);
        buttonUpdateService.setEnabled(false);
        buttonDeleteService.setEnabled(false);
        final Button buttonAddtoserver = (Button) dialogView.findViewById(R.id.buttonAddtoService);
        final Spinner spinner = (Spinner) dialogView.findViewById(R.id.spinner_date) ;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        dialogBuilder.setTitle(serv);
        final AlertDialog alert = dialogBuilder.create();
        alert.show();


        buttonAddtoserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateService = editTextStart.getText().toString().trim();
                String[] strings = dateService.split(":");
                String dateEnd = editTextEnd.getText().toString().trim();
                String[] strings1 = dateEnd.split(":");
                try{
                    int one = Integer.parseInt(strings[0]);
                    int two = Integer.parseInt(strings[1]);
                    int three = Integer.parseInt(strings1[0]);
                    int four = Integer.parseInt(strings1[1]);
                    if(0<=one&&one<24&&0<=two&&two<60&&0<=three&&three<24&&0<=four&&four<60){
                        if(one<three||(one==three&two<four)){

                            String date = String.valueOf(spinner.getSelectedItem());
                            if(!TextUtils.isEmpty(dateService)||!TextUtils.isEmpty(dateEnd)){
                                try {
                                    addtoService(service1,dateService, dateEnd, date);
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                alert.dismiss();
                            }
                        }else{
                            throw new ArrayIndexOutOfBoundsException();
                        }
                    }

                } catch (ArrayIndexOutOfBoundsException e){
                    Toast.makeText(getApplicationContext(),"uncurrect number",Toast.LENGTH_LONG).show();
                } catch(IndexOutOfBoundsException e){
                    Toast.makeText(getApplicationContext(),"uncurrect format",Toast.LENGTH_LONG).show();
                } catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"uncurrect format",Toast.LENGTH_LONG).show();

                }
            }
        });
    }



    public void addtoService(Service service,String dateStart,String dateEnd, String date) throws UnsupportedEncodingException {
        //todo: unfinished
        String provider = intentss.getStringExtra("username");
        String[] dates = new String[]{date,dateStart,dateEnd};
        Log.d("adtoServices", provider.toString());
        Log.d("DateInfo", dates.toString());
        String reference = "ProviderServices/"+Sha1.hash(provider.toString());
        Log.d("DateInfo", reference);
        DatabaseReference dF = FirebaseDatabase.getInstance().getReference(reference);
        String ProviderID = dF.push().getKey();
        ProSer Proser = new ProSer(ProviderID,service,date, dateStart,dateEnd );

        dF.child(ProviderID).setValue(Proser);
        dF.child(ProviderID).push().setValue(Proser);




    }

}
