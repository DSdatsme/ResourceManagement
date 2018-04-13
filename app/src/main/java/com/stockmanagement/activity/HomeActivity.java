package com.stockmanagement.activity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.stockmanagement.R;
import com.stockmanagement.common.Preferences;
import com.stockmanagement.fragment.AvailableFragment;
import com.stockmanagement.fragment.StockFragment;
import com.stockmanagement.fragment.OccupiedFragment;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private Dialog rateDialog;
    private Button btnHome, btnShare, btnRate, btnLogout;
    private ImageButton ibtnAdd, ibtnTransfer, ibtnBack;
    private static Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        activity = HomeActivity.this;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnHome = (Button) findViewById(R.id.btnHome);
        btnShare = (Button) findViewById(R.id.btnShare);
        btnRate = (Button) findViewById(R.id.btnRate);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        ibtnAdd = (ImageButton) findViewById(R.id.ibtnAdd);
        ibtnTransfer = (ImageButton) findViewById(R.id.ibtnTransfer);
        ibtnBack = (ImageButton) findViewById(R.id.ibtnBack);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        btnHome.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        btnRate.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        ibtnAdd.setOnClickListener(this);
        ibtnTransfer.setOnClickListener(this);
        ibtnBack.setOnClickListener(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        HomeActivity.ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AvailableFragment(), getString(R.string.contacts));
        adapter.addFragment(new OccupiedFragment(), getString(R.string.message));
        adapter.addFragment(new StockFragment(), getString(R.string.geo_status));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnHome:
                finish();
                break;

            case R.id.btnShare:
                intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text));
                intent.setType("text/plain");
                startActivity(intent);
                break;

            case R.id.btnRate:
                DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
                int screenWidth = (int) (metrics.widthPixels * 0.90);
                rateDialog = new Dialog(this);
                rateDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                rateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                rateDialog.setContentView(R.layout.activity_rate_profile);
                rateDialog.getWindow().setLayout(screenWidth, RecyclerView.LayoutParams.WRAP_CONTENT);
                rateDialog.show();
                Button btnSubmit = (Button) rateDialog.findViewById(R.id.btnSubmit);
                ImageButton ibtnClose = (ImageButton) rateDialog.findViewById(R.id.ibtnClose);
                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("NewApi")
                    @Override
                    public void onClick(View v) {
                        rateDialog.cancel();
                        Toast.makeText(activity, "Thank you for rating us...", Toast.LENGTH_SHORT).show();
                    }
                });

                ibtnClose.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("NewApi")
                    @Override
                    public void onClick(View v) {
                        rateDialog.cancel();
                    }
                });
                break;

            case R.id.btnLogout:
                AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AppCompatAlertDialogStyle);
                builder.setTitle(getString(R.string.app_name));
                builder.setMessage(getString(R.string.sure_to_logout));
                builder.setPositiveButton(activity.getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(activity, AfterSplashActivity.class);
                        Preferences.setLoginflag("logout");
//                        Preferences.setFirstName(null);
//                        Preferences.setLastName(null);
//                        Preferences.setEmail(null);
//                        Preferences.setPassword(null);
//                        Preferences.setPhoneNumber(null);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton(activity.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
                break;

            case R.id.ibtnAdd:
                intent = new Intent(activity, AddItemActivity.class);
                startActivity(intent);
                break;

            case R.id.ibtnTransfer:
                intent = new Intent(activity, TransferItemActivity.class);
                startActivity(intent);
                break;

            case R.id.ibtnBack:
                finish();
                break;
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
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

    @Override
    public void onBackPressed() {


        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("Exit from application ?");
        builder.setMessage("Do you want to Rate us !").setCancelable(false).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id="));
                HomeActivity.this.startActivity(intent);
            }
        }).setPositiveButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                HomeActivity.this.finish();

            }
        });

        builder.create().show();
        //additional code


    }
}
