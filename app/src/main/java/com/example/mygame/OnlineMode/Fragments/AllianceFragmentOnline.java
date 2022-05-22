package com.example.mygame.OnlineMode.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mygame.Game;
import com.example.mygame.OnlineMode.Classes.AllianceOnline;
import com.example.mygame.OnlineMode.Classes.GameOnline;
import com.example.mygame.R;

public class AllianceFragmentOnline extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    GameOnline game;
    AllianceOnline alliance;

    public AllianceFragmentOnline(GameOnline game, AllianceOnline alliance) {
        this.game = game;
        this.alliance = alliance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alliance_online, container, false);
        ListView listView = view.findViewById(R.id.listView);
        String[] array = alliance.getNameOfCountries();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, array);
        listView.setAdapter(adapter);
        ImageView imageView = view.findViewById(R.id.imageViewAvatar);
        TextView textName = view.findViewById(R.id.textViewName);
        TextView textViewOwner = view.findViewById(R.id.textViewOwner);
        TextView textViewWar = view.findViewById(R.id.textViewWar);
        TextView desc = view.findViewById(R.id.textViewDescription);
        ImageView imageViewDone = view.findViewById(R.id.imageViewDone);
        if (alliance.isActiveWar){
            textViewWar.setText("Сражения: АКТИВНО");
        }
        else{
            textViewWar.setText("Сражения: НЕТ АКТИВНОГО");
        }
        textViewOwner.setText("Страна-основатель: " + game.users.get(alliance.idOfOwner).country.name);
        desc.setText(alliance.description);
        textName.setText(alliance.name);
        imageView.setImageResource(alliance.idOfAvatar);
        Button buttonRequest = view.findViewById(R.id.buttonRequest);
        buttonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Game.sendInvite(index);
//                buttonRequest.setEnabled(false);
//                imageViewDone.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }
}