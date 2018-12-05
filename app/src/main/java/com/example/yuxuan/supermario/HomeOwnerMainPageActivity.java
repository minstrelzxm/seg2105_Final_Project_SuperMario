package com.example.yuxuan.supermario;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
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
import android.widget.RatingBar;
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

import static java.lang.Thread.sleep;

public class HomeOwnerMainPageActivity extends AppCompatActivity {

    List<ProSer> services;
    ArrayAdapter<String> adapter;
    DatabaseReference databaseProvideService;
    DatabaseReference databaseRatee;
    rate ratesss;

    private EditText mSearchField;
    private Button mSearchBtn;
    private Button Search;
    private Button TSearchBtn;
    Button Rbutton;
    ListView listViewServices;
    DatabaseReference databaseProviderService;
    String username;
    ArrayList Lrate = new ArrayList();
    ArrayList myList = new ArrayList();
    ArrayList<String> timeList = new ArrayList<>();
    Intent intens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_main_page);
        intens = getIntent();

        listViewServices = (ListView)findViewById(R.id.listviewLis);
        listViewServices.setAdapter(adapter);

        //adding an onItemLongClickListener to the item in the item view list
        listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ProSer service = services.get(position);
                try {
                    databaseRatee = FirebaseDatabase.getInstance().getReference("rate/"+Sha1.hash(service.getProID().toString()));
                    final rate[] rate1 = new rate[1];
                    ratesss = new rate(5,1);;
                    databaseRatee.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                double rate;
                                                                if(dataSnapshot.child("rate").child("rate").getValue(double.class)!=null){
                                                                    rate = dataSnapshot.child("rate").child("rate").getValue(double.class);
                                                                    Log.d("raterate", String.valueOf(rate));
                                                                    int man = dataSnapshot.child("rate").child("man").getValue(int.class);
                                                                    Log.d("rateman", String.valueOf(man));
                                                                    rate1[0] = new rate(rate,man);
                                                                    ratesss = new rate(rate,man);
                                                                    Log.d("ratesss4", ratesss.toString());

                                                                } else{
                                                                    ratesss = new rate(5,1);
                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(DatabaseError databaseError) {
                                                                databaseRatee.child("rate").child("rate").setValue(5);
                                                                databaseRatee.child("rate").child("man").setValue(5);

                                                            }


                                                        }
                    );
                    Log.d("ratesss1", ratesss.toString());
                    //databaseRatee.child("value").setValue("1");
                    //databaseRatee.child("value").removeValue();
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(rate1[0]==null){
                        rate1[0] = new rate(5,1);
                    }
                    Log.d("ratesss2", ratesss.toString());
                    ratingDialog(service, rate1[0]);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });


        mSearchField = (EditText)findViewById(R.id.homeOwnerMainSearchField);
        mSearchBtn = (Button) findViewById(R.id.homeOwnerMainSearchByTypeBtn);
        TSearchBtn = (Button) findViewById(R.id.homeOwnerMainSearchByTimeBtn);
        Rbutton = (Button) findViewById(R.id.homeOwnerMainSearchByRateBtn);
        Search = (Button) findViewById(R.id.button3);

        mSearchBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String Service_name = mSearchField.getText().toString();
                TypeSear(Service_name);
            }
        }

        );

        Rbutton.setOnClickListener(new View.OnClickListener() {

                                          @Override
                                          public void onClick(View v) {
                                              String Service_name = mSearchField.getText().toString();

                                              String[] strings = Service_name.split("-");
                                              Rate(Double.parseDouble(strings[0]),Double.parseDouble(strings[1]));
                                          }
                                      }

        );

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
                reference = "ProviderServices/";
                Log.d("ProviderServices", reference);
                databaseProvideService = FirebaseDatabase.getInstance().getReference(reference);
                services = new ArrayList<>();
                databaseProvideService.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        services.clear();
                        //update every Service Snapshot
                        for (DataSnapshot snapshot2 : dataSnapshot.getChildren()) {
                            Log.d("ProviderServices", snapshot2.toString());
                            for (DataSnapshot snapshot : snapshot2.getChildren()) {
                                Log.d("ProviderServices", snapshot.toString());

                                String date = snapshot.child("date").getValue(String.class);
                                String endTime = snapshot.child("endTime").getValue(String.class);
                                String startTime = snapshot.child("startTime").getValue(String.class);
                                String proID = snapshot.child("proID").getValue(String.class);

                                String serviceId = snapshot.child("serID").child("serviceId").getValue(String.class);
                                String typeOfService = snapshot.child("serID").child("typeOfService").getValue(String.class);
                                Double hourRate = snapshot.child("serID").child("hourRate").getValue(Double.class);
                                Service servicess = new Service(serviceId, typeOfService, hourRate);
                                Log.d("DateInfo", snapshot.toString());

                                ProSer service = new ProSer(proID, servicess, date, startTime, endTime);
                                services.add(service);
                            }

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





    }
    //ratingDialog method
    private void ratingDialog(ProSer service, final rate rate) {
        final String ids = service.getProID();

        Log.d("ratesss3", ratesss.toString());
        final Service service1 = service.getSerID();
        final String sId = service.getProID();
        String serv = service1.getTypeOfService();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.home_owner_rate_book,null);
        dialogBuilder.setView(dialogView);
        final TextView display = (TextView) dialogView.findViewById(R.id.textView14);
        final RatingBar ratingRatingBar = (RatingBar) dialogView.findViewById(R.id.ratingBar);
            final Button homeOwnerBookBtn = (Button) dialogView.findViewById(R.id.homeOwnerBookBtn);
            final Button homeOwnerRateBtn = (Button) dialogView.findViewById(R.id.homeOwnerRateBtn);
            Button RateButton = (Button) dialogView.findViewById(R.id.homeOwnerRateBtn);
        final TextView rateingDisplayTextview = (TextView) dialogView.findViewById(R.id.textViewRateValue);
        Button BookButton = (Button) dialogView.findViewById(R.id.homeOwnerBookBtn);
        display.setText(ratesss.toString());
        Log.d("DateInfo", rate.toString());

            RateButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Log.d("ratesss5", ratesss.toString());
                    ratesss.setRate(ratesss.getRate()+ratingRatingBar.getRating());
                    ratesss.setMan(ratesss.getMan()+1);
                    ratesss.setId(ids);
                    display.setText(ratesss.toString());

                    databaseRatee.child("rate").setValue(ratesss);
                    rateingDisplayTextview.setText("Your rating is: "+ ratingRatingBar.getRating());
                }
            });
        BookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeOwnerMainPageActivity.this, "Service booked!", Toast.LENGTH_SHORT).show();
                BookJump();

            }

            private void BookJump() {
                Intent intent = new Intent(getApplicationContext(),HomeOwnerBookedPageActivity.class);
                startActivityForResult(intent,0);
            }
        });

        dialogBuilder.setTitle(serv);
        final AlertDialog alert = dialogBuilder.create();
        alert.show();

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
        List<ProSer> newDate = new ArrayList<>();
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
    public void TypeSear(String Service_name){
        List<ProSer> newDate = new ArrayList<>();
        for(int i=0;i<services.size();i++){
            Service service= services.get(i).getSerID();
            if(service.getTypeOfService().indexOf(Service_name)!=-1){
                newDate.add(services.get(i));
            }

        }
        ProSerList servicesAdapter = new ProSerList(HomeOwnerMainPageActivity.this, newDate);
        Log.d("updateService", newDate.size()+newDate.toString());

        listViewServices.setAdapter(servicesAdapter);

    }

    public void Rate(final double getrates, final double endrate){
        databaseProviderService = FirebaseDatabase.getInstance().getReference("rate");
        databaseProviderService.addValueEventListener(new ValueEventListener() {

            @SuppressLint("LongLogTag")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<ProSer> newDate = new ArrayList<>();
                List<String> newrate = new ArrayList<>();
                for (DataSnapshot snapshot2 : dataSnapshot.getChildren()) {
                    for (int i = 0; i < services.size(); i++) {
                        String service = services.get(i).getProID();
                        Log.d("updateServiceRateservice", service);
                        Log.d("updateServiceRatesnapshot2", snapshot2.child("rate").child("id").getValue(String.class));

                        if (snapshot2.child("rate").child("id").getValue(String.class) .equals(service)){
                            double rate = snapshot2.child("rate").child("rate").getValue(double.class);
                            int man = snapshot2.child("rate").child("man").getValue(int.class);
                            double c = rate/man;
                            if(getrates<c&&c<endrate){
                                newDate.add(services.get(i));
                                newrate.add( String.valueOf(c));

                            }


                    }
                }
                }
                OwnViList servicesAdapter = new OwnViList(HomeOwnerMainPageActivity.this,newrate, newDate);
                Log.d("updateServiceRate", newDate.size()+newDate.toString());

                listViewServices.setAdapter(servicesAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });


    }



}
