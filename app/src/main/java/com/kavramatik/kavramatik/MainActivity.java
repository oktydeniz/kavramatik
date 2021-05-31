package com.kavramatik.kavramatik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.kavramatik.kavramatik.util.AppAlertDialogs;
import com.kavramatik.kavramatik.util.SharedPreferencesManager;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isFirstTime = SharedPreferencesManager.getIsFirstTime(this);
        if (isFirstTime) {
            AppAlertDialogs.showOnlyOnce(MainActivity.this, this);
            SharedPreferencesManager.setIsFirstTime(MainActivity.this, false);
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + "com.google.android.tts")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}