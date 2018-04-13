package com.stockmanagement.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.stockmanagement.StockManagementApplication;


public class Preferences {
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String COUNT = "count";
    private static final String LOGINFLAG = "logout";
    private static final String ADDITEMFLAG = "add_item_flag";
    private static final String TRANSITEMFLAG = "trans_flag";

    //Stock.........
    private static final String ADDTOSTOCKMONITOR = "add_to_stock_monitor";
    private static final String ADDTOSTOCKKEYBOARD = "add_to_stock_keyboard";
    private static final String ADDTOSTOCKMOUSE = "add_to_stock_mouse";
    private static final String ADDTOSTOCKCPU = "add_to_stock_cpu";
    private static final String ADDTOSTOCKMACMINI = "add_to_stock_macmini";
    private static final String ADDTOSTOCKUSB = "add_to_stock_usb";
    private static final String ADDTOSTOCKHEADPHONE = "add_to_stock_headphone";
    private static final String ADDTOSTOCKPRINTER = "add_to_stock_printer";


    //Available.....
    private static final String AVAILABLEMONITOR = "available_monitor";
    private static final String AVAILABLEKEYBOARD = "available_keyboard";
    private static final String AVAILABLEMOUSE = "available_mouse";
    private static final String AVAILABLECPU = "available_cpu";
    private static final String AVAILABLEMACMINI = "available_macmini";
    private static final String AVAILABLEUSB = "available_usb";
    private static final String AVAILABLEHEADPHONE = "available_headphone";
    private static final String AVAILABLEPRINTER = "available_printer";

    //Occupied.....
    private static final String OCCUPIEDMONITOR = "occupied_monitor";
    private static final String OCCUPIEDKEYBOARD = "occupied_keyboard";
    private static final String OCCUPIEDMOUSE = "occupied_mouse";
    private static final String OCCUPIEDCPU = "occupied_cpu";
    private static final String OCCUPIEDMACMINI = "occupied_macmini";
    private static final String OCCUPIEDUSB = "occupied_usb";
    private static final String OCCUPIEDHEADPHONE = "occupied_headphone";
    private static final String OCCUPIEDPRINTER = "occupied_printer";


    private static SharedPreferences get() {
        return StockManagementApplication.getAppContext().getSharedPreferences("SPYLockApplication", Context.MODE_PRIVATE);
    }

    public static String getEmail() {
        return get().getString(EMAIL, null);
    }

    public static void setEmail(String email) {
        get().edit().putString(EMAIL, email).commit();
    }

    public static String getLOGINFLAG() {
        return get().getString(LOGINFLAG, null);
    }

    public static void setLoginflag(String loginflag) {
        get().edit().putString(LOGINFLAG, loginflag).commit();
    }

    public static String getPassword() {
        return get().getString(PASSWORD, null);
    }

    public static void setPassword(String password) {
        get().edit().putString(PASSWORD, password).commit();
    }

    public static String getPhoneNumber() {
        return get().getString(PHONE_NUMBER, null);
    }

    public static void setPhoneNumber(String phoneNumber) {
        get().edit().putString(PHONE_NUMBER, phoneNumber).commit();
    }

    public static String getFirstName() {
        return get().getString(FIRST_NAME, null);
    }

    public static void setFirstName(String firstName) {
        get().edit().putString(FIRST_NAME, firstName).commit();
    }

    public static String getLastName() {
        return get().getString(LAST_NAME, null);
    }

    public static void setLastName(String lastName) {
        get().edit().putString(LAST_NAME, lastName).commit();
    }

    public static String getCount() {
        return get().getString(COUNT, null);
    }

    public static void setCount(String count) {
        get().edit().putString(COUNT, count).commit();
    }

    public static String getAddtostockkeyboard() {
        return get().getString(ADDTOSTOCKKEYBOARD, null);
    }

    public static void setAddtostockkeyboard(String addtostockkeyboard) {
        get().edit().putString(ADDTOSTOCKKEYBOARD, addtostockkeyboard).commit();
    }

    public static String getAddtostockmouse() {
        return get().getString(ADDTOSTOCKMOUSE, null);
    }

    public static void setAddtostockmouse(String addtostockmouse) {
        get().edit().putString(ADDTOSTOCKMOUSE, addtostockmouse).commit();
    }

    public static String getAddtostockcpu() {
        return get().getString(ADDTOSTOCKCPU, null);
    }

    public static void setAddtostockcpu(String addtostockcpu) {
        get().edit().putString(ADDTOSTOCKCPU, addtostockcpu).commit();
    }

    public static String getAddtostockmonitor() {
        return get().getString(ADDTOSTOCKMONITOR, null);
    }

    public static void setAddtostockmonitor(String addtostockmonitor) {
        get().edit().putString(ADDTOSTOCKMONITOR, addtostockmonitor).commit();
    }

    public static String getAddtostockmacmini() {
        return get().getString(ADDTOSTOCKMACMINI, null);
    }

    public static void setAddtostockmacmini(String addtostockmacmini) {
        get().edit().putString(ADDTOSTOCKMACMINI, addtostockmacmini).commit();
    }

    public static String getAddtostockusb() {
        return get().getString(ADDTOSTOCKUSB, null);
    }

    public static void setAddtostockusb(String addtostockusb) {
        get().edit().putString(ADDTOSTOCKUSB, addtostockusb).commit();
    }

    public static String getAddtostockheadphone() {
        return get().getString(ADDTOSTOCKHEADPHONE, null);
    }

    public static void setAddtostockheadphone(String addtostockheadphone) {
        get().edit().putString(ADDTOSTOCKHEADPHONE, addtostockheadphone).commit();
    }

    public static String getAddtostockprinter() {
        return get().getString(ADDTOSTOCKPRINTER, null);
    }

    public static void setAddtostockprinter(String addtostockprinter) {
        get().edit().putString(ADDTOSTOCKPRINTER, addtostockprinter).commit();
    }

    //Available..........
    public static String getAvailablekeyboard() {
        return get().getString(AVAILABLEKEYBOARD, null);
    }

    public static void setAvailablekeyboard(String availablekeyboard) {
        get().edit().putString(AVAILABLEKEYBOARD, availablekeyboard).commit();
    }

    public static String getAvailablemouse() {
        return get().getString(AVAILABLEMOUSE, null);
    }

    public static void setAvailablemouse(String availablemouse) {
        get().edit().putString(AVAILABLEMOUSE, availablemouse).commit();
    }

    public static String getAvailablecpu() {
        return get().getString(AVAILABLECPU, null);
    }

    public static void setAvailablecpu(String availablecpu) {
        get().edit().putString(AVAILABLECPU, availablecpu).commit();
    }

    public static String getAvailablemonitor() {
        return get().getString(AVAILABLEMONITOR, null);
    }

    public static void setAvailablemonitor(String availablemonitor) {
        get().edit().putString(AVAILABLEMONITOR, availablemonitor).commit();
    }

    public static String getAvailablemacmini() {
        return get().getString(AVAILABLEMACMINI, null);
    }

    public static void setAvailablemacmini(String availablemacmini) {
        get().edit().putString(AVAILABLEMACMINI, availablemacmini).commit();
    }

    public static String getAvailableusb() {
        return get().getString(AVAILABLEUSB, null);
    }

    public static void setAvailableusb(String availableusb) {
        get().edit().putString(AVAILABLEUSB, availableusb).commit();
    }

    public static String getAvailableheadphone() {
        return get().getString(AVAILABLEHEADPHONE, null);
    }

    public static void setAvailableheadphone(String availableheadphone) {
        get().edit().putString(AVAILABLEHEADPHONE, availableheadphone).commit();
    }

    public static String getAvailableprinter() {
        return get().getString(AVAILABLEPRINTER, null);
    }

    public static void setAvailableprinter(String availableprinter) {
        get().edit().putString(AVAILABLEPRINTER, availableprinter).commit();
    }

    //Occupied........
    public static String getOccupiedkeyboard() {
        return get().getString(OCCUPIEDKEYBOARD, null);
    }

    public static void setOccupiedkeyboard(String occupiedkeyboard) {
        get().edit().putString(OCCUPIEDKEYBOARD, occupiedkeyboard).commit();
    }

    public static String getOccupiedmouse() {
        return get().getString(OCCUPIEDMOUSE, null);
    }

    public static void setOccupiedmouse(String occupiedmouse) {
        get().edit().putString(OCCUPIEDMOUSE, occupiedmouse).commit();
    }

    public static String getOccupiedcpu() {
        return get().getString(OCCUPIEDCPU, null);
    }

    public static void setOccupiedcpu(String occupiedcpu) {
        get().edit().putString(OCCUPIEDCPU, occupiedcpu).commit();
    }

    public static String getOccupiedmonitor() {
        return get().getString(OCCUPIEDMONITOR, null);
    }

    public static void setOccupiedmonitor(String occupiedmonitor) {
        get().edit().putString(OCCUPIEDMONITOR, occupiedmonitor).commit();
    }

    public static String getOccupiedmacmini() {
        return get().getString(OCCUPIEDMACMINI, null);
    }

    public static void setOccupiedmacmini(String occupiedmacmini) {
        get().edit().putString(OCCUPIEDMACMINI, occupiedmacmini).commit();
    }

    public static String getOccupiedusb() {
        return get().getString(OCCUPIEDUSB, null);
    }

    public static void setOccupiedusb(String occupiedusb) {
        get().edit().putString(OCCUPIEDUSB, occupiedusb).commit();
    }

    public static String getOccupiedheadphone() {
        return get().getString(OCCUPIEDHEADPHONE, null);
    }

    public static void setOccupiedheadphone(String occupiedheadphone) {
        get().edit().putString(OCCUPIEDHEADPHONE, occupiedheadphone).commit();
    }

    public static String getOccupiedprinter() {
        return get().getString(OCCUPIEDPRINTER, null);
    }

    public static void setOccupiedprinter(String occupiedprinter) {
        get().edit().putString(OCCUPIEDPRINTER, occupiedprinter).commit();
    }

    public static String getAdditemflag() {
        return get().getString(ADDITEMFLAG, null);
    }

    public static void setAdditemflag(String additemflag) {
        get().edit().putString(ADDITEMFLAG, additemflag).commit();
    }

    public static String getTransitemflag() {
        return get().getString(TRANSITEMFLAG, null);
    }

    public static void setTransitemflag(String transitemflag) {
        get().edit().putString(TRANSITEMFLAG, transitemflag).commit();
    }


}
