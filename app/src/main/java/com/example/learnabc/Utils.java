package com.example.learnabc;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class Utils {

    public static void showSnackBar(View view, String message) {
        final Snackbar snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);

        snackBar.setAction("Dismiss", v -> {
            // Call your action method here
            snackBar.dismiss();
        });
        snackBar.show();

    }
}
