package com.stockmanagement.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.stockmanagement.R;
import com.stockmanagement.common.DBHelper_Stock;
import com.stockmanagement.common.Preferences;


public class StockFragment extends Fragment {

    private TextView tvStockMonitor, tvStockKeyboard, tvStockMouse, tvStockCpu,
            tvStockMacMini, tvStockUsb, tvStockHeadPhones, tvStockPrinter;
    private int i = 0, monitor = 0, keyboard = 0, mouse = 0, cpu = 0, mac = 0, usb = 0, hp = 0, printer = 0;
    DBHelper_Stock mydb;

    public StockFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_stock, container, false);

        //Findviews.......
        tvStockMonitor = (TextView) view.findViewById(R.id.tvStockMonitor);
        tvStockKeyboard = (TextView) view.findViewById(R.id.tvStockKeyboard);
        tvStockMouse = (TextView) view.findViewById(R.id.tvStockMouse);
        tvStockCpu = (TextView) view.findViewById(R.id.tvStockCpu);
        tvStockMacMini = (TextView) view.findViewById(R.id.tvStockMacMini);
        tvStockUsb = (TextView) view.findViewById(R.id.tvStockUsb);
        tvStockHeadPhones = (TextView) view.findViewById(R.id.tvStockHeadPhones);
        tvStockPrinter = (TextView) view.findViewById(R.id.tvStockPrinter);
        mydb = new DBHelper_Stock(getContext());
        if (mydb.getAll().size() != 0) {

            // int key = mydb.getAllCotacts("Keyboard").size();
            //  int mou = mydb.getAllCotacts("Mouse").size();
            //    int mac = mydb.getAllCotacts("Mac Mini").size();
            //  int cpu = mydb.getAllCotacts("CPU").size();
            //  int usb = mydb.getAllCotacts("USB Cables").size();
            //  int head = mydb.getAllCotacts("HeadPhones").size();
            //  int print = mydb.getAllCotacts("Printer").size();
            if (!mydb.getAllCotacts("Monitor").isEmpty()) {
                int mon = mydb.getAllCotacts("Monitor").size();
                tvStockMonitor.setText((mon) + "");
            } else {
                tvStockMonitor.setText(0 + "");
            }
            if (!mydb.getAllCotacts("Keyboard").isEmpty()) {
                int key = mydb.getAllCotacts("Keyboard").size();
                tvStockKeyboard.setText((key) + "");
            } else {
                tvStockKeyboard.setText(0 + "");
            }
            if (!mydb.getAllCotacts("Mouse").isEmpty()) {
                int mou = mydb.getAllCotacts("Mouse").size();
                tvStockMouse.setText((mou) + "");
            } else {
                tvStockMouse.setText(0 + "");
            }
            if (!mydb.getAllCotacts("Mac Mini").isEmpty()) {
                int mac = mydb.getAllCotacts("Mac Mini").size();
                tvStockMacMini.setText((mac) + "");
            } else {
                tvStockMacMini.setText(0 + "");
            }
            if (!mydb.getAllCotacts("CPU").isEmpty()) {
                int cpu = mydb.getAllCotacts("CPU").size();
                tvStockCpu.setText((cpu) + "");
            } else {
                tvStockCpu.setText(0 + "");
            }
            if (!mydb.getAllCotacts("USB Cables").isEmpty()) {
                int usb = mydb.getAllCotacts("USB Cables").size();
                tvStockUsb.setText((usb) + "");
            } else {
                tvStockUsb.setText(0 + "");
            }
            if (!mydb.getAllCotacts("HeadPhones").isEmpty()) {
                int head = mydb.getAllCotacts("HeadPhones").size();
                tvStockHeadPhones.setText((head) + "");
            } else {
                tvStockHeadPhones.setText(0 + "");
            }
            if (!mydb.getAllCotacts("Printer").isEmpty()) {
                int print = mydb.getAllCotacts("Printer").size();
                tvStockPrinter.setText((print) + "");
            } else {
                tvStockPrinter.setText(0 + "");
            }
        } else {
            tvStockMonitor.setText(0 + "");
            tvStockKeyboard.setText(0 + "");
            tvStockMouse.setText(0 + "");
            tvStockMacMini.setText(0 + "");
            tvStockCpu.setText(0 + "");
            tvStockUsb.setText(0 + "");
            tvStockHeadPhones.setText(0 + "");
            tvStockPrinter.setText(0 + "");
        }
//        try {
//            if (Preferences.getAdditemflag() != null) {
//                if (Preferences.getAdditemflag().equals("1")) {
//                    tvStockMonitor.setText(Preferences.getAddtostockmonitor());
//                    tvStockKeyboard.setText(Preferences.getAddtostockkeyboard());
//                    tvStockMouse.setText(Preferences.getAddtostockmouse());
//                    tvStockCpu.setText(Preferences.getAddtostockcpu());
//                    tvStockMacMini.setText(Preferences.getAddtostockmacmini());
//                    tvStockUsb.setText(Preferences.getAddtostockusb());
//                    tvStockHeadPhones.setText(Preferences.getAddtostockheadphone());
//                    tvStockPrinter.setText(Preferences.getAddtostockprinter());
//                    Log.e("Stock", ":::>>Moni" + monitor);
//                    Preferences.setAdditemflag("0");
//                } else if (Preferences.getAdditemflag().equals("0")) {
//                    tvStockMonitor.setText(Preferences.getAddtostockmonitor());
//                    tvStockKeyboard.setText(Preferences.getAddtostockkeyboard());
//                    tvStockMouse.setText(Preferences.getAddtostockmouse());
//                    tvStockCpu.setText(Preferences.getAddtostockcpu());
//                    tvStockMacMini.setText(Preferences.getAddtostockmacmini());
//                    tvStockUsb.setText(Preferences.getAddtostockusb());
//                    tvStockHeadPhones.setText(Preferences.getAddtostockheadphone());
//                    tvStockPrinter.setText(Preferences.getAddtostockprinter());
//                    Log.e("StockFrag", ":::>> else if part 1");
//                }
//            } else if (Preferences.getTransitemflag() != null) {
//                if (Preferences.getTransitemflag() != null) {
//                    if (Preferences.getTransitemflag().equals("1")) {
//                        tvStockMonitor.setText(Preferences.getAddtostockmonitor());
//                        tvStockKeyboard.setText(Preferences.getAddtostockkeyboard());
//                        tvStockMouse.setText(Preferences.getAddtostockmouse());
//                        tvStockCpu.setText(Preferences.getAddtostockcpu());
//                        tvStockMacMini.setText(Preferences.getAddtostockmacmini());
//                        tvStockUsb.setText(Preferences.getAddtostockusb());
//                        tvStockHeadPhones.setText(Preferences.getAddtostockheadphone());
//                        tvStockPrinter.setText(Preferences.getAddtostockprinter());
//                        Log.e("StockFrag", ":::>> else if part 2");
//                    }
//                }
//            } else {
//                    Log.e("StockFrag", ":::>> else part");
//                    if (Preferences.getAvailablemonitor() != null && Preferences.getOccupiedmonitor() != null)
//                        monitor = Integer.parseInt(Preferences.getAvailablemonitor()) - Integer.parseInt(Preferences.getOccupiedmonitor());
//                    Log.e("StockFrag", "moni:>> " + Preferences.getAvailablemonitor() + Preferences.getOccupiedmonitor());
//                    if (Preferences.getAvailablekeyboard() != null && Preferences.getOccupiedkeyboard() != null)
//                        keyboard = Integer.parseInt(Preferences.getAvailablekeyboard()) - Integer.parseInt(Preferences.getOccupiedkeyboard());
//                    Log.e("StockFrag", "moni:>> " + Preferences.getAvailablekeyboard() + Preferences.getOccupiedkeyboard());
//                    if (Preferences.getAvailablemouse() != null && Preferences.getOccupiedmouse() != null)
//                        mouse = Integer.parseInt(Preferences.getAvailablemouse()) - Integer.parseInt(Preferences.getOccupiedmouse());
//                    Log.e("StockFrag", "moni:>> " + Preferences.getAvailablemouse() + Preferences.getOccupiedmouse());
//                    if (Preferences.getAvailablecpu() != null && Preferences.getOccupiedcpu() != null)
//                        cpu = Integer.parseInt(Preferences.getAvailablecpu()) - Integer.parseInt(Preferences.getOccupiedcpu());
//                    Log.e("StockFrag", "moni:>> " + Preferences.getAvailablecpu() + Preferences.getOccupiedcpu());
//                    if (Preferences.getAvailablemacmini() != null && Preferences.getOccupiedmacmini() != null)
//                        mac = Integer.parseInt(Preferences.getAvailablemacmini()) - Integer.parseInt(Preferences.getOccupiedmacmini());
//                    Log.e("StockFrag", "moni:>> " + Preferences.getAvailablemacmini() + Preferences.getOccupiedmacmini());
//                    if (Preferences.getAvailableusb() != null && Preferences.getOccupiedusb() != null)
//                        usb = Integer.parseInt(Preferences.getAvailableusb()) - Integer.parseInt(Preferences.getOccupiedusb());
//                    Log.e("StockFrag", "moni:>> " + Preferences.getAvailableusb() + Preferences.getOccupiedusb());
//                    if (Preferences.getAvailableheadphone() != null && Preferences.getOccupiedheadphone() != null)
//                        hp = Integer.parseInt(Preferences.getAvailableheadphone()) - Integer.parseInt(Preferences.getOccupiedheadphone());
//                    Log.e("StockFrag", "moni:>> " + Preferences.getAvailableheadphone() + Preferences.getOccupiedheadphone());
//                    if (Preferences.getAvailableprinter() != null && Preferences.getOccupiedprinter() != null)
//                        printer = Integer.parseInt(Preferences.getAvailableprinter()) - Integer.parseInt(Preferences.getOccupiedprinter());
//                    Log.e("StockFrag", "moni:>> " + Preferences.getAvailableprinter() + Preferences.getOccupiedprinter());
//
//                    Preferences.setAddtostockmonitor(String.valueOf(monitor));
//                    Preferences.setAddtostockkeyboard(String.valueOf(keyboard));
//                    Preferences.setAddtostockmouse(String.valueOf(mouse));
//                    Preferences.setAddtostockcpu(String.valueOf(cpu));
//                    Preferences.setAddtostockmacmini(String.valueOf(mac));
//                    Preferences.setAddtostockusb(String.valueOf(usb));
//                    Preferences.setAddtostockheadphone(String.valueOf(hp));
//                    Preferences.setAddtostockprinter(String.valueOf(printer));
//
//                    tvStockMonitor.setText(Preferences.getAddtostockmonitor());
//                    tvStockKeyboard.setText(Preferences.getAddtostockkeyboard());
//                    tvStockMouse.setText(Preferences.getAddtostockmouse());
//                    tvStockCpu.setText(Preferences.getAddtostockcpu());
//                    tvStockMacMini.setText(Preferences.getAddtostockmacmini());
//                    tvStockUsb.setText(Preferences.getAddtostockusb());
//                    tvStockHeadPhones.setText(Preferences.getAddtostockheadphone());
//                    tvStockPrinter.setText(Preferences.getAddtostockprinter());
//                    Log.e("Stock", ":::>>Moni" + monitor);
//                }
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        }

        return view;
    }

}
