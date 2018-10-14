package com.benjizaid.myapp.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.benjizaid.myapp.R;

public class Functions {

    public static void mostrarAlerta(Activity activity, String message, DialogInterface.OnClickListener dialogoOk) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(activity)
                .setTitle(activity.getString(R.string.app_name))
                .setMessage(message)
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("OK", dialogoOk);
        dialogo.show();
    }
}
