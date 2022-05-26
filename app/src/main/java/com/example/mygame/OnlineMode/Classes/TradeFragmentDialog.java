package com.example.mygame.OnlineMode.Classes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.mygame.Game;
import com.example.mygame.OnlineMode.Activities.RelationshipActivityOnline;

public class TradeFragmentDialog extends DialogFragment {

    int indexOfTrader;

    public TradeFragmentDialog(int indexOfTrader){
        this.indexOfTrader = indexOfTrader;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("МИД может побровать улучшить внешние взаимоотношения!");
        builder.setMessage("Вы можете поручить МИДу заняться этим делом, но для этого нужно устроить обмен! Выберите то, что хотите отдать и получить! Результат вы увидите через 2 игровые недели!");
        builder.setPositiveButton("Поручить!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RelationshipActivityOnline.setAddView(indexOfTrader);
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
