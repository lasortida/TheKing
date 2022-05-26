package com.example.mygame.OnlineMode.Classes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.mygame.OnlineMode.Fragments.MyOwnAllianceFragment;

import java.util.ArrayList;

public class SenderDialog extends DialogFragment {

    public GameOnline game;
    ArrayList<CountryOnline> array;
    String[] names;
    public boolean isSended = false;

    public SenderDialog(GameOnline game){
        this.game = game;
        array = game.getBlankCountries();
        names = new String[array.size()];
        for (int i = 0; i < names.length; ++i){
            names[i] = array.get(i).name;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Кому отправить приглашение?");
        builder.setItems(names, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!isSended){
                    int idOfCountry = array.get(i).id;
                    game.post.idOfCountryOffer = idOfCountry;
                    isSended = true;
                    MyOwnAllianceFragment.setGame(game);
                    dismiss();
                }
            }
        });
        return builder.create();
    }
}
