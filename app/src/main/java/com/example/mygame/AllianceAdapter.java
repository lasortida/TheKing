package com.example.mygame;

import android.content.Context;
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
        String nameOfAlliance = game.activeAlliances.get(position).name;
        String owner = game.activeAlliances.get(position).owner.name;
        int id = game.activeAlliances.get(position).idOfAvatar;
        holder.bind(nameOfAlliance, owner, id);
    }

    @Override
    public int getItemCount() {
        return game.activeAlliances.size();
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
                    AlliancesMenuActivity.showAlliance(game.activeAlliances.get(getAdapterPosition()));
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
