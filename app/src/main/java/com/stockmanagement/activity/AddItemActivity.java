package com.stockmanagement.activity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.stockmanagement.R;
import com.stockmanagement.common.DBHelper_Available;
import com.stockmanagement.common.DBHelper_Stock;
import com.stockmanagement.common.Preferences;

import java.util.ArrayList;
import java.util.List;

public class AddItemActivity extends BaseActivity {

    private Spinner spAddName;
    private EditText edtModelno, edtSrno, edtDis;
    private Button btnAdd;
    private Toolbar toolbar;
    private static Activity activity;
    private int monitor = 0, keyboard = 0, mouse = 0, cpu = 0, mac = 0, usb = 0, hp = 0, printer = 0;

    DBHelper_Stock mydb;
    DBHelper_Available mydb_aAvailable;
    List<String> lables = new ArrayList<String>();

    private FirebaseAuth mAuth;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        mAuth = FirebaseAuth.getInstance();
        activity = AddItemActivity.this;
        findviews();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Add Item");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mydb = new DBHelper_Stock(this);
        mydb_aAvailable = new DBHelper_Available(this);
        Log.d("database:", "all = " + mydb.getAllCotacts("Monitor"));
        lables.add("Monitor");
        lables.add("Keyboard");
        lables.add("Mouse");
        lables.add("CPU");
        lables.add("Mac Mini");
        lables.add("USB Cables");
        lables.add("HeadPhones");
        lables.add("Printer");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spAddName.setAdapter(spinnerAdapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    if (spAddName.getSelectedItem().toString().equals("Monitor")) {
//                        monitor = Integer.parseInt(Preferences.getAddtostockmonitor());
//                        monitor = monitor + 1;
//                        if (edtSrno.getText().toString().equals("")) {
//                            Toast.makeText(activity, "Please enter the Sr. No.", Toast.LENGTH_SHORT).show();
//                        } else if (edtModelno.getText().toString().equals("")) {
//                            Toast.makeText(activity, "Please enter the Model. No.", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Preferences.setAddtostockmonitor(String.valueOf(monitor));
//                            Preferences.setAdditemflag("1");
//                            Intent intent = new Intent(activity, HomeActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                        Log.e("AddItem", "::>> Monitor Added" + monitor);
//                    } else if (spAddName.getSelectedItem().toString().equals("Keyboard")) {
//                        keyboard = Integer.parseInt(Preferences.getAddtostockkeyboard());
//                        keyboard = keyboard + 1;
//                        if (edtSrno.getText().toString().equals("")) {
//                            Toast.makeText(activity, "Please enter the Sr. No.", Toast.LENGTH_SHORT).show();
//                        } else if (edtModelno.getText().toString().equals("")) {
//                            Toast.makeText(activity, "Please enter the Model. No.", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Preferences.setAdditemflag("1");
//                            Preferences.setAddtostockkeyboard(String.valueOf(keyboard));
//                            Intent intent = new Intent(activity, HomeActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                        Log.e("AddItem", "::>> Keyboard Added");
//                    } else if (spAddName.getSelectedItem().toString().equals("Mouse")) {
//                        mouse = Integer.parseInt(Preferences.getAddtostockmouse());
//                        mouse = mouse + 1;
//                        if (edtSrno.getText().toString().equals("")) {
//                            Toast.makeText(activity, "Please enter the Sr. No.", Toast.LENGTH_SHORT).show();
//                        } else if (edtModelno.getText().toString().equals("")) {
//                            Toast.makeText(activity, "Please enter the Model. No.", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Preferences.setAddtostockmouse(String.valueOf(mouse));
//                            Preferences.setAdditemflag("1");
//                            Intent intent = new Intent(activity, HomeActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                        Log.e("AddItem", "::>> Mouse Added");
//                    } else if (spAddName.getSelectedItem().toString().equals("CPU")) {
//                        cpu = Integer.parseInt(Preferences.getAddtostockcpu());
//                        cpu = cpu + 1;
//                        if (edtSrno.getText().toString().equals("")) {
//                            Toast.makeText(activity, "Please enter the Sr. No.", Toast.LENGTH_SHORT).show();
//                        } else if (edtModelno.getText().toString().equals("")) {
//                            Toast.makeText(activity, "Please enter the Model. No.", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Preferences.setAddtostockcpu(String.valueOf(cpu));
//                            Preferences.setAdditemflag("1");
//                            Intent intent = new Intent(activity, HomeActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                        Log.e("AddItem", "::>> CPU Added");
//                    } else if (spAddName.getSelectedItem().toString().equals("Mac Mini")) {
//                        mac = Integer.parseInt(Preferences.getAddtostockmacmini());
//                        mac = mac + 1;
//                        if (edtSrno.getText().toString().equals("")) {
//                            Toast.makeText(activity, "Please enter the Sr. No.", Toast.LENGTH_SHORT).show();
//                        } else if (edtModelno.getText().toString().equals("")) {
//                            Toast.makeText(activity, "Please enter the Model. No.", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Preferences.setAddtostockmacmini(String.valueOf(mac));
//                            Preferences.setAdditemflag("1");
//                            Intent intent = new Intent(activity, HomeActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                        Log.e("AddItem", "::>> Mac Added");
//                    } else if (spAddName.getSelectedItem().toString().equals("USB Cables")) {
//                        usb = Integer.parseInt(Preferences.getAddtostockusb());
//                        usb = usb + 1;
//                        if (edtSrno.getText().toString().equals("")) {
//                            Toast.makeText(activity, "Please enter the Sr. No.", Toast.LENGTH_SHORT).show();
//                        } else if (edtModelno.getText().toString().equals("")) {
//                            Toast.makeText(activity, "Please enter the Model. No.", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Preferences.setAddtostockusb(String.valueOf(usb));
//                            Preferences.setAdditemflag("1");
//                            Intent intent = new Intent(activity, HomeActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                        Log.e("AddItem", "::>> USB Added");
//                    } else if (spAddName.getSelectedItem().toString().equals("HeadPhones")) {
//                        hp = Integer.parseInt(Preferences.getAddtostockheadphone());
//                        hp = hp + 1;
//                        if (edtSrno.getText().toString().equals("")) {
//                            Toast.makeText(activity, "Please enter the Sr. No.", Toast.LENGTH_SHORT).show();
//                        } else if (edtModelno.getText().toString().equals("")) {
//                            Toast.makeText(activity, "Please enter the Model. No.", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Preferences.setAddtostockheadphone(String.valueOf(hp));
//                            Preferences.setAdditemflag("1");
//                            Intent intent = new Intent(activity, HomeActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                        Log.e("AddItem", "::>> HeadPhones Added");
//                    } else if (spAddName.getSelectedItem().toString().equals("Printer")) {
//                        printer = Integer.parseInt(Preferences.getAddtostockprinter());
//                        printer = printer + 1;
//                        if (edtSrno.getText().toString().equals("")) {
//                            Toast.makeText(activity, "Please enter the Sr. No.", Toast.LENGTH_SHORT).show();
//                        } else if (edtModelno.getText().toString().equals("")) {
//                            Toast.makeText(activity, "Please enter the Model. No.", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Preferences.setAddtostockprinter(String.valueOf(printer));
//                            Preferences.setAdditemflag("1");
//                            Intent intent = new Intent(activity, HomeActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                        Log.e("AddItem", "::>> Printer Added");
//                    }
//                } catch (NumberFormatException e) {
//                    e.printStackTrace();
//                }

                if (edtSrno.getText().toString().equals("")) {
                    Toast.makeText(activity, "Please enter the Sr. No.", Toast.LENGTH_SHORT).show();
                } else if (edtModelno.getText().toString().equals("")) {
                    Toast.makeText(activity, "Please enter the Model. No.", Toast.LENGTH_SHORT).show();
                } else if (edtDis.getText().toString().equals("")) {
//                    Preferences.setAddtostockprinter(String.valueOf(printer));
//                    Preferences.setAdditemflag("1");
//                    Intent intent = new Intent(activity, HomeActivity.class);
//                    startActivity(intent);
//                    finish();
                    Toast.makeText(activity, "Please enter the Model. No.", Toast.LENGTH_SHORT).show();
                } else {
                    String thing = spAddName.getItemAtPosition(spAddName.getSelectedItemPosition()).toString();
                    String model_no = edtModelno.getText().toString();
                    String sr_no = edtSrno.getText().toString();
                    String desc = edtDis.getText().toString();
                    mydb.insertContact(thing, model_no, sr_no, desc);
                    mydb_aAvailable.insertContact(spAddName.getItemAtPosition(spAddName.getSelectedItemPosition()).toString(), edtModelno.getText().toString(), edtSrno.getText().toString(), edtDis.getText().toString());
                    Intent intent = new Intent(activity, HomeActivity.class);

                    //Firebase
                    DatabaseReference child = ref.child(spAddName.getItemAtPosition(spAddName.getSelectedItemPosition()).toString());
                    //ref.child(thing).push().setValue(model_no);
                    //ref.child(thing).push().setValue(sr_no);
                    //ref.child(thing).push().setValue(desc);

                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    private void findviews() {
        spAddName = (Spinner) findViewById(R.id.spAddName);
        edtModelno = (EditText) findViewById(R.id.edtModelno);
        edtSrno = (EditText) findViewById(R.id.edtSrNo);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        edtDis = (EditText) findViewById(R.id.edtDis);
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
