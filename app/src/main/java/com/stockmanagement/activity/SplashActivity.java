package com.stockmanagement.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.stockmanagement.R;
import com.stockmanagement.common.Preferences;


public class SplashActivity extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1500;
    private static Activity activity;
    final int PERMISSION_REQUEST_CODE = 111;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        activity = SplashActivity.this;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (!checkReadContactPermission() || !checkCameraPermission()
                            || !checkWriteExternalStorage() || !checkReadExternalStorage() ||
                            !checkSystemAlertWindowPermission()) {
                        requestPermission();

                    } else {
                        IntentActivity();
                    }
                } else {
                    IntentActivity();
                }
            }
        }, SPLASH_TIME_OUT);
    }

    private void IntentActivity() {
        // This method will be executed once the timer is over
        // Start your app main activity
        if (Preferences.getLOGINFLAG() != null) {
            if (Preferences.getLOGINFLAG().equals("login")) {
                Intent intent = new Intent(activity, HomeActivity.class);
                startActivity(intent);
                finish();
            } else if (Preferences.getLOGINFLAG().equals("logout")) {
                Intent i = new Intent(activity, AfterSplashActivity.class);
                startActivity(i);
                finish();
            }


        } else {
            Intent i = new Intent(activity, AfterSplashActivity.class);
            startActivity(i);
            finish();
        }
//        } else if () {
//        }
//        Intent i = new Intent(activity, AfterSplashActivity.class);
//        startActivity(i);
//        finish();
    }

    private void requestPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_CONTACTS,
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_CONTACTS,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                PERMISSION_REQUEST_CODE);
//        }
    }

    private boolean checkReadContactPermission() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkWriteContactPermission() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_CONTACTS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkCameraPermission() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkWriteExternalStorage() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkReadExternalStorage() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkSystemAlertWindowPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.SYSTEM_ALERT_WINDOW);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    IntentActivity();
                } else {
                    finish();
                }
                break;
        }
    }
}
