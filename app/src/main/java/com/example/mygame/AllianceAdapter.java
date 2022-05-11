package com.example.mygame;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllianceAdapter extends RecyclerView.Adapter<AllianceAdapter.ElementHolder> {

    Game game;

    public AllianceAdapter(Game game){
        this.game = game;
    }

    @NonNull
    @Override
    public ElementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.alliance;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutId, parent, false);
        AllianceAdapter.ElementHolder elementHolder = new AllianceAdapter.ElementHolder(view);
        return elementHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ElementHolder holder, int position) {
        String nameOfAlliance = "";
        String owner = "";
        int id = 0;
        if (game.userAlliance == null) {
            nameOfAlliance = game.activeAlliances.get(position).name;
            owner = game.activeAlliances.get(position).owner.name;
            id = game.activeAlliances.get(position).idOfAvatar;
        }
        else{
            if (position == 0){
                nameOfAlliance = game.userAlliance.name;
                owner = game.userAlliance.owner.name;
                id = game.userAlliance.idOfAvatar;
            }
            else{
                nameOfAlliance = game.activeAlliances.get(position - 1).name;
                owner = game.activeAlliances.get(position - 1).owner.name;
                id = game.activeAlliances.get(position - 1).idOfAvatar;
            }
        }
        holder.bind(nameOfAlliance, owner, id);
    }

    @Override
    public int getItemCount() {
        if (game.userAlliance == null){
            return game.activeAlliances.size();
        }
        else {
            return game.activeAlliances.size() + 1;
        }
    }

    public class ElementHolder extends RecyclerView.ViewHolder {

        TextView nameOfAlliance;
        TextView owner;
        ImageView avatar;
        View element;

        public ElementHolder(@NonNull View itemView) {
            super(itemView);
            nameOfAlliance = itemView.findViewById(R.id.textViewNameOfAlliance);
            owner = itemView.findViewById(R.id.textViewCountryOwner);
            avatar = itemView.findViewById(R.id.imageViewAvatarOfAlliance);
            element = itemView.findViewById(R.id.element);
            element.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (game.userAlliance == null) {
                        AlliancesMenuActivity.showAlliance(game.activeAlliances.get(getAdapterPosition()));
                    }
                    else {
                        if (getAdapterPosition() == 0){
                            // включаем вкладку Мой Альянс!
                        }
                        else{
                            AlliancesMenuActivity.showAlliance(game.activeAlliances.get(getAdapterPosition() - 1));
                        }
                    }
                }
            });
        }

        void bind(String nameOfAlliance, String owner, int idOfAvatar){
            avatar.setImageResource(idOfAvatar);
            this.nameOfAlliance.setText("Название: " + nameOfAlliance);
            this.owner.setText("Страна-создатель: " + owner);
        }
    }
}
