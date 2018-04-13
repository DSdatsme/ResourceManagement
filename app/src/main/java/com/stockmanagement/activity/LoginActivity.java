package com.stockmanagement.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stockmanagement.R;
import com.stockmanagement.common.Preferences;
import com.stockmanagement.common.Utils;


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static Activity activity;
    private EditText edtLoginEmail, edtLoginPassword;
    private TextView tvLoginForgotPassword;
    private Button btnLogin;
    private ImageButton ibtnBack;
    String email, pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activity = LoginActivity.this;
        findviews();

        edtLoginEmail.setText(Preferences.getEmail());
        tvLoginForgotPassword.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        ibtnBack.setOnClickListener(this);
        email = Preferences.getEmail();
        pass = Preferences.getPassword();
    }

    private void findviews() {
        edtLoginEmail = (EditText) findViewById(R.id.edtLoginEmail);
        edtLoginPassword = (EditText) findViewById(R.id.edtLoginPassword);
        tvLoginForgotPassword = (TextView) findViewById(R.id.tvLoginForgotPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        ibtnBack = (ImageButton) findViewById(R.id.ibtnBack);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnLogin:
                if (Utils.isEmpty(edtLoginEmail.getText().toString().trim()) || edtLoginEmail.length() <= 0) {
                    Utils.showAlert(activity, getString(R.string.app_name), getString(R.string.alert_email));
                } else if (!Utils.isValidEmail(edtLoginEmail.getText().toString().trim())) {
                    Utils.showAlert(activity, getString(R.string.app_name), getString(R.string.alert_valid_email));
                } else if (edtLoginPassword.length() < 5) {
                    Utils.showAlert(activity, getString(R.string.app_name), getString(R.string.alert_password_length));
                } else if (email.equals(edtLoginEmail.getText().toString()) && pass.equals(edtLoginPassword.getText().toString())) {
                    Preferences.setLoginflag("login");
                    intent = new Intent(activity, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else if (!email.equals(edtLoginEmail.getText().toString())) {
                    Utils.showAlert(activity, getString(R.string.app_name), "Please Enter Right Email-id ");
                } else if (!pass.equals(edtLoginPassword.getText().toString())) {
                    Utils.showAlert(activity, getString(R.string.app_name), "Please Enter Right Password");
                }
                break;

            case R.id.tvLoginForgotPassword:
                intent = new Intent(activity, ForgotPasswordActivity.class);
                startActivity(intent);
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
