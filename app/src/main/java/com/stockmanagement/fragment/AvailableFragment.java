package com.stockmanagement.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stockmanagement.R;
import com.stockmanagement.common.DBHelper_Available;
import com.stockmanagement.common.DBHelper_Stock;
import com.stockmanagement.common.Preferences;

import java.util.HashMap;
import java.util.Map;


public class AvailableFragment extends Fragment {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    DBHelper_Stock mydb;
    DBHelper_Available mydb_aAvailable;

    private TextView tvAvailMonitor, tvAvailKeyboard, tvAvailMouse, tvAvailCpu,
            tvAvailMacMini, tvAvailUsb, tvAvailHeadPhones, tvAvailPrinter;

    private String TAG = "AvailableFragment";
    int equipment_quantity[] = {0,0,0,0,0,0,0,0};
String equipment[] = {"Monitor", "Keyboard", "Mouse", "CPU", "MacMini", "USB", "HeadPhones", "Printer"};
    private TextView[] tvarray = new TextView[equipment.length];

    public AvailableFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

   /*     ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.v("this is",value);
                Toast.makeText(getContext(),value,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };*/

       /* ref.child("Available").child(equipment[1]).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.child("Available").getValue(Integer.class);
                Log.v("mLog",""+value);
                //equipment_quantity[1] = mydb_aAvailable.getAllCotacts("Keyboard").size();
                //equipment_quantity[1] = value;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                downStreamCode(dataSnapshot);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        };
        ref.addValueEventListener(postListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_available_list, container, false);

        //Findviews.......
        tvAvailMonitor = (TextView) view.findViewById(R.id.tvAvailMonitor);
        tvAvailKeyboard = (TextView) view.findViewById(R.id.tvAvailKeyboard);
        tvAvailMouse = (TextView) view.findViewById(R.id.tvAvailMouse);
        tvAvailCpu = (TextView) view.findViewById(R.id.tvAvailCpu);
        tvAvailMacMini = (TextView) view.findViewById(R.id.tvAvailMacMini);
        tvAvailUsb = (TextView) view.findViewById(R.id.tvAvailUsb);
        tvAvailHeadPhones = (TextView) view.findViewById(R.id.tvAvailHeadPhones);
        tvAvailPrinter = (TextView) view.findViewById(R.id.tvAvailPrinter);
        mydb_aAvailable = new DBHelper_Available(getContext());
        tvarray[0]= tvAvailMonitor;
        tvarray[1]=tvAvailKeyboard;
        tvarray[2]=tvAvailMouse;
        tvarray[3]=tvAvailCpu;
        tvarray[4]=tvAvailMacMini;
        tvarray[5]=tvAvailUsb;
        tvarray[6]=tvAvailHeadPhones;
        tvarray[7]=tvAvailPrinter;




        if (mydb_aAvailable.getAll().size() != 0) {
            // int key = mydb.getAllCotacts("Keyboard").size();
            //  int mou = mydb.getAllCotacts("Mouse").size();
            //    int mac = mydb.getAllCotacts("Mac Mini").size();
            //  int cpu = mydb.getAllCotacts("CPU").size();
            //  int usb = mydb.getAllCotacts("USB Cables").size();
            //  int head = mydb.getAllCotacts("HeadPhones").size();
            //  int print = mydb.getAllCotacts("Printer").size();
            //Log.d("hashmap data", "id = " + mydb_aAvailable.getAlldata().get("id"));
           // Log.d("hashmap data", "name =" + mydb_aAvailable.getAlldata().get("name"));
            if (!mydb_aAvailable.getAllCotacts("Monitor").isEmpty()) {
                equipment_quantity[0] = mydb_aAvailable.getAllCotacts("Monitor").size();
                tvAvailMonitor.setText((equipment_quantity[0]) + "");

            } else {
                equipment_quantity[0] = 0;
                tvAvailMonitor.setText(0 + "");
            }
            if (!mydb_aAvailable.getAllCotacts("Keyboard").isEmpty()) {
                equipment_quantity[1] = mydb_aAvailable.getAllCotacts("Keyboard").size();
                tvAvailKeyboard.setText((equipment_quantity[1]) + "");

            } else {
                equipment_quantity[1] = 0;
                tvAvailKeyboard.setText(0 + "");
            }
            if (!mydb_aAvailable.getAllCotacts("Mouse").isEmpty()) {
                equipment_quantity[2] = mydb_aAvailable.getAllCotacts("Mouse").size();
                tvAvailMouse.setText((equipment_quantity[2]) + "");
            } else {
                equipment_quantity[2] = 0;
                tvAvailMouse.setText(0 + "");
            }
            if (!mydb_aAvailable.getAllCotacts("Mac Mini").isEmpty()) {
                equipment_quantity[3] = mydb_aAvailable.getAllCotacts("Mac Mini").size();
                tvAvailMacMini.setText((equipment_quantity[3]) + "");
            } else {
                equipment_quantity[3] = 0;
                tvAvailMacMini.setText(0 + "");
            }
            if (!mydb_aAvailable.getAllCotacts("CPU").isEmpty()) {
                equipment_quantity[4] = mydb_aAvailable.getAllCotacts("CPU").size();
                tvAvailCpu.setText((equipment_quantity[4]) + "");
            } else {
                equipment_quantity[4] = 0;
                tvAvailCpu.setText(0 + "");
            }
            if (!mydb_aAvailable.getAllCotacts("USB Cables").isEmpty()) {
                equipment_quantity[5] = mydb_aAvailable.getAllCotacts("USB Cables").size();
                tvAvailUsb.setText((equipment_quantity[5]) + "");
            } else {
                equipment_quantity[5] = 0;
                tvAvailUsb.setText(0 + "");
            }
            if (!mydb_aAvailable.getAllCotacts("HeadPhones").isEmpty()) {
                equipment_quantity[6] = mydb_aAvailable.getAllCotacts("HeadPhones").size();
                tvAvailHeadPhones.setText((equipment_quantity[6]) + "");
            } else {
                equipment_quantity[6] = 0;
                tvAvailHeadPhones.setText(0 + "");
            }
            if (!mydb_aAvailable.getAllCotacts("Printer").isEmpty()) {
                equipment_quantity[7] = mydb_aAvailable.getAllCotacts("Printer").size();
                tvAvailPrinter.setText((equipment_quantity[7]) + "");
            } else {
                equipment_quantity[7] = 0;
                tvAvailPrinter.setText(0 + "");
            }
        } else {
            tvAvailMonitor.setText(0 + "");
            tvAvailKeyboard.setText(0 + "");
            tvAvailMouse.setText(0 + "");
            tvAvailMacMini.setText(0 + "");
            tvAvailCpu.setText(0 + "");
            tvAvailUsb.setText(0 + "");
            tvAvailHeadPhones.setText(0 + "");
            tvAvailPrinter.setText(0 + "");
        }
        upStreamCode();




        //        if (Preferences.getAvailablemonitor() != null)
//            tvAvailMonitor.setText(Preferences.getAvailablemonitor());
//        if (Preferences.getAvailablekeyboard() != null)
//            tvAvailKeyboard.setText(Preferences.getAvailablekeyboard());
//        if (Preferences.getAvailablemouse() != null)
//            tvAvailMouse.setText(Preferences.getAvailablemouse());
//        if (Preferences.getAvailablecpu() != null)
//            tvAvailCpu.setText(Preferences.getAvailablecpu());
//        if (Preferences.getAvailablemacmini() != null)
//            tvAvailMacMini.setText(Preferences.getAvailablemacmini());
//        if (Preferences.getAvailableusb() != null)
//            tvAvailUsb.setText(Preferences.getAvailableusb());
//        if (Preferences.getAvailableheadphone() != null)
//            tvAvailHeadPhones.setText(Preferences.getAvailableheadphone());
//        if (Preferences.getAvailableprinter() != null) {
//            tvAvailPrinter.setText(Preferences.getAvailableprinter());
//            Log.e("Avail", "frag printer" + Preferences.getAvailableprinter());
//        }
//
//        Preferences.setAvailablemonitor(tvAvailMonitor.getText().toString());
//        Preferences.setAvailablekeyboard(tvAvailKeyboard.getText().toString());
//        Preferences.setAvailablemouse(tvAvailMouse.getText().toString());
//        Preferences.setAvailablecpu(tvAvailCpu.getText().toString());
//        Preferences.setAvailablemacmini(tvAvailMacMini.getText().toString());
//        Preferences.setAvailableusb(tvAvailUsb.getText().toString());
//        Preferences.setAvailableheadphone(tvAvailHeadPhones.getText().toString());
//        Preferences.setAvailableprinter(tvAvailPrinter.getText().toString());
//
//        Log.e("Available", "::>> " + tvAvailMonitor.getText().toString()
//                + tvAvailKeyboard.getText().toString() + tvAvailMouse.getText().toString()
//                + tvAvailCpu.getText().toString() + tvAvailMacMini.getText().toString()
//                + tvAvailUsb.getText().toString() + tvAvailHeadPhones.getText().toString()
//                + tvAvailPrinter.getText().toString());


        return view;

    }



 public void upStreamCode(){
        for(int i=0;i<equipment.length;i++){
            DatabaseReference temp_ref = ref.child("Available");
            Map<String,Object> taskMap = new HashMap<String,Object>();
            taskMap.put(equipment[i], equipment_quantity[i]);
            temp_ref.updateChildren(taskMap);
        }

     //ref.child("Monitor").push().setValue(mon);



 }//end of upstream

    public void downStreamCode(DataSnapshot dataSnapshot){

        //int carid = dataSnapshot.child("Available").child("Keyboard").getValue(Integer.class);

        for(int i=0;i<equipment.length;i++){
            int temp = dataSnapshot.child("Available").child(equipment[i]).getValue(Integer.class);
            tvarray[i].setText(""+temp);
            Log.v("mLog",""+temp);



        }
    }
}
