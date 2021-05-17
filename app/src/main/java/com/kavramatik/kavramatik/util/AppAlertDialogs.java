package com.kavramatik.kavramatik.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;

import com.kavramatik.kavramatik.R;

public class AppAlertDialogs {
    private Activity classActivity;
    private AlertDialog alertDialog;

    public static void showOnlyOnce(Context context, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.opening_dialog_text);
        builder.setCancelable(true); //TODO::Get false when app done !!!
        builder.setTitle(R.string.information);
        builder.setPositiveButton(R.string.continue_text, listener);
        builder.create().show();
    }

    public AppAlertDialogs() {

    }

    public AppAlertDialogs(Activity activity) {
        this.classActivity = activity;
    }

    public static void showAlertDialog(Context context, String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.okay, (dialog, which) -> {
        });
        builder.create().show();
    }


    public static void showAlertDialog(Context context, String title, String msg, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.okay, listener);
        builder.setNegativeButton(R.string.cancel, listener);
        builder.create().show();
    }

    public void startLoadingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(classActivity);
        LayoutInflater inflater = classActivity.getLayoutInflater();
        builder.setView(R.layout.loading_dialog);
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();
    }

    public void dismissLoading() {
        alertDialog.dismiss();
    }
}
