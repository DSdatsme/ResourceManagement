package com.stockmanagement.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.stockmanagement.R;
import com.stockmanagement.activity.OccupiedItemDetailActivity;
import com.stockmanagement.common.Preferences;


public class OccupiedFragment extends Fragment implements View.OnClickListener {

    private Button btnUsedMonitor, btnUsedKeyboard, btnUsedMouse, btnUsedCpu,
            btnUsedMacMini, btnUsedUsb, btnUsedHeadPhones, btnUsedPrinter;

    public OccupiedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_occupied, container, false);

        btnUsedMonitor = (Button) view.findViewById(R.id.btnUsedMonitor);
        btnUsedKeyboard = (Button) view.findViewById(R.id.btnUsedKeyboard);
        btnUsedMouse = (Button) view.findViewById(R.id.btnUsedMouse);
        btnUsedCpu = (Button) view.findViewById(R.id.btnUsedCpu);
        btnUsedMacMini = (Button) view.findViewById(R.id.btnUsedMacMini);
        btnUsedUsb = (Button) view.findViewById(R.id.btnUsedUsb);
        btnUsedHeadPhones = (Button) view.findViewById(R.id.btnUsedHeadPhones);
        btnUsedPrinter = (Button) view.findViewById(R.id.btnUsedPrinter);

        btnUsedMonitor.setOnClickListener(this);
        btnUsedKeyboard.setOnClickListener(this);
        btnUsedMouse.setOnClickListener(this);
        btnUsedCpu.setOnClickListener(this);
        btnUsedMacMini.setOnClickListener(this);
        btnUsedUsb.setOnClickListener(this);
        btnUsedHeadPhones.setOnClickListener(this);
        btnUsedPrinter.setOnClickListener(this);

        if (Preferences.getOccupiedmonitor() == null)
            Preferences.setOccupiedmonitor("15");
        if (Preferences.getOccupiedkeyboard() == null)
            Preferences.setOccupiedkeyboard("15");
        if (Preferences.getOccupiedmouse() == null)
            Preferences.setOccupiedmouse("15");
        if (Preferences.getOccupiedcpu() == null)
            Preferences.setOccupiedcpu("12");
        if (Preferences.getOccupiedmacmini() == null)
            Preferences.setOccupiedmacmini("5");
        if (Preferences.getOccupiedusb() == null)
            Preferences.setOccupiedusb("8");
        if (Preferences.getOccupiedheadphone() == null)
            Preferences.setOccupiedheadphone("3");
        if (Preferences.getOccupiedprinter() == null)
            Preferences.setOccupiedprinter("1");
        Log.e("OccupiedFrag", "::>>>");

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnUsedMonitor:
                intent = new Intent(getActivity(), OccupiedItemDetailActivity.class);
                intent.putExtra("itemname", "Monitor");
                if (Preferences.getOccupiedmonitor() != null)
                    intent.putExtra("qty", Preferences.getOccupiedmonitor());
                else
                    intent.putExtra("qty", "15");
                Preferences.setOccupiedmonitor("15");
                Preferences.setOccupiedkeyboard("15");
                Preferences.setOccupiedmouse("15");
                Preferences.setOccupiedcpu("12");
                Preferences.setOccupiedmacmini("5");
                Preferences.setOccupiedusb("8");
                Preferences.setOccupiedheadphone("3");
                Preferences.setOccupiedprinter("1");
                startActivity(intent);
                break;

            case R.id.btnUsedKeyboard:
                intent = new Intent(getActivity(), OccupiedItemDetailActivity.class);
                intent.putExtra("itemname", "Keyboard");
                if (Preferences.getOccupiedkeyboard() != null)
                    intent.putExtra("qty", Preferences.getOccupiedkeyboard());
                else
                    intent.putExtra("qty", "15");
                Preferences.setOccupiedmonitor("15");
                Preferences.setOccupiedkeyboard("15");
                Preferences.setOccupiedmouse("15");
                Preferences.setOccupiedcpu("12");
                Preferences.setOccupiedmacmini("5");
                Preferences.setOccupiedusb("8");
                Preferences.setOccupiedheadphone("3");
                Preferences.setOccupiedprinter("1");
                startActivity(intent);
                break;

            case R.id.btnUsedMouse:
                intent = new Intent(getActivity(), OccupiedItemDetailActivity.class);
                intent.putExtra("itemname", "Mouse");
                if (Preferences.getOccupiedmouse() != null)
                    intent.putExtra("qty", Preferences.getOccupiedmouse());
                else
                    intent.putExtra("qty", "15");
                Preferences.setOccupiedmonitor("15");
                Preferences.setOccupiedkeyboard("15");
                Preferences.setOccupiedmouse("15");
                Preferences.setOccupiedcpu("12");
                Preferences.setOccupiedmacmini("5");
                Preferences.setOccupiedusb("8");
                Preferences.setOccupiedheadphone("3");
                Preferences.setOccupiedprinter("1");
                startActivity(intent);
                break;

            case R.id.btnUsedCpu:
                intent = new Intent(getActivity(), OccupiedItemDetailActivity.class);
                intent.putExtra("itemname", "CPU");
                if (Preferences.getOccupiedcpu() != null)
                    intent.putExtra("qty", Preferences.getOccupiedcpu());
                else
                    intent.putExtra("qty", "12");
                Preferences.setOccupiedmonitor("15");
                Preferences.setOccupiedkeyboard("15");
                Preferences.setOccupiedmouse("15");
                Preferences.setOccupiedcpu("12");
                Preferences.setOccupiedmacmini("5");
                Preferences.setOccupiedusb("8");
                Preferences.setOccupiedheadphone("3");
                Preferences.setOccupiedprinter("1");
                startActivity(intent);
                break;

            case R.id.btnUsedMacMini:
                intent = new Intent(getActivity(), OccupiedItemDetailActivity.class);
                intent.putExtra("itemname", "Mac Mini");
                if (Preferences.getOccupiedmacmini() != null)
                    intent.putExtra("qty", Preferences.getOccupiedmacmini());
                else
                    intent.putExtra("qty", "3");
                Preferences.setOccupiedmonitor("15");
                Preferences.setOccupiedkeyboard("15");
                Preferences.setOccupiedmouse("15");
                Preferences.setOccupiedcpu("12");
                Preferences.setOccupiedmacmini("5");
                Preferences.setOccupiedusb("8");
                Preferences.setOccupiedheadphone("3");
                Preferences.setOccupiedprinter("1");
                startActivity(intent);
                break;

            case R.id.btnUsedUsb:
                intent = new Intent(getActivity(), OccupiedItemDetailActivity.class);
                intent.putExtra("itemname", "USB Cables");
                if (Preferences.getOccupiedusb() != null)
                    intent.putExtra("qty", Preferences.getOccupiedusb());
                else
                    intent.putExtra("qty", "12");
                Preferences.setOccupiedmonitor("15");
                Preferences.setOccupiedkeyboard("15");
                Preferences.setOccupiedmouse("15");
                Preferences.setOccupiedcpu("12");
                Preferences.setOccupiedmacmini("5");
                Preferences.setOccupiedusb("8");
                Preferences.setOccupiedheadphone("3");
                Preferences.setOccupiedprinter("1");
                startActivity(intent);
                break;

            case R.id.btnUsedHeadPhones:
                intent = new Intent(getActivity(), OccupiedItemDetailActivity.class);
                intent.putExtra("itemname", "HeadPhones");
                if (Preferences.getOccupiedheadphone() != null)
                    intent.putExtra("qty", Preferences.getOccupiedheadphone());
                else
                    intent.putExtra("qty", "3");
                startActivity(intent);
                Preferences.setOccupiedmonitor("15");
                Preferences.setOccupiedkeyboard("15");
                Preferences.setOccupiedmouse("15");
                Preferences.setOccupiedcpu("12");
                Preferences.setOccupiedmacmini("5");
                Preferences.setOccupiedusb("8");
                Preferences.setOccupiedheadphone("3");
                Preferences.setOccupiedprinter("1");
                break;

            case R.id.btnUsedPrinter:
                intent = new Intent(getActivity(), OccupiedItemDetailActivity.class);
                intent.putExtra("itemname", "Printer");
                if (Preferences.getOccupiedprinter() != null)
                    intent.putExtra("qty", Preferences.getOccupiedprinter());
                else
                    intent.putExtra("qty", "1");
                Preferences.setOccupiedmonitor("15");
                Preferences.setOccupiedkeyboard("15");
                Preferences.setOccupiedmouse("15");
                Preferences.setOccupiedcpu("12");
                Preferences.setOccupiedmacmini("5");
                Preferences.setOccupiedusb("8");
                Preferences.setOccupiedheadphone("3");
                Preferences.setOccupiedprinter("1");
                startActivity(intent);
                break;

        }
    }

}
