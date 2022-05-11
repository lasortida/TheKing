package com.example.mygame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CreateAllianceFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Game game;
    private Button button;
    private ImageView imageView;
    private Button saver;
    private EditText editTextName;
    private ArrayList<Invitation> invitations;
    private int id;

    public CreateAllianceFragment(Game game){
        this.game = game;
        invitations = new ArrayList<>();
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
        View view = inflater.inflate(R.layout.fragment_create_alliance, container, false);
        ListView listView = view.findViewById(R.id.listview);
        String[] countries = game.getListOfCountries();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, countries);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setBackgroundColor(Color.GREEN);
                Invitation invitation = new Invitation(game.listOfCountries[0], game.listOfCountries[i + 1], false);
                invitations.add(invitation);
            }
        });
        imageView = view.findViewById(R.id.imageView6);
        id = Storage.getRandomIdOfImage();
        imageView.setImageResource(id);
        button = view.findViewById(R.id.buttonRechoose);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = Storage.getRandomIdOfImage();
                imageView.setImageResource(id);
            }
        });
        editTextName = view.findViewById(R.id.editTextName);
        saver = view.findViewById(R.id.buttonSave);
        saver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameOfAlliance = String.valueOf(editTextName.getText());
                Alliance alliance = new Alliance(game.listOfCountries[0], nameOfAlliance);
                for (Invitation i: invitations) {
                    alliance.addInvitation(i);
                }
                alliance.setIdOfAvatar(id);
                game.setUserAlliance(alliance);
                startActivity(new Intent(getActivity(), AlliancesMenuActivity.class).putExtra("GAME", game));
            }
        });
        return view;
    }
}