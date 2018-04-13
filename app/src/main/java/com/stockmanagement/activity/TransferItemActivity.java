package com.stockmanagement.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.stockmanagement.R;
import com.stockmanagement.common.DBHelper_Available;
import com.stockmanagement.common.DBHelper_Occupied;
import com.stockmanagement.common.Preferences;
import com.travijuu.numberpicker.library.NumberPicker;

import java.util.ArrayList;
import java.util.HashMap;

public class TransferItemActivity extends BaseActivity {

    private Spinner spName, spQty, spFrom, spDeptFrom, spTo, spDeptTo;
    private Button btnTransfer;
    private Toolbar toolbar;
    private static Activity activity;
    private int qty = 0;
    LinearLayout linearLayout, linearLayout1;
    private int availmon = 0, availkb = 0, availmou = 0, availcpu = 0, availmac = 0, availusb = 0, availhp = 0, availprinter = 0,
            stockmon = 0, stockkb = 0, stockmou = 0, stockcpu = 0, stockmac = 0, stockusb = 0, stockhp = 0, stockprinter = 0,
            occupymon = 0, occupykb = 0, occupymou = 0, occupycpu = 0, occupymac = 0, occupyusb = 0, occupyhp = 0, occupyprinter = 0,
            updatedmon = 0, updatedkb = 0, updatedmou = 0, udpatedcpu = 0, updatedmac = 0, updatedusb = 0, updatedhp = 0, updatedprinter = 0;
    NumberPicker numberPicker;
    DBHelper_Available mDbHelper_available;
    DBHelper_Occupied mDbHelper_occupied;
    //  HashMap<String, String> data = new HashMap<String, String>();
    ArrayList<HashMap<String, String>> data;
    ArrayList<HashMap<String, String>> occ_data;
    String spFrom_str, spName_str, spDeptfrom_str, spTo_str, spDeptTo_str;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_item);
        activity = TransferItemActivity.this;
        findviews();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Transfer Item");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Set All Preferences....
        //   setPreferences();
        mDbHelper_available = new DBHelper_Available(this);
        mDbHelper_occupied = new DBHelper_Occupied(this);
        data = new ArrayList<HashMap<String, String>>();
        occ_data = new ArrayList<HashMap<String, String>>();
        numberPicker.setMax(50);
        numberPicker.setMin(0);
        numberPicker.setUnit(1);
        numberPicker.setValue(10);
        spFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                if (spFrom.getItemAtPosition(spFrom.getSelectedItemPosition()).toString().equals("Available")) {
                    linearLayout.setVisibility(View.GONE);
                } else {
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        spTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                if (spTo.getItemAtPosition(spTo.getSelectedItemPosition()).toString().equals("Available")) {
                    linearLayout1.setVisibility(View.GONE);
                } else {
                    linearLayout1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        //  occ_data = mDbHelper_occupied.getAllAnimals();
        //   data.add(mDbHelper_available.getAlldata());

        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = mDbHelper_available.getAllAnimals(spName.getItemAtPosition(spName.getSelectedItemPosition()).toString());
                occ_data = mDbHelper_occupied.getAllAnimals(spName.getItemAtPosition(spName.getSelectedItemPosition()).toString(), spDeptFrom.getItemAtPosition(spDeptFrom.getSelectedItemPosition()).toString());

                Log.d("hashmap", "data =" + data);
                Log.d("hashmap", "occ_data =" + occ_data);
                spFrom_str = spFrom.getItemAtPosition(spFrom.getSelectedItemPosition()).toString();
                spTo_str = spTo.getItemAtPosition(spTo.getSelectedItemPosition()).toString();
                spName_str = spName.getItemAtPosition(spName.getSelectedItemPosition()).toString();
                spDeptfrom_str = spDeptFrom.getItemAtPosition(spDeptFrom.getSelectedItemPosition()).toString();
                spDeptTo_str = spDeptTo.getItemAtPosition(spDeptTo.getSelectedItemPosition()).toString();
                if (spFrom_str.equals("Available") && !spTo_str.equals("Available")) {
                    if (!mDbHelper_available.getAllCotacts(spName_str).isEmpty()) {
                        int len = mDbHelper_available.getAllCotacts(spName_str).size();
                        Log.d("num", "" + numberPicker.getValue());
                        Log.d("totol stock", "" + len);
                        if (len >= numberPicker.getValue()) {

                            int l = numberPicker.getValue();
                            for (int i = 0; i < l; i++) {
                                mDbHelper_available.deleteContact(data.get(i).get("id"));
                                //String a = data.get(i).get("ssh");
                                mDbHelper_occupied.insertContact(data.get(i).get("name"), data.get(i).get("model"), data.get(i).get("serial"), data.get(i).get("description"), spDeptTo_str);

                            }
                            Intent intent = new Intent(activity, HomeActivity.class);
                            Preferences.setTransitemflag("1");
                            startActivity(intent);
                        } else {
                            Toast.makeText(activity, "Your " + spName.getItemAtPosition(spName.getSelectedItemPosition()).toString() + " is out of stock ", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else if (spFrom_str.equals("Occupied")) {
//                    if (!mDbHelper_occupied.getAllCotacts(spName.getItemAtPosition(spName.getSelectedItemPosition()).toString()).isEmpty()) {
//
//                    }
                    if (spTo_str.equals("Available")) {
                        if (!mDbHelper_occupied.getAllCotactslist(spName_str, spDeptfrom_str).isEmpty()) {
                            int len = mDbHelper_occupied.getAllCotactslist(spName_str, spDeptfrom_str).size();
                            if (len >= numberPicker.getValue()) {

                                //int l = mDbHelper_occupied.getAllCotactslist(spName_str, spDeptfrom_str).size();
                                int l = numberPicker.getValue();
                                for (int i = 0; i < l; i++) {
                                    mDbHelper_occupied.deleteContact(occ_data.get(i).get("id"));
                                    //String a = data.get(i).get("ssh");
                                    mDbHelper_available.insertContact(occ_data.get(i).get("name"), occ_data.get(i).get("model"), occ_data.get(i).get("serial"), occ_data.get(i).get("description"));

                                }
                                Intent intent = new Intent(activity, HomeActivity.class);
                                Preferences.setTransitemflag("1");
                                startActivity(intent);
                            }
                        }
                    } else if (spTo_str.equals("Occupied")) {
                        if (spDeptfrom_str.equals(spDeptTo_str)) {
                            Toast.makeText(TransferItemActivity.this, "Please Select Other Deptement", Toast.LENGTH_SHORT).show();

                        } else {
                            if (!mDbHelper_occupied.getAllCotactslist(spName_str, spDeptfrom_str).isEmpty()) {
                                int len = mDbHelper_occupied.getAllCotactslist(spName_str, spDeptfrom_str).size();
                                if (len >= numberPicker.getValue()) {
                                    int l = numberPicker.getValue();
                                  //  int l = mDbHelper_occupied.getAllCotactslist(spName_str, spDeptfrom_str).size();
                                    for (int i = 0; i < l; i++) {
                                        //    mDbHelper_occupied.deleteContact(occ_data.get(i).get("id"));
                                        mDbHelper_occupied.updateContact(occ_data.get(i).get("id"), spDeptTo_str);
                                        //String a = data.get(i).get("ssh");
                                        // mDbHelper_available.insertContact(occ_data.get(i).get("name"), occ_data.get(i).get("model"), occ_data.get(i).get("serial"), occ_data.get(i).get("description"));

                                    }
                                    Intent intent = new Intent(activity, HomeActivity.class);
                                    Preferences.setTransitemflag("1");
                                    startActivity(intent);
                                }
                            }
                        }


                    }
//                }else if (spFrom.getItemAtPosition(spFrom.getSelectedItemPosition()).toString().equals("Available") && spTo.getItemAtPosition(spTo.getSelectedItemPosition()).toString().equals("Available")) {

                } else {
                    Toast.makeText(TransferItemActivity.this, "Please Select Occupied", Toast.LENGTH_SHORT).show();
                }

//                qty = Integer.parseInt(spQty.getSelectedItem().toString());
//
//                if (spName.getSelectedItem().toString().equals("Monitor")) {
//                    if (spFrom.getSelectedItem().toString().equals("Available")) {
//                        if (qty <= (availmon)) {
//                            Log.e("Transfer", "availmon");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "To Stock");
//
//                                int updatedmon = availmon - qty;
//                                Preferences.setAvailablemonitor(String.valueOf(updatedmon));
//                                stockmon = stockmon + qty;
//                                Preferences.setAddtostockmonitor(String.valueOf(stockmon));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "To Occupied");
//
//                                int updatedmon = availmon - qty;
//                                Preferences.setAvailablemonitor(String.valueOf(updatedmon));
//                                occupymon = occupymon + qty;
//                                Preferences.setOccupiedmonitor(String.valueOf(occupymon));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                startActivity(intent);
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "Not Possible");
//
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    } else if (spFrom.getSelectedItem().toString().equals("Occupied")) {
//                        if (qty <= (occupymon)) {
//                            Log.e("Tranfer", "occupymon");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "To Stock");
//
//                                int updatedmon = occupymon - qty;
//                                Preferences.setOccupiedmonitor(String.valueOf(updatedmon));
//                                stockmon = stockmon + qty;
//                                Preferences.setAddtostockmonitor(String.valueOf(stockmon));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "To Available");
//
//                                int updatedmon = occupymon - qty;
//                                Preferences.setOccupiedmonitor(String.valueOf(updatedmon));
//                                availmon = availmon + qty;
//                                Preferences.setAddtostockmonitor(String.valueOf(availmon));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                startActivity(intent);
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    } else if (spFrom.getSelectedItem().toString().equals("Stock")) {
//                        if (qty <= (stockmon)) {
//                            Log.e("Tranfer", "stockmon");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "To Occupied");
//
//                                int updatedmon = stockmon - qty;
//                                Preferences.setAddtostockmonitor(String.valueOf(updatedmon));
//                                occupymon = occupymon + qty;
//                                Preferences.setOccupiedmonitor(String.valueOf(occupymon));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "To Available");
//
//                                int updatedmon = stockmon - qty;
//                                Preferences.setAddtostockmonitor(String.valueOf(updatedmon));
//                                availmon = availmon + qty;
//                                Preferences.setAvailablemonitor(String.valueOf(availmon));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                } else if (spName.getSelectedItem().toString().equals("Keyboard")) {
//                    if (spFrom.getSelectedItem().toString().equals("Available")) {
//                        if (qty <= (availkb)) {
//                            Log.e("Tranfer", "availkb");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "To Stock");
//
//                                int updatedkb = availmon - qty;
//                                Preferences.setAvailablekeyboard(String.valueOf(updatedkb));
//                                stockkb = stockkb + qty;
//                                Preferences.setAddtostockkeyboard(String.valueOf(stockkb));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "To Occupied");
//
//                                int updatedkb = availkb - qty;
//                                Preferences.setAvailablekeyboard(String.valueOf(updatedkb));
//                                occupykb = occupykb + qty;
//                                Preferences.setOccupiedkeyboard(String.valueOf(occupykb));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                startActivity(intent);
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    } else if (spFrom.getSelectedItem().toString().equals("Occupied")) {
//                        if (qty <= (occupykb)) {
//                            Log.e("Tranfer", "occupykb");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "To Stock");
//
//                                int updatedkb = occupykb - qty;
//                                Preferences.setOccupiedkeyboard(String.valueOf(updatedkb));
//                                stockkb = stockkb + qty;
//                                Preferences.setAddtostockkeyboard(String.valueOf(stockkb));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "To Available");
//
//                                int updatedkb = occupykb + qty;
//                                Preferences.setOccupiedkeyboard(String.valueOf(updatedkb));
//                                availkb = availkb + qty;
//                                Preferences.setAvailablekeyboard(String.valueOf(availkb));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                startActivity(intent);
//
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    } else if (spFrom.getSelectedItem().toString().equals("Stock")) {
//                        if (qty <= (stockkb)) {
//                            Log.e("Tranfer", "stockkb");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "To Occupied");
//
//                                int updatedkb = stockkb + qty;
//                                Preferences.setAddtostockkeyboard(String.valueOf(updatedkb));
//                                occupykb = occupykb + qty;
//                                Preferences.setOccupiedkeyboard(String.valueOf(occupykb));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "To Available");
//
//                                int updatedkb = stockkb - qty;
//                                Preferences.setAddtostockkeyboard(String.valueOf(updatedkb));
//                                availkb = availkb + qty;
//                                Preferences.setAvailablekeyboard(String.valueOf(availkb));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                } else if (spName.getSelectedItem().toString().equals("Mouse")) {
//                    if (spFrom.getSelectedItem().toString().equals("Available")) {
//                        if (qty <= (availmou)) {
//                            Log.e("Tranfer", "availmou");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "To Stock");
//
//                                int updatedmou = availmou - qty;
//                                Preferences.setAvailablemouse(String.valueOf(updatedmou));
//                                stockmou = stockmou + qty;
//                                Preferences.setAddtostockmouse(String.valueOf(stockmou));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "To Occupied");
//
//                                int updatedmou = availmou - qty;
//                                Preferences.setAvailablemouse(String.valueOf(updatedmou));
//                                occupymou = occupymou + qty;
//                                Preferences.setOccupiedmouse(String.valueOf(occupymou));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    } else if (spFrom.getSelectedItem().toString().equals("Occupied")) {
//                        if (qty <= (occupymou)) {
//                            Log.e("Tranfer", "occupymou");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "To Stock");
//
//                                int updatedmou = occupymou - qty;
//                                Preferences.setOccupiedmouse(String.valueOf(updatedmou));
//                                stockmou = stockmou + qty;
//                                Preferences.setAddtostockmouse(String.valueOf(stockmou));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "To Available");
//
//                                int updatedmou = occupymou - qty;
//                                Preferences.setOccupiedmouse(String.valueOf(updatedmou));
//                                availmou = availmou + qty;
//                                Preferences.setAvailablemouse(String.valueOf(availmou));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    } else if (spFrom.getSelectedItem().toString().equals("Stock")) {
//                        if (qty <= (stockmou)) {
//                            Log.e("Tranfer", "stockmou");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "To Occupied");
//
//                                int updatedmou = stockmou - qty;
//                                Preferences.setAddtostockmouse(String.valueOf(updatedmou));
//                                occupymou = occupymou + qty;
//                                Preferences.setOccupiedmouse(String.valueOf(occupymou));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "To Available");
//
//                                int updatedmou = stockmou - qty;
//                                Preferences.setAddtostockmouse(String.valueOf(updatedmou));
//                                availmou = availmou + qty;
//                                Preferences.setAvailablemouse(String.valueOf(availmou));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                } else if (spName.getSelectedItem().toString().equals("CPU")) {
//                    if (spFrom.getSelectedItem().toString().equals("Available")) {
//                        if (qty <= (availcpu)) {
//                            Log.e("Tranfer", "availcpu");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "To Stock");
//
//                                int updatedcpu = availcpu - qty;
//                                Preferences.setAvailablecpu(String.valueOf(updatedcpu));
//                                stockcpu = stockcpu + qty;
//                                Preferences.setAddtostockcpu(String.valueOf(stockcpu));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "To Occupied");
//
//                                int updatedcpu = availcpu - qty;
//                                Preferences.setAvailablecpu(String.valueOf(updatedcpu));
//                                occupycpu = occupycpu + qty;
//                                Preferences.setOccupiedcpu(String.valueOf(occupycpu));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    } else if (spFrom.getSelectedItem().toString().equals("Occupied")) {
//                        if (qty <= (occupycpu)) {
//                            Log.e("Tranfer", "occupycpu");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "To Stock");
//
//                                int updatedcpu = occupycpu - qty;
//                                Preferences.setOccupiedcpu(String.valueOf(updatedcpu));
//                                stockcpu = stockcpu + qty;
//                                Preferences.setAddtostockcpu(String.valueOf(stockcpu));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "To Available");
//
//                                int updatedcpu = occupycpu - qty;
//                                Preferences.setOccupiedcpu(String.valueOf(updatedcpu));
//                                availcpu = availcpu + qty;
//                                Preferences.setAvailablecpu(String.valueOf(availcpu));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    } else if (spFrom.getSelectedItem().toString().equals("Stock")) {
//                        if (qty <= (stockcpu)) {
//                            Log.e("Tranfer", "stockcpu");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "To Occupied");
//
//                                int updatedcpu = stockcpu - qty;
//                                Preferences.setAddtostockcpu(String.valueOf(updatedcpu));
//                                occupycpu = occupycpu + qty;
//                                Preferences.setOccupiedcpu(String.valueOf(occupycpu));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "To Available");
//
//                                int updatedcpu = stockcpu - qty;
//                                Preferences.setAddtostockcpu(String.valueOf(updatedcpu));
//                                availcpu = availcpu + qty;
//                                Preferences.setAvailablecpu(String.valueOf(availcpu));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                } else if (spName.getSelectedItem().toString().equals("Mac Mini")) {
//                    if (spFrom.getSelectedItem().toString().equals("Available")) {
//                        if (qty <= (availmac)) {
//                            Log.e("Tranfer", "availmac");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "To Stock");
//
//                                int updatedmac = availmac - qty;
//                                Preferences.setAvailablemacmini(String.valueOf(updatedmac));
//                                stockmac = stockmac + qty;
//                                Preferences.setAddtostockmacmini(String.valueOf(stockmac));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "To Occupied");
//
//                                int updatedmac = availmac - qty;
//                                Preferences.setAvailablemacmini(String.valueOf(updatedmac));
//                                occupymac = occupymac + qty;
//                                Preferences.setOccupiedmacmini(String.valueOf(occupymac));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    } else if (spFrom.getSelectedItem().toString().equals("Occupied")) {
//                        if (qty <= (occupymac)) {
//                            Log.e("Tranfer", "occupymac");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "To Stock");
//
//                                int updatedmac = occupymac - qty;
//                                Preferences.setOccupiedmacmini(String.valueOf(updatedmac));
//                                stockmac = stockmac + qty;
//                                Preferences.setAddtostockmacmini(String.valueOf(stockmac));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "To Available");
//
//                                int updatedmac = occupymac - qty;
//                                Preferences.setOccupiedmacmini(String.valueOf(updatedmac));
//                                availmac = availmac + qty;
//                                Preferences.setAvailablemacmini(String.valueOf(availmac));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    } else if (spFrom.getSelectedItem().toString().equals("Stock")) {
//                        if (qty <= (stockmac)) {
//                            Log.e("Tranfer", "stockmac");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "To Occupied");
//
//                                int updatedmac = stockmac - qty;
//                                Preferences.setAddtostockmacmini(String.valueOf(updatedmac));
//                                occupymac = occupymac + qty;
//                                Preferences.setOccupiedmacmini(String.valueOf(occupymac));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "To Available");
//
//                                int updatedmac = stockmac - qty;
//                                Preferences.setAddtostockmacmini(String.valueOf(updatedmac));
//                                availmac = availmac + qty;
//                                Preferences.setAddtostockmacmini(String.valueOf(availmac));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                } else if (spName.getSelectedItem().toString().equals("USB Cables")) {
//                    if (spFrom.getSelectedItem().toString().equals("Available")) {
//                        if (qty <= (availusb)) {
//                            Log.e("Tranfer", "availusb");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "To Stock");
//
//                                int updatedusb = availusb - qty;
//                                Preferences.setAvailableusb(String.valueOf(updatedusb));
//                                stockusb = stockusb + qty;
//                                Preferences.setAddtostockusb(String.valueOf(stockusb));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "To Occupied");
//
//                                int updatedusb = availusb - qty;
//                                Preferences.setAvailableusb(String.valueOf(updatedusb));
//                                occupyusb = occupyusb + qty;
//                                Preferences.setOccupiedusb(String.valueOf(occupyusb));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    } else if (spFrom.getSelectedItem().toString().equals("Occupied")) {
//                        if (qty <= (occupyusb)) {
//                            Log.e("Tranfer", "occupyusb");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "To Stock");
//
//                                int updatedusb = occupyusb - qty;
//                                Preferences.setOccupiedusb(String.valueOf(updatedusb));
//                                stockusb = stockusb + qty;
//                                Preferences.setAddtostockusb(String.valueOf(stockusb));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "To Available");
//
//                                int updatedusb = occupyusb - qty;
//                                Preferences.setOccupiedusb(String.valueOf(updatedusb));
//                                availusb = availusb + qty;
//                                Preferences.setAvailableusb(String.valueOf(availusb));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    } else if (spFrom.getSelectedItem().toString().equals("Stock")) {
//                        if (qty <= (stockusb)) {
//                            Log.e("Tranfer", "stockusb");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "To Occupied");
//
//                                int updatedusb = stockusb - qty;
//                                Preferences.setAddtostockusb(String.valueOf(updatedusb));
//                                occupyusb = occupyusb + qty;
//                                Preferences.setOccupiedusb(String.valueOf(occupyusb));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "To Available");
//
//                                int updatedusb = stockusb - qty;
//                                Preferences.setAddtostockusb(String.valueOf(updatedusb));
//                                availusb = availusb + qty;
//                                Preferences.setAvailableusb(String.valueOf(availusb));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                } else if (spName.getSelectedItem().toString().equals("HeadPhones")) {
//                    if (spFrom.getSelectedItem().toString().equals("Available")) {
//                        if (qty <= (availhp)) {
//                            Log.e("Tranfer", "availhp");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "To Stock");
//
//                                int updatedhp = availhp - qty;
//                                Preferences.setAvailableheadphone(String.valueOf(updatedhp));
//                                stockhp = stockhp + qty;
//                                Preferences.setAddtostockheadphone(String.valueOf(stockhp));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "To Occupied");
//
//                                int updatedhp = availhp - qty;
//                                Preferences.setAvailableheadphone(String.valueOf(updatedhp));
//                                occupyhp = occupyhp + qty;
//                                Preferences.setOccupiedheadphone(String.valueOf(occupyhp));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    } else if (spFrom.getSelectedItem().toString().equals("Occupied")) {
//                        if (qty <= (occupyhp)) {
//                            Log.e("Tranfer", "occupyhp");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "To Stock");
//
//                                int updatedhp = occupyhp - qty;
//                                Preferences.setOccupiedheadphone(String.valueOf(updatedhp));
//                                stockhp = stockhp + qty;
//                                Preferences.setAddtostockheadphone(String.valueOf(stockhp));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "To Available");
//
//                                int updatedhp = occupyhp - qty;
//                                Preferences.setOccupiedheadphone(String.valueOf(updatedhp));
//                                availhp = availhp + qty;
//                                Preferences.setAvailableheadphone(String.valueOf(availhp));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    } else if (spFrom.getSelectedItem().toString().equals("Stock")) {
//                        if (qty <= (stockhp)) {
//                            Log.e("Tranfer", "stockhp");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "To Occupied");
//
//                                int updatedhp = stockhp - qty;
//                                Preferences.setAddtostockheadphone(String.valueOf(updatedhp));
//                                occupyhp = occupyhp + qty;
//                                Preferences.setOccupiedheadphone(String.valueOf(occupyhp));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "To Available");
//
//                                int updatedhp = stockhp - qty;
//                                Preferences.setAddtostockheadphone(String.valueOf(updatedhp));
//                                availhp = availhp + qty;
//                                Preferences.setAvailableheadphone(String.valueOf(availhp));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                } else if (spName.getSelectedItem().toString().equals("Printer")) {
//                    if (spFrom.getSelectedItem().toString().equals("Available")) {
//                        if (qty <= (availprinter)) {
//                            Log.e("Tranfer", "availprinter");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "To Stock");
//
//                                int updatedprint = availprinter - qty;
//                                Preferences.setAvailableprinter(String.valueOf(updatedprint));
//                                stockprinter = stockprinter + qty;
//                                Preferences.setAddtostockprinter(String.valueOf(stockprinter));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "To Occupied");
//
//                                int updatedprint = availprinter - qty;
//                                Preferences.setAvailableprinter(String.valueOf(updatedprint));
//                                occupyprinter = occupyprinter + qty;
//                                Preferences.setOccupiedprinter(String.valueOf(occupyprinter));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    } else if (spFrom.getSelectedItem().toString().equals("Occupied")) {
//                        if (qty <= (occupyprinter)) {
//                            Log.e("Tranfer", "occupyprinter");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "To Stock");
//
//                                int updatedprint = occupyprinter - qty;
//                                Preferences.setOccupiedprinter(String.valueOf(updatedprint));
//                                stockprinter = stockprinter + qty;
//                                Preferences.setAddtostockprinter(String.valueOf(stockprinter));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "To Available");
//
//                                int updatedprint = occupyprinter - qty;
//                                Preferences.setOccupiedprinter(String.valueOf(updatedprint));
//                                availprinter = availprinter + qty;
//                                Preferences.setAvailableprinter(String.valueOf(availprinter));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    } else if (spFrom.getSelectedItem().toString().equals("Stock")) {
//                        if (qty <= (stockprinter)) {
//                            Log.e("Tranfer", "stockprinter");
//
//                            if (spTo.getSelectedItem().toString().equals("Stock")) {
//                                Log.e("Transfer", "Not Possible");
//                                Toast.makeText(activity, "Transfer not possible in same division", Toast.LENGTH_SHORT).show();
//
//                            } else if (spTo.getSelectedItem().toString().equals("Occupied")) {
//                                Log.e("Transfer", "To Occupied");
//
//                                int updatedprint = stockprinter - qty;
//                                Preferences.setAddtostockprinter(String.valueOf(updatedprint));
//                                occupyprinter = occupyprinter + qty;
//                                Preferences.setOccupiedprinter(String.valueOf(occupyprinter));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            } else if (spTo.getSelectedItem().toString().equals("Available")) {
//                                Log.e("Transfer", "To Available");
//
//                                int updatedprint = stockprinter - qty;
//                                Preferences.setAddtostockprinter(String.valueOf(updatedprint));
//                                availprinter = availprinter + qty;
//                                Preferences.setAvailableprinter(String.valueOf(availprinter));
//                                Intent intent = new Intent(activity, HomeActivity.class);
//                                Preferences.setTransitemflag("1");
//                                startActivity(intent);
//
//                            }
//
//                        } else {
//                            Toast.makeText(activity, "Current selected quantity is out of bound. Plz decrease the limit",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }

            }
        });

    }

    private void setPreferences() {

        //Available Preferences....
        availmon = Integer.parseInt(Preferences.getAvailablemonitor());
        availkb = Integer.parseInt(Preferences.getAvailablekeyboard());
        availmou = Integer.parseInt(Preferences.getAvailablemouse());
        availcpu = Integer.parseInt(Preferences.getAvailablecpu());
        availmac = Integer.parseInt(Preferences.getAvailablemacmini());
        availusb = Integer.parseInt(Preferences.getAvailableusb());
        availhp = Integer.parseInt(Preferences.getAvailableheadphone());
        availprinter = Integer.parseInt(Preferences.getAvailableprinter());

        //Stock Preferences.....
        stockmon = Integer.parseInt(Preferences.getAddtostockmonitor());
        stockkb = Integer.parseInt(Preferences.getAddtostockkeyboard());
        stockmou = Integer.parseInt(Preferences.getAddtostockmouse());
        stockcpu = Integer.parseInt(Preferences.getAddtostockcpu());
        stockmac = Integer.parseInt(Preferences.getAddtostockmacmini());
        stockusb = Integer.parseInt(Preferences.getAddtostockusb());
        stockhp = Integer.parseInt(Preferences.getAddtostockheadphone());
        stockprinter = Integer.parseInt(Preferences.getAddtostockprinter());

        //Occupy Preferences.....
        occupymon = Integer.parseInt(Preferences.getOccupiedmonitor());
        occupykb = Integer.parseInt(Preferences.getOccupiedkeyboard());
        occupymou = Integer.parseInt(Preferences.getOccupiedmouse());
        occupycpu = Integer.parseInt(Preferences.getOccupiedcpu());
        occupymac = Integer.parseInt(Preferences.getOccupiedmacmini());
        occupyusb = Integer.parseInt(Preferences.getOccupiedusb());
        occupyhp = Integer.parseInt(Preferences.getOccupiedheadphone());
        occupyprinter = Integer.parseInt(Preferences.getOccupiedprinter());

    }

    private void findviews() {
        spName = (Spinner) findViewById(R.id.spName);
        spQty = (Spinner) findViewById(R.id.spQty);
        spFrom = (Spinner) findViewById(R.id.spFrom);
        spDeptFrom = (Spinner) findViewById(R.id.spDeptFrom);
        spDeptTo = (Spinner) findViewById(R.id.spDeptTo);
        spTo = (Spinner) findViewById(R.id.spTo);
        btnTransfer = (Button) findViewById(R.id.btnTransfer);
        linearLayout = (LinearLayout) findViewById(R.id.liner1);
        linearLayout1 = (LinearLayout) findViewById(R.id.liner2);
        numberPicker = (NumberPicker) findViewById(R.id.number_picker);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
