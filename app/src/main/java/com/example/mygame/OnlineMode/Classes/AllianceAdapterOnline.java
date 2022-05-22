package com.example.mygame.OnlineMode.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygame.OnlineMode.Activities.AllianceMenuActivityOnline;
import com.example.mygame.R;

public class AllianceAdapterOnline extends RecyclerView.Adapter<AllianceAdapterOnline.ElementHolder> {


    GameOnline game;

    public AllianceAdapterOnline(GameOnline game){
        this.game = game;
    }

    @NonNull
    @Override
    public AllianceAdapterOnline.ElementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.alliance;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutId, parent, false);
        AllianceAdapterOnline.ElementHolder elementHolder = new AllianceAdapterOnline.ElementHolder(view);
        return elementHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllianceAdapterOnline.ElementHolder holder, int position) {
        String nameOfAlliance = game.alliances.get(position).name;
        int index = game.alliances.get(position).idOfOwner;
        String owner = game.countries[index].name;
        int id = game.alliances.get(position).idOfAvatar;
        holder.bind(nameOfAlliance, owner, id);
    }

    @Override
    public int getItemCount() {
        return game.alliances.size();
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
                    AllianceMenuActivityOnline.showAlliance(game.alliances.get(getAdapterPosition()));
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
