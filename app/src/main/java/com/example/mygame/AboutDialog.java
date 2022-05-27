package com.example.mygame;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AboutDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("About")
                .setMessage("Web: 912939-cf66069.tmweb.ru \n E-mail: theking@communtiy.com")
                .setCancelable(true)
                .setIcon(R.drawable.corona);
        Dialog dialog = builder.create();
        return dialog;
    }
}
