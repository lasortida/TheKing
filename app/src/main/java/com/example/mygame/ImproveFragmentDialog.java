package com.example.mygame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ImproveFragmentDialog extends DialogFragment {

    int countryId;

    public ImproveFragmentDialog(int idOfCountry){
        this.countryId = idOfCountry;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("МИД может побровать улучшить внешние взаимоотношения!");
        builder.setMessage("Вы можете поручить МИДу заняться этим делом, но для этого нужно потратить немного денег! Учтите, что ничего может не получиться! (Результат вы сможете узнать после 1 недели)");
        builder.setPositiveButton("Поручить!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Game.isImproveNeed = true;
                Game.countryIdImprovement = countryId;
                Game.moneyStatus -= 0.17;
            }
        });
        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        return builder.create();
    }
}
