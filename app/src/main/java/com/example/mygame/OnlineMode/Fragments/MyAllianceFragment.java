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

import com.example.mygame.AlliancesMenuActivity;
import com.example.mygame.OnlineMode.Activities.AllianceMenuActivityOnline;
import com.example.mygame.OnlineMode.Classes.AllianceOnline;
import com.example.mygame.OnlineMode.Classes.GameOnline;
import com.example.mygame.OnlineMode.Classes.UserOnline;
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
                if (!title.equals("")){
                    String desc = String.valueOf(description.getText());
                    if (desc.equals("")){
                        desc = "Описание отсутствует!";
                    }
                    AllianceOnline alliance = new AllianceOnline(game.yourUserCode, game.storage.getResImageAvatar(indexOfAvatar), title, desc);
                    game.alliances.add(alliance);
                    UserOnline user = game.users.get(game.yourUserCode);
                    user.country.alliance = alliance;
                    game.users.set(game.yourUserCode, user);
                    game.post.nameOfOwnAlliance = title;
                    AllianceMenuActivityOnline.isFragmentCreateAllianceLoaded = false;
                    startActivity(new Intent(getActivity(), AllianceMenuActivityOnline.class).putExtra("GAME", game));
                }
            }
        });
        return view;
    }
}