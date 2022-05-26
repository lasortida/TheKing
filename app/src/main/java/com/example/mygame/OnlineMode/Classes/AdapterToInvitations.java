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
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdapterToInvitations extends RecyclerView.Adapter<AdapterToInvitations.ElementHolder> implements Serializable {

    GameOnline game;
    ArrayList<Note> notes;
    ArrayList<Note> offerNotes;

    public AdapterToInvitations(GameOnline game, ArrayList<Note> notes, ArrayList<Note> offerNotes){
        this.game = game;
        this.notes = notes;
        this.offerNotes = offerNotes;
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
        Note note;
        if (position >= notes.size()){
            note = offerNotes.get(position - notes.size());
        }
        else{
            note = notes.get(position);
        }
        if (note.type == 0){
            holder.note = note;
            String row = "Страна " + game.countries[note.idOfCountry].name + " предложила сделку! " + note.text;
            holder.bind(row);
        }
        if (note.type == 1){
            holder.note = note;
            String row = "Альянс " + game.alliances.get(note.idOfAlliance).name + " (" + game.countries[note.idOfCountry].name + ") приглашает вас присоединиться к альянсу!";
            holder.bind(row);
        }
    }

    @Override
    public int getItemCount() {
        return notes.size() + offerNotes.size();
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
                        game.isHandle[game.postIndex] = true;
                        notes.remove(note);
                        game.post.isTradeAccepted[game.postIndex] = false;
                        game.post.confirmation[game.postIndex] = note.idOfCountry;
                        game.postIndex++;
                        InvitationActivity.setGame(game, notes, offerNotes);
                    }
                    if (note.type == 1){
                        game.isHandleOffer[game.postIndexOffer] = true;
                        offerNotes.remove(note);
                        game.post.isOfferAccepted[game.postIndexOffer] = false;
                        game.post.allianceIdConfirmation[game.postIndexOffer] = note.idOfAlliance;
                        game.postIndexOffer++;
                        InvitationActivity.setGame(game, notes, offerNotes);
                    }
                }
            });
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (note.type == 0){
                        game.post.isTradeAccepted[game.postIndex] = true;
                        game.post.confirmation[game.postIndex] = note.idOfCountry;
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
                        game.isHandle[game.postIndex] = true;
                        notes.remove(note);
                        game.postIndex++;
                        InvitationActivity.setGame(game, notes, offerNotes);
                    }
                    if (note.type == 1){
                        game.post.isOfferAccepted[game.postIndexOffer] = true;
                        game.post.allianceIdConfirmation[game.postIndexOffer] = note.idOfAlliance;
                        game.isHandleOffer[game.postIndexOffer] = true;
                        offerNotes.remove(note);
                        game.postIndexOffer++;
                        InvitationActivity.setGame(game, notes, offerNotes);
                    }
                }
            });
        }

        public void bind(String text){
            this.text.setText(text);
        }
    }
}
