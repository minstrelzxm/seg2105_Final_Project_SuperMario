package com.example.yuxuan.supermario;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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

public class HomeOwnerMainPageActivity extends AppCompatActivity {

    List<ProSer> services;
    ArrayAdapter<String> adapter;
    DatabaseReference databaseProvideService;

    private EditText mSearchField;
    private Button mSearchBtn;
    private Button Search;
    private Button TSearchBtn;
    ListView listViewServices;
    private RecyclerView mResultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_main_page);
        listViewServices = (ListView)findViewById(R.id.listviewLis);

        mSearchField = (EditText)findViewById(R.id.homeOwnerMainSearchField);
        mSearchBtn = (Button) findViewById(R.id.homeOwnerMainSearchByTypeBtn);
        TSearchBtn = (Button) findViewById(R.id.homeOwnerMainSearchByTimeBtn);
        mResultView = (RecyclerView) findViewById(R.id.homeOwnerMainResultList);
        Search = (Button) findViewById(R.id.button3);
        TSearchBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("updateService", services.toString());
                //Add an judgment
                TSearchBtn();
            }
        });
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add an judgment
                //String provider = mSearchField.getText().toString();
                String provider = mSearchField.getText().toString();
                //
                String reference = null;
                try {
                    reference = "ProviderServices/"+Sha1.hash(provider);
                    Log.d("ProviderServices", reference);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                databaseProvideService = FirebaseDatabase.getInstance().getReference(reference);
                services = new ArrayList<>();
                databaseProvideService.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
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
                        ProSerList servicesAdapter = new ProSerList(HomeOwnerMainPageActivity.this, services);
                        listViewServices.setAdapter(servicesAdapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });


            }
        });

        listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ProSer service = services.get(position);
                showUpdateDeleteDialog(service);
                return true;
            }
        });
    }



    //showing the service is updated or deleted
    private void showUpdateDeleteDialog(ProSer service){

    }


    private void TSearchBtn(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.homeownermainsearchbytime,null);
        dialogBuilder.setView(dialogView);

        final EditText editTextStart = (EditText) dialogView.findViewById(R.id.editTextStart);
        final EditText editTextEnd = (EditText) dialogView.findViewById(R.id.editTextEnd);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.button);
        final Spinner spinner = (Spinner) dialogView.findViewById(R.id.spinner_date) ;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        dialogBuilder.setTitle("times update");
        final AlertDialog alert = dialogBuilder.create();
        alert.show();
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String StartTime = editTextStart.getText().toString().trim();
                String EndTime = editTextEnd.getText().toString().trim();
                String dateService = String.valueOf(spinner.getSelectedItem());
                if(!TextUtils.isEmpty(StartTime)){
                    updateService(dateService,StartTime,EndTime);
                    alert.dismiss();
                }
            }
        });

    }

    public void updateService(String date, String start, String end){
        List<ProSer> newDate = new ArrayList<>();;
        String[] starts = start.split(":");
        String[] ends = end.split(":");
        Log.d("updateService", services.toString());
        Log.d("updateService", String.valueOf(services.size()));

        for(int i=0;i<services.size();i++){
            Log.d("updateService", String.valueOf(i));
            if(services.get(i).getDate().equals(date)){
                Log.d("updateService", services.toString());
                Log.d("updateService", services.get(i).getStartTime());
                String[] starttimes = services.get(i).getStartTime().split(":");
                String[] endtimes = services.get(i).getEndTime().split(":");
                Log.d("updateService", starts[0]+starttimes[0]);
                if((Integer.parseInt(starts[0])<Integer.parseInt(starttimes[0]))|| (Integer.parseInt(starts[0])==Integer.parseInt(starttimes[0])&&(Integer.parseInt(starts[1])<=Integer.parseInt(starttimes[1])))){
                        if ((Integer.parseInt(endtimes[0]) < Integer.parseInt(ends[0])) || (Integer.parseInt(endtimes[0])==Integer.parseInt(ends[0])&&(Integer.parseInt(endtimes[1])<=Integer.parseInt(ends[1])))){
                                newDate.add(services.get(i));
                        }
                }
            }

        }
        ProSerList servicesAdapter = new ProSerList(HomeOwnerMainPageActivity.this, newDate);
        Log.d("updateService", newDate.size()+newDate.toString());

        listViewServices.setAdapter(servicesAdapter);

    }

}
