package com.stockmanagement.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stockmanagement.R;
import com.stockmanagement.common.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


public class ForgotPasswordActivity extends BaseActivity implements View.OnClickListener {

    private static Activity activity;
    private ImageButton ibtnBack;
    private EditText edtEmail;
    private Button btnConfirm;
    private TextView tvShare, tvHome;
    private RelativeLayout rlForgotPwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        activity = ForgotPasswordActivity.this;
        finviews();

        btnConfirm.setOnClickListener(this);
        ibtnBack.setOnClickListener(this);
    }

    private void finviews() {
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        ibtnBack = (ImageButton) findViewById(R.id.ibtnBack);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnConfirm:
                if (Utils.isEmpty(edtEmail.getText().toString().trim()) || edtEmail.length() <= 0)
                    Utils.showAlert(activity, getString(R.string.app_name), getString(R.string.alert_email));
                else if (!Utils.isValidEmail(edtEmail.getText().toString().trim()))
                    Utils.showAlert(activity, getString(R.string.app_name), getString(R.string.alert_valid_email));
                else {
                    finish();
                }
                break;

            case R.id.ibtnBack:
                finish();
                break;

        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
