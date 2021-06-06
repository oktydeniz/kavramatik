package com.kavramatik.kavramatik;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.kavramatik.kavramatik.util.AppAlertDialogs;
import com.kavramatik.kavramatik.util.IntentsTTS;
import com.kavramatik.kavramatik.util.SharedPreferencesManager;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener {
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        ColorDrawable drawable = new ColorDrawable(Color.parseColor("#FF018786"));
        actionBar.setBackgroundDrawable(drawable);
        boolean isFirstTime = SharedPreferencesManager.getIsFirstTime(this);
        if (isFirstTime) {
            AppAlertDialogs.showOnlyOnce(MainActivity.this, this);
            SharedPreferencesManager.setIsFirstTime(MainActivity.this, false);
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE) {
            IntentsTTS.installTTS(getApplicationContext());
        } else if (which == DialogInterface.BUTTON_NEGATIVE) {
            IntentsTTS.openTTSSettings(getApplicationContext());
        }
    }
}