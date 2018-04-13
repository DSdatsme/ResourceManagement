package com.stockmanagement.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.stockmanagement.R;
import com.stockmanagement.common.DBHelper_Occupied;
import com.stockmanagement.common.Preferences;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class OccupiedItemDetailActivity extends BaseActivity {

    private TextView tvItemName, tvIdDevelop, tvIdDesign, tvIdSEO,
            tvIdTesting, tvIdBDE, tvIdHR, tvIdArtistic, tvIdAccounts;
    private static Activity activity;
    private String name, qty;
    private Toolbar toolbar;
    private int j = 0, part = 8, number = 0;
    private ArrayList<Integer> c = new ArrayList<>();

    DBHelper_Occupied mydb_DbHelper_occupied;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_occupied_details);
        activity = OccupiedItemDetailActivity.this;
        findviews();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Item Details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent itemintent = getIntent();
        name = itemintent.getStringExtra("itemname");
        qty = itemintent.getStringExtra("qty");
        Log.e("OccupiedItemDetail", "::>> " + qty);
        number = Integer.parseInt(qty);
        mydb_DbHelper_occupied = new DBHelper_Occupied(this);
//        //random division...........
//        int num = number;
//        int count = part; //where N some number (from 1 to 20)
//        int val = (int) Math.floor(num / count);
//        int sum = 0;
//        int max = val;
//        for (int r = 0; r < count; r++) {
//            c.add(val);
//            sum += c.get(r);
//        }
//        int temp = c.get(0) + num - sum;
//        int r = 1;
//        while (temp > max) {
//            if (r >= c.size())
//                r = 1;
//            temp -= 1;
//            c.set(r, c.get(r) + 1);
//            if (max < c.get(r))
//                max = c.get(r);
//            r++;
//        }
//        c.set(0, temp);
//        System.out.println(c);
//        Log.e("OccupiedItemDetails", "::>>" + c);
        tvItemName.setText(name);
        if (mydb_DbHelper_occupied.getAll().size() != 0) {

            // int key = mydb.getAllCotacts("Keyboard").size();
            //  int mou = mydb.getAllCotacts("Mouse").size();
            //    int mac = mydb.getAllCotacts("Mac Mini").size();
            //  int cpu = mydb.getAllCotacts("CPU").size();
            //  int usb = mydb.getAllCotacts("USB Cables").size();
            //  int head = mydb.getAllCotacts("HeadPhones").size();
            //  int print = mydb.getAllCotacts("Printer").size();
            //Log.d("hashmap data", "id = " + mydb_aAvailable.getAlldata().get("id"));
            // Log.d("hashmap data", "name =" + mydb_aAvailable.getAlldata().get("name"));
            if (!mydb_DbHelper_occupied.getAllCotactslist(name,"Development").isEmpty()) {
                int mon = mydb_DbHelper_occupied.getAllCotactslist(name,"Development").size();
                tvIdDevelop.setText((mon) + "");
            } else {
                tvIdDevelop.setText(0 + "");
            }
            if (!mydb_DbHelper_occupied.getAllCotactslist(name,"Designing").isEmpty()) {
                int key = mydb_DbHelper_occupied.getAllCotactslist(name,"Designing").size();
                tvIdDesign.setText((key) + "");
            } else {
                tvIdDesign.setText(0 + "");
            }
            if (!mydb_DbHelper_occupied.getAllCotactslist(name,"SEO").isEmpty()) {
                int mou = mydb_DbHelper_occupied.getAllCotactslist(name,"SEO").size();
                tvIdSEO.setText((mou) + "");
            } else {
                tvIdSEO.setText(0 + "");
            }
            if (!mydb_DbHelper_occupied.getAllCotactslist(name,"Testing").isEmpty()) {
                int mac = mydb_DbHelper_occupied.getAllCotactslist(name,"Testing").size();
                tvIdTesting.setText((mac) + "");
            } else {
                tvIdTesting.setText(0 + "");
            }
            if (!mydb_DbHelper_occupied.getAllCotactslist(name,"BDE").isEmpty()) {
                int cpu = mydb_DbHelper_occupied.getAllCotactslist(name,"BDE").size();
                tvIdBDE.setText((cpu) + "");
            } else {
                tvIdBDE.setText(0 + "");
            }
            if (!mydb_DbHelper_occupied.getAllCotactslist(name,"HR").isEmpty()) {
                int usb = mydb_DbHelper_occupied.getAllCotactslist(name,"HR").size();
                tvIdHR.setText((usb) + "");
            } else {
                tvIdHR.setText(0 + "");
            }
            if (!mydb_DbHelper_occupied.getAllCotactslist(name,"Artistic Work").isEmpty()) {
                int head = mydb_DbHelper_occupied.getAllCotactslist(name,"Artistic Work").size();
                tvIdArtistic.setText((head) + "");
            } else {
                tvIdArtistic.setText(0 + "");
            }
            if (!mydb_DbHelper_occupied.getAllCotactslist(name,"Accounts").isEmpty()) {
                int print = mydb_DbHelper_occupied.getAllCotactslist(name,"Accounts").size();
                tvIdAccounts.setText((print) + "");
            } else {
                tvIdAccounts.setText(0 + "");
            }
        } else {
            tvIdDevelop.setText(0 + "");
            tvIdDesign.setText(0 + "");
            tvIdSEO.setText(0 + "");
            tvIdTesting.setText(0 + "");
            tvIdBDE.setText(0 + "");
            tvIdHR.setText(0 + "");
            tvIdArtistic.setText(0 + "");
            tvIdAccounts.setText(0 + "");
        }

//        if (name.equals("Monitor")) {
//            tvItemName.setText(name);
//            /*for (int i = 0; i <= c.size(); i++) {
//                Log.e("for", "::>> " + c.get(i));
//                int value = c.get(i);
//                Log.e("value","::>> " + value);
//                if (i == 0)
//                    tvIdDevelop.setText(String.valueOf(value));
//                else if (i == 1)
//                    tvIdDesign.setText(String.valueOf(value));
//                else if (i == 2)
//                    tvIdTesting.setText(String.valueOf(value));
//                else if (i == 3)
//                    tvIdAccounts.setText(String.valueOf(value));
//                else if (i == 4)
//                    tvIdHR.setText(String.valueOf(value));
//                else if (i == 5)
//                    tvIdSEO.setText(String.valueOf(value));
//                else if (i == 6)
//                    tvIdBDE.setText(String.valueOf(value));
//                else if (i == 7)
//                    tvIdArtistic.setText(String.valueOf(value));
//            }*/
//
//            j = Integer.parseInt(Preferences.getOccupiedmonitor());
//            for (int i = 1; i <= Preferences.getOccupiedmonitor().length(); i++) {
//                if (i == j / 2) {
//                    tvIdDevelop.setText("1");
//                    tvIdDesign.setText("1");
//                    tvIdTesting.setText("1");
//                    tvIdAccounts.setText("1");
//                    tvIdHR.setText("1");
//                    tvIdSEO.setText("0");
//                    tvIdBDE.setText("1");
//                    tvIdArtistic.setText("1");
//                } else {
//                    tvIdDevelop.setText("2");
//                    tvIdDesign.setText("2");
//                    tvIdTesting.setText("2");
//                    tvIdAccounts.setText("2");
//                    tvIdHR.setText("2");
//                    tvIdSEO.setText("1");
//                    tvIdBDE.setText("2");
//                    tvIdArtistic.setText("2");
//                }
//            }
//        } else if (name.equals("Keyboard")) {
//            tvItemName.setText(name);
//            for (int i = 1; i <= Preferences.getOccupiedkeyboard().length(); i++) {
//                if (i == j / 2) {
//                    tvIdDevelop.setText("1");
//                    tvIdDesign.setText("1");
//                    tvIdTesting.setText("1");
//                    tvIdAccounts.setText("1");
//                    tvIdHR.setText("1");
//                    tvIdSEO.setText("0");
//                    tvIdBDE.setText("1");
//                    tvIdArtistic.setText("1");
//                } else {
//                    tvIdDevelop.setText("2");
//                    tvIdDesign.setText("2");
//                    tvIdTesting.setText("2");
//                    tvIdAccounts.setText("2");
//                    tvIdHR.setText("2");
//                    tvIdSEO.setText("1");
//                    tvIdBDE.setText("2");
//                    tvIdArtistic.setText("2");
//                }
//            }
//        } else if (name.equals("Mouse")) {
//            tvItemName.setText(name);
//            for (int i = 1; i <= Preferences.getOccupiedmouse().length(); i++) {
//                if (i == j / 2) {
//                    tvIdDevelop.setText("2");
//                    tvIdDesign.setText("1");
//                    tvIdTesting.setText("1");
//                    tvIdAccounts.setText("0");
//                    tvIdHR.setText("1");
//                    tvIdSEO.setText("0");
//                    tvIdBDE.setText("1");
//                    tvIdArtistic.setText("0");
//                } else {
//                    tvIdDevelop.setText("3");
//                    tvIdDesign.setText("2");
//                    tvIdTesting.setText("3");
//                    tvIdAccounts.setText("2");
//                    tvIdHR.setText("2");
//                    tvIdSEO.setText("1");
//                    tvIdBDE.setText("2");
//                    tvIdArtistic.setText("1");
//                }
//            }
//        } else if (name.equals("CPU")) {
//            tvItemName.setText(name);
//            for (int i = 1; i <= Preferences.getOccupiedcpu().length(); i++) {
//                if (i == j / 2) {
//                    tvIdDevelop.setText("1");
//                    tvIdDesign.setText("1");
//                    tvIdTesting.setText("1");
//                    tvIdAccounts.setText("1");
//                    tvIdHR.setText("1");
//                    tvIdSEO.setText("1");
//                    tvIdBDE.setText("1");
//                    tvIdArtistic.setText("1");
//                } else {
//                    tvIdDevelop.setText("2");
//                    tvIdDesign.setText("2");
//                    tvIdTesting.setText("3");
//                    tvIdAccounts.setText("2");
//                    tvIdHR.setText("2");
//                    tvIdSEO.setText("1");
//                    tvIdBDE.setText("2");
//                    tvIdArtistic.setText("1");
//                }
//            }
//        } else if (name.equals("Mac Mini")) {
//            tvItemName.setText(name);
//            for (int i = 1; i <= Preferences.getOccupiedmacmini().length(); i++) {
//                if (i == j / 2) {
//                    tvIdDevelop.setText("1");
//                    tvIdDesign.setText("1");
//                    tvIdTesting.setText("0");
//                    tvIdAccounts.setText("1");
//                    tvIdHR.setText("1");
//                    tvIdSEO.setText("0");
//                    tvIdBDE.setText("1");
//                    tvIdArtistic.setText("0");
//                } else {
//                    tvIdDevelop.setText("4");
//                    tvIdDesign.setText("1");
//                    tvIdTesting.setText("2");
//                    tvIdAccounts.setText("1");
//                    tvIdHR.setText("2");
//                    tvIdSEO.setText("2");
//                    tvIdBDE.setText("1");
//                    tvIdArtistic.setText("2");
//                }
//            }
//        } else if (name.equals("USB Cables")) {
//            tvItemName.setText(name);
//            for (int i = 1; i <= Preferences.getOccupiedusb().length(); i++) {
//                if (i == j / 2) {
//                    tvIdDevelop.setText("1");
//                    tvIdDesign.setText("1");
//                    tvIdTesting.setText("1");
//                    tvIdAccounts.setText("1");
//                    tvIdHR.setText("1");
//                    tvIdSEO.setText("0");
//                    tvIdBDE.setText("1");
//                    tvIdArtistic.setText("1");
//                } else {
//                    tvIdDevelop.setText("7");
//                    tvIdDesign.setText("1");
//                    tvIdTesting.setText("1");
//                    tvIdAccounts.setText("1");
//                    tvIdHR.setText("2");
//                    tvIdSEO.setText("1");
//                    tvIdBDE.setText("2");
//                    tvIdArtistic.setText("0");
//                }
//            }
//        } else if (name.equals("HeadPhones")) {
//            tvItemName.setText(name);
//            for (int i = 1; i <= Preferences.getOccupiedheadphone().length(); i++) {
//                if (i == j / 2) {
//                    tvIdDevelop.setText("1");
//                    tvIdDesign.setText("1");
//                    tvIdTesting.setText("1");
//                    tvIdAccounts.setText("1");
//                    tvIdHR.setText("1");
//                    tvIdSEO.setText("0");
//                    tvIdBDE.setText("1");
//                    tvIdArtistic.setText("1");
//                } else {
//                    tvIdDevelop.setText("2");
//                    tvIdDesign.setText("5");
//                    tvIdTesting.setText("1");
//                    tvIdAccounts.setText("1");
//                    tvIdHR.setText("1");
//                    tvIdSEO.setText("1");
//                    tvIdBDE.setText("2");
//                    tvIdArtistic.setText("2");
//                }
//            }
//        } else if (name.equals("Printer")) {
//            tvItemName.setText(name);
//            for (int i = 1; i <= Preferences.getOccupiedprinter().length(); i++) {
//                if (i == j / 2) {
//                    tvIdDevelop.setText("0");
//                    tvIdDesign.setText("0");
//                    tvIdTesting.setText("0");
//                    tvIdAccounts.setText("0");
//                    tvIdHR.setText("1");
//                    tvIdSEO.setText("0");
//                    tvIdBDE.setText("0");
//                    tvIdArtistic.setText("0");
//                } else {
//                    tvIdDevelop.setText("0");
//                    tvIdDesign.setText("0");
//                    tvIdTesting.setText("0");
//                    tvIdAccounts.setText("0");
//                    tvIdHR.setText("1");
//                    tvIdSEO.setText("0");
//                    tvIdBDE.setText("0");
//                    tvIdArtistic.setText("0");
//                }
//            }
//        }

    }

    private void findviews() {
        tvItemName = (TextView) findViewById(R.id.tvItemName);
        tvIdDevelop = (TextView) findViewById(R.id.tvIdDevelop);
        tvIdDesign = (TextView) findViewById(R.id.tvIdDesign);
        tvIdSEO = (TextView) findViewById(R.id.tvIdSEO);
        tvIdTesting = (TextView) findViewById(R.id.tvIdTesting);
        tvIdBDE = (TextView) findViewById(R.id.tvIdBDE);
        tvIdHR = (TextView) findViewById(R.id.tvIdHR);
        tvIdArtistic = (TextView) findViewById(R.id.tvIdArtistic);
        tvIdAccounts = (TextView) findViewById(R.id.tvIdAccounts);

    }

    /*public double[] divideUniformlyRandomly(double number, int part) {
        double uniformRandoms[] = new double[part];
        Random random = new Random();

        double mean = number / part;
        double sum = 0.0;

        for (int i = 0; i < part / 2; i++) {
            uniformRandoms[i] = random.nextDouble() * mean;

            uniformRandoms[part - i - 1] = mean + random.nextDouble() * mean;

            sum += uniformRandoms[i] + uniformRandoms[part - i - 1];
        }
        uniformRandoms[(int) Math.ceil(part / 2)] = uniformRandoms[(int) Math.ceil(part / 2)] + number - sum;

        Log.e("OccupiedItems",":divideuniformrandomly:>>" + uniformRandoms.toString());
        return uniformRandoms;
    }*/

    /*static public BigDecimal[] split(BigDecimal sum, int prec, int count) {
        int s = sum.scaleByPowerOfTen(prec).intValue();
        Random r = new Random();
        BigDecimal[] result = new BigDecimal[count];
        int[] v = new int[count];

        for (int i = 0; i < count - 1; i++)
            v[i] = r.nextInt(s);
        v[count - 1] = s;

        Arrays.sort(v);
        result[0] = BigDecimal.valueOf(v[0]).scaleByPowerOfTen(-prec);
        for (int i = 1; i < count; i++) {
            result[i] = BigDecimal.valueOf(v[i] - v[i - 1]).scaleByPowerOfTen(-prec);
            Log.e("OIDA","::::>> " + result[i]);
        }
        return result;
    }*/


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
