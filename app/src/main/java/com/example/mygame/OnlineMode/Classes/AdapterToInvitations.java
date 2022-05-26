package com.example.mygame.OnlineMode.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygame.OnlineMode.Activities.InvitationActivity;
import com.example.mygame.R;

import java.io.Serializable;
import java.util.ArrayList;

public class AdapterToInvitations extends RecyclerView.Adapter<AdapterToInvitations.ElementHolder> implements Serializable {

    GameOnline game;
    ArrayList<Note> notes;

    public AdapterToInvitations(GameOnline game){
        this.game = game;
        notes = new ArrayList<>();
        if (game.tradeWith != null && game.tradeWith.length != 0){
            for (int i = 0; i < game.tradeWith.length; ++i){
                Note note = new Note(game.tradeWith[i], 0, game.tradeAway[i], game.tradeToMe[i]);
                notes.add(note);
            }
        }
    }

    @NonNull
    @Override
    public ElementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.invite;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutId, parent, false);
        AdapterToInvitations.ElementHolder holder = new ElementHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ElementHolder holder, int position) {
        Note note = notes.get(position);
        if (note.type == 0){
            holder.note = note;
            String row = "Страна " + game.countries[note.idOfCountry].name + " предложила сделку! " + note.text;
            holder.bind(row);
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class ElementHolder extends RecyclerView.ViewHolder {

        TextView text;
        Button yes;
        Button no;
        Note note;

        public ElementHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.textViewTitle);
            yes = itemView.findViewById(R.id.buttonYes);
            no = itemView.findViewById(R.id.buttonNo);

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (note.type == 0){
                        notes.remove(note);
                    }
                }
            });
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (note.type == 0){
                        game.post.isTradeAccepted = true;
                        game.post.confirmation = note.idOfCountry;
                        int stateAway = note.stateAway;
                        switch (stateAway){
                            case 0:
                                game.countries[game.yourCountryId].moneyStatus -= 0.17;
                                break;
                            case 1:
                                game.countries[game.yourCountryId].armyStatus -= 0.17;
                                break;
                            case 2:
                                game.countries[game.yourCountryId].businessStatus -= 0.17;
                                break;
                            case 3:
                                game.countries[game.yourCountryId].workerStatus -= 0.17;
                                break;
                            case 4:
                                game.countries[game.yourCountryId].foodStatus -= 0.17;
                                break;
                        }
                        int stateToMe = note.stateToMe;
                        switch (stateToMe){
                            case 0:
                                game.countries[game.yourCountryId].moneyStatus += 0.17;
                                break;
                            case 1:
                                game.countries[game.yourCountryId].armyStatus += 0.17;
                                break;
                            case 2:
                                game.countries[game.yourCountryId].businessStatus += 0.17;
                                break;
                            case 3:
                                game.countries[game.yourCountryId].workerStatus += 0.17;
                                break;
                            case 4:
                                game.countries[game.yourCountryId].foodStatus += 0.17;
                                break;
                        }
                        InvitationActivity.setGame(game);
                        yes.setEnabled(false);
                        no.setEnabled(false);
                    }
                }
            });
        }

        public void bind(String text){
            this.text.setText(text);
        }
    }
}
