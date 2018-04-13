package com.stockmanagement.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.stockmanagement.R;


public class AfterSplashActivity extends AppCompatActivity implements View.OnClickListener {

    private static Activity activity;
    private TextView tvAboutApp, tvRegister, tvLogin;
    private ImageView ivAboutApp, ivLogin, ivRegister, ivLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_splash);
        activity = AfterSplashActivity.this;
        findviews();

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade);
        ivLogo.startAnimation(animation);

        ivAboutApp.setOnClickListener(this);
        ivRegister.setOnClickListener(this);
        ivLogin.setOnClickListener(this);

    }

    private void findviews() {
        ivAboutApp = (ImageView) findViewById(R.id.ivAboutApp);
        ivLogin = (ImageView) findViewById(R.id.ivLogin);
        ivRegister = (ImageView) findViewById(R.id.ivRegister);
        ivLogo = (ImageView) findViewById(R.id.ivLogo);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ivAboutApp:
                ivRegister.setImageResource(R.drawable.ic_reg);
                ivLogin.setImageResource(R.drawable.ic_login_unselected);
                ivAboutApp.setImageResource(R.drawable.ic_abt_selected);
                intent = new Intent(activity, AboutAppActivity.class);
                startActivity(intent);
                break;

            case R.id.ivRegister:
                ivAboutApp.setImageResource(R.drawable.ic_about);
                ivLogin.setImageResource(R.drawable.ic_login_unselected);
                ivRegister.setImageResource(R.drawable.ic_reg_selected);
                intent = new Intent(activity, RegistrationActivity.class);
                startActivity(intent);
                break;

            case R.id.ivLogin:
                ivAboutApp.setImageResource(R.drawable.ic_about);
                ivRegister.setImageResource(R.drawable.ic_reg);
                ivLogin.setImageResource(R.drawable.ic_login);
                intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
                break;

        }
    }

    public void onBackPressed() {
        //  super.onBackPressed();
        moveTaskToBack(true);

    }
}
