package com.example.yuxuan.supermario;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class OwnViList extends ArrayAdapter<ProSer> {
    private Activity context;
    List<ProSer> services;

    public OwnViList(Activity context, List<ProSer> services) {
        super(context, R.layout.layout_service_list, services);
        this.context = context;
        this.services = services;

    }

    public View getView(int position, View convertView, ViewGroup parent){

    LayoutInflater inflater = context.getLayoutInflater();
    View listViewItem = inflater.inflate(R.layout.layout_proser_list, null, true);

    TextView textViewService = (TextView) listViewItem.findViewById(R.id.textViewService);
    TextView textViewHourRate = (TextView) listViewItem.findViewById(R.id.textViewDate);

    ProSer service = services.get(position);
    String Provi = service.getProID();
        textViewService.setText(Provi);
    Service service1 = service.getSerID();
    String Serviceas = service.getProID() + "\nServices Name: " + service1.getTypeOfService() + ".\n Hour Rate: " + service1.getHourRate();
        textViewService.setText(Serviceas);
    String Date = "Date: " + service.getDate() + ": From " + service.getStartTime() + " to " + service.getEndTime();
        textViewHourRate.setText(Date);
        return listViewItem;
    }
}
