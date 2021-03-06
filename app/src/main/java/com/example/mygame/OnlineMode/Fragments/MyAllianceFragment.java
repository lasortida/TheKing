package com.example.mygame.OnlineMode.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mygame.OnlineMode.Activities.AllianceMenuActivityOnline;
import com.example.mygame.OnlineMode.Classes.AllianceOnline;
import com.example.mygame.OnlineMode.Classes.GameOnline;
import com.example.mygame.R;

public class MyAllianceFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    GameOnline game;
    int indexOfAvatar = 0;

    public MyAllianceFragment(GameOnline game) {
        this.game = game;
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
        View view = inflater.inflate(R.layout.fragment_my_alliance, container, false);
        ImageView avatar = view.findViewById(R.id.imageViewAvatar);
        ImageView next = view.findViewById(R.id.imageViewNext);
        ImageView prev = view.findViewById(R.id.imageViewPrev);
        avatar.setImageResource(game.storage.getResImageAvatar(indexOfAvatar));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indexOfAvatar++;
                if (indexOfAvatar == game.storage.imagesAvatar.length){
                    indexOfAvatar = 0;
                }
                avatar.setImageResource(game.storage.getResImageAvatar(indexOfAvatar));
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indexOfAvatar--;
                if (indexOfAvatar == -1){
                    indexOfAvatar = game.storage.imagesAvatar.length - 1;
                }
                avatar.setImageResource(game.storage.getResImageAvatar(indexOfAvatar));
            }
        });
        EditText nameOfAlliance = view.findViewById(R.id.editTextNameOfAlliance);
        EditText description = view.findViewById(R.id.editTextTextMultiLine);
        Button ready = view.findViewById(R.id.buttonReady);
        ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = String.valueOf(nameOfAlliance.getText());
                String desc = String.valueOf(description.getText());
                if (!title.equals("") && !desc.equals("")){
                    AllianceOnline alliance = new AllianceOnline(game.alliances.size(), game.yourCountryId, game.storage.getResImageAvatar(indexOfAvatar), title, desc, game.numberOfWeek);
                    game.alliances.add(alliance);
                    game.countries[game.yourCountryId].idOfAlliance = alliance.id;
                    game.post.nameOfNewAlliance = alliance.name;
                    game.post.description = alliance.description;
                    game.post.avatar = alliance.idOfAvatar;
                    AllianceMenuActivityOnline.isFragmentCreateAllianceLoaded = false;
                    AllianceMenuActivityOnline.timerStop = true;
                    startActivity(new Intent(getActivity(), AllianceMenuActivityOnline.class).putExtra("GAME", game));
                }
                else {
                    Toast toast = Toast.makeText(getContext(), "???????? ?????? ?? ???????????????? ???? ???????????? ???????? ??????????????!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        return view;
    }
}