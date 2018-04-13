package com.stockmanagement.activity;


import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.stockmanagement.R;
import com.stockmanagement.common.Preferences;
import com.stockmanagement.common.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class RegistrationActivity extends BaseActivity implements View.OnClickListener {

    private static Activity activity;
    private ImageView ivCamera;
    private EditText edtFirstName, edtLastName, edtEmail, edtPassword, edtConfirmPassword, edtPhoneNumber;
    private Button btnRegister;
    private RelativeLayout rlPiImage;
    private Toolbar toolbar;
    private Bitmap bitmap;
    private File destination = null;

    private InputStream inputStreamImg;
    private String imgPath = null, image;
    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;
    DatabaseReference database;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        activity = RegistrationActivity.this;
        findviews();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true); //TODO: here
        database = FirebaseDatabase.getInstance().getReference();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Registration");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ivCamera.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    private void findviews() {
        edtFirstName = (EditText) findViewById(R.id.edtFirstName);
        edtLastName = (EditText) findViewById(R.id.edtLastName);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtConfirmPassword = (EditText) findViewById(R.id.edtConfirmPassword);
        edtPhoneNumber = (EditText) findViewById(R.id.edtPhoneNumber);
        ivCamera = (ImageView) findViewById(R.id.ivCamera);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        rlPiImage = (RelativeLayout) findViewById(R.id.rlPiImage);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ivCamera:
                try {
                    selectImage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btnRegister:
                if (Utils.isEmpty(edtFirstName.getText().toString().trim()) || edtFirstName.length() <= 0)
                    Utils.showAlert(activity, getString(R.string.app_name), getString(R.string.alert_first_name));
                else if (Utils.isEmpty(edtLastName.getText().toString().trim()) || edtLastName.length() <= 0)
                    Utils.showAlert(activity, getString(R.string.app_name), getString(R.string.alert_last_name));
                else if (Utils.isEmpty(edtEmail.getText().toString().trim()) || edtEmail.length() <= 0)
                    Utils.showAlert(activity, getString(R.string.app_name), getString(R.string.alert_email));
                else if (!Utils.isValidEmail(edtEmail.getText().toString().trim()))
                    Utils.showAlert(activity, getString(R.string.app_name), getString(R.string.alert_valid_email));
                else if (Utils.isEmpty(edtPassword.getText().toString().trim()) || edtPassword.length() <= 0)
                    Utils.showAlert(activity, getString(R.string.app_name), getString(R.string.alert_password));
                else if (edtPassword.length() < 5)
                    Utils.showAlert(activity, getString(R.string.app_name), getString(R.string.alert_password_length));
                else if (Utils.isEmpty(edtConfirmPassword.getText().toString().trim()) || edtConfirmPassword.length() <= 0)
                    Utils.showAlert(activity, getString(R.string.app_name), getString(R.string.alert_confirm_password));
                else if (edtConfirmPassword.length() < 5)
                    Utils.showAlert(activity, getString(R.string.app_name), getString(R.string.alert_password_length));
                else {
                    intent = new Intent(activity, LoginActivity.class);
                    Preferences.setEmail(edtEmail.getText().toString());
                    Preferences.setFirstName(edtFirstName.getText().toString());
                    Preferences.setLastName(edtLastName.getText().toString());
                    Preferences.setPassword(edtPassword.getText().toString());

                    // firebase uploading

                    startActivity(intent);
                    finish();
                }
                break;
        }
    }


    // Select image from camera and gallery
    private void selectImage() {
        try {
            PackageManager pm = getPackageManager();
            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getPackageName());
            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                final CharSequence[] options = {getString(R.string.take_photo), getString(R.string.choose_from_gallery),
                        getString(R.string.cancel)};
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity);
                builder.setTitle(getString(R.string.select_option));
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals(getString(R.string.take_photo))) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, PICK_IMAGE_CAMERA);
                        } else if (options[item].equals(getString(R.string.choose_from_gallery))) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);
                        } else if (options[item].equals(getString(R.string.cancel))) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            } else
                Utils.ShowTost(getApplicationContext(), getString(R.string.camera_permission));
        } catch (Exception e) {
            Utils.ShowTost(getApplicationContext(), getString(R.string.camera_permission));
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        inputStreamImg = null;
        try {
            if (requestCode == PICK_IMAGE_CAMERA) {
                try {
                    Uri selectedImage = data.getData();
                    bitmap = (Bitmap) data.getExtras().get("data");
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);

                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                    destination = new File(Environment.getExternalStorageDirectory() + "/" +
                            getString(R.string.app_name), "IMG_" + timeStamp + ".jpg");

                    Log.e("Registeration Activity", "Camera::>>> " + destination);
                    FileOutputStream fo;

                    try {
                        destination.createNewFile();
                        fo = new FileOutputStream(destination);
                        fo.write(bytes.toByteArray());
                        fo.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    imgPath = destination.getAbsolutePath();
                    ivCamera.setImageBitmap(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == PICK_IMAGE_GALLERY) {
                Uri selectedImage = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                    Log.e("Registeration Activity", "Gallery::>>> " + image);

                    imgPath = getRealPathFromURI(selectedImage);
                    destination = new File(imgPath.toString());
                    ivCamera.setImageBitmap(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
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
}
