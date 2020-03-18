package com.example.myapplication;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

/**
 * Code with ❤
 * ╔════════════════════════════╗
 * ║  Created by Buğra Yetkin  ║
 * ╠════════════════════════════╣
 * ║ bugrayetkinn@gmail.com ║
 * ╠════════════════════════════╣
 * ║     18/03/2020 - 19:27     ║
 * ╚════════════════════════════╝
 */
public class MyAlertCreate {

    private AlertDialog dialog;
    private AlertDialog.Builder builder;

    public AlertDialog getDialog() {
        return dialog;
    }

    public void setDialog(AlertDialog dialog) {
        this.dialog = dialog;
    }

    public AlertDialog.Builder getBuilder() {
        return builder;
    }

    public void setBuilder(AlertDialog.Builder builder) {
        this.builder = builder;
    }

    public void createAlert(Context context, View view) {
        builder = new AlertDialog.Builder(context);
        dialog = builder.create();
        dialog.setView(view);
    }
}
