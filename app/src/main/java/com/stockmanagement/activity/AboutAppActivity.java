package com.stockmanagement.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.stockmanagement.R;


public class AboutAppActivity extends AppCompatActivity implements View.OnClickListener {

    private static Activity activity;
    private RelativeLayout rlAboutApp;
    private FloatingActionButton fabShare, fabHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        activity = AboutAppActivity.this;

        final FloatingActionMenu fabPlus = (FloatingActionMenu) findViewById(R.id.fabPlus);
        fabHome = (FloatingActionButton) findViewById(R.id.fabHome);
        fabShare = (FloatingActionButton) findViewById(R.id.fabShare);

        fabHome.setOnClickListener(this);
        fabShare.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.fabShare:
                fabHome.setImageResource(R.drawable.ic_home_unselected);
                fabShare.setImageResource(R.drawable.ic_share_selected);
                intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text));
                intent.setType("text/plain");
                startActivity(intent);
                break;

            case R.id.fabHome:
                fabShare.setImageResource(R.drawable.ic_share);
                fabHome.setImageResource(R.drawable.ic_home);
                intent = new Intent(activity, AfterSplashActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
