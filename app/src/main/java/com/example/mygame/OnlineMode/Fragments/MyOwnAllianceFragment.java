package com.example.mygame.OnlineMode.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mygame.OnlineMode.Classes.AllianceOnline;
import com.example.mygame.OnlineMode.Classes.GameOnline;
import com.example.mygame.OnlineMode.Classes.SenderDialog;
import com.example.mygame.R;

public class MyOwnAllianceFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    static GameOnline game;
    AllianceOnline alliance;

    public MyOwnAllianceFragment(GameOnline game) {
        this.game = game;
        alliance = game.users.get(game.yourUserCode).country.alliance;
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
        View view = inflater.inflate(R.layout.fragment_my_own_alliance, container, false);
        ListView listView = view.findViewById(R.id.listView);
        String[] array = alliance.getNameOfCountries();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, array);
        listView.setAdapter(adapter);
        ImageView imageView = view.findViewById(R.id.imageViewAvatar);
        TextView textName = view.findViewById(R.id.textViewName);
        TextView textViewWar = view.findViewById(R.id.textViewWar);
        if (alliance.isActiveWar){
            textViewWar.setText("Сражения: АКТИВНО");
        }
        else{
            textViewWar.setText("Сражения: НЕТ АКТИВНОГО");
        }
        textName.setText(alliance.name);
        imageView.setImageResource(alliance.idOfAvatar);
        Button sender = view.findViewById(R.id.buttonSendInvite);
        SenderDialog dialog = new SenderDialog(game);
        sender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (game.post.myAllianceInvitation < 0){
                    dialog.show(getActivity().getSupportFragmentManager(), "Fragment");
                }
            }
        });
        return view;
    }

    public static void setGame(GameOnline gameOnline){
        game = gameOnline;
        Log.d("sender", String.valueOf(game.post.myAllianceInvitation));
    }
}