package com.robson.a5g;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.telephony.TelephonyDisplayInfo;
import android.util.Log;
import android.view.View;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;

import com.robson.a5g.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.io.DataOutputStream;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private TelephonyManager telephonyManager;
    private PhoneStateListener phoneStateListener;
    private static final int REQUEST_CODE_PHONE_STATE_PERMISSION = 2;
    @RequiresApi(api = 33)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        // Get TelephonyManager
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                == PackageManager.PERMISSION_GRANTED) {
            // Permission is already granted
            // Your code here
            Log.d("test", "Read Phone State permsission is there");
            // Create a PhoneStateListener
            phoneStateListener = new PhoneStateListener() {
                @Override
                public void onDisplayInfoChanged(@NonNull TelephonyDisplayInfo telephonyDisplayInfo) {

                    @SuppressLint("MissingPermission")
                    int networkType = telephonyManager.getDataNetworkType();
                    Log.d("test", "Network Type: " + networkType);


                };
            };

            // Register the listener
            telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_DISPLAY_INFO_CHANGED);

        } else {
            // Permission is not granted
            // Request the permission

            Log.d("test", "Read phone state permsission is not there");
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    REQUEST_CODE_PHONE_STATE_PERMISSION
            );
        }


    };




    }


