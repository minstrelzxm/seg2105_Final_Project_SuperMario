package com.example.yuxuan.supermario;

/*
We are going to use Lab5 productCatalog code as our source code
 */
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminPageActivity extends AppCompatActivity {

    EditText editTextService;
    EditText editTextHourRate;
    Button buttonAddService;
    ListView listViewServices;

    List<Service> services;
    DatabaseReference databaseServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        databaseServices = FirebaseDatabase.getInstance().getReference("services");

        editTextService = (EditText)findViewById(R.id.editTextService);
        editTextHourRate = (EditText)findViewById(R.id.editTextHourRate);
        listViewServices = (ListView)findViewById(R.id.listViewServices);
        buttonAddService = (Button)findViewById(R.id.addButton);

        services = new ArrayList<>();

        //adding an onClickListener to button
        buttonAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addService();
            }
        });

        //adding an onClickListener to the item in the item view list
        listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Service service = services.get(position);
                showUpdateDeleteDialog(service.getServiceId(),service.getTypeOfService());

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
                    Service service = snapshot.getValue(Service.class);
                    services.add(service);
                }

                ServiceList servicesAdapter = new ServiceList(AdminPageActivity.this, services);
                listViewServices.setAdapter(servicesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    //TODO:create a service in create button //lab5
    //TODO:delete a service in delete button //lab5
    //TODO:edit a service in update button //lab5
    //Adding service to the item view list
    private void addService(){
        String serviceName = editTextService.getText().toString().trim();
        double hourRate = Double.parseDouble(String.valueOf(editTextHourRate.getText().toString()));

        if(!TextUtils.isEmpty(serviceName)){
            String serviceID = databaseServices.push().getKey();
            Service service = new Service(serviceID,serviceName,hourRate);
            databaseServices.child(serviceID).setValue(service);
            editTextService.setText("");
            editTextHourRate.setText("");
            Toast.makeText(this,"Service added",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"Please enter a service",Toast.LENGTH_LONG).show();
        }
    }

    //showing the service is updated or deleted
    private void showUpdateDeleteDialog(final String sId, String serv){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_admin_update,null);
        dialogBuilder.setView(dialogView);

        final EditText editTextService = (EditText) dialogView.findViewById(R.id.editTextService);
        final EditText editTextHourRate = (EditText) dialogView.findViewById(R.id.editTextHourRate);
        final Button buttonUpdateService = (Button) dialogView.findViewById(R.id.buttonUpdateService);
        final Button buttonDeleteService = (Button) dialogView.findViewById(R.id.buttonDeleteService);

        dialogBuilder.setTitle(serv);
        final AlertDialog alert = dialogBuilder.create();
        alert.show();

        buttonUpdateService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serviceName = editTextService.getText().toString().trim();
                double serviceHourRate = Double.parseDouble(String.valueOf(editTextHourRate.getText().toString()));
                if(!TextUtils.isEmpty(serviceName)){
                    updateService(sId,serviceName,serviceHourRate);
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

    //Updating a service, mostly the price
    private void updateService(String sId, String serv, double price){

    }

    //Delete a service
    private void deleteService(String sId){

    }


}
