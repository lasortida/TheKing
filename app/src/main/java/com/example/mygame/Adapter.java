package com.example.mygame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ElementHolder> {

    Country[] countries;
    int[] images = {
            R.drawable.granderberg, R.drawable.goland, R.drawable.dogsland, R.drawable.goldland, R.drawable.stoneland, R.drawable.airland, R.drawable.diamondland, R.drawable.greenland
    };
    Context context;

    public Adapter(Country[] countries){
        this.countries = countries;
    }

    @NonNull
    @Override
    public ElementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        int layoutId = R.layout.element;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutId, parent, false);
        ElementHolder elementHolder = new ElementHolder(view);
        return elementHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ElementHolder holder, int position) {
        Country country = countries[position + 1];
        String name = country.name;
        int mood = country.relationshipValue;
        int id = images[position + 1];
        holder.bind(name, mood, id, position);
    }

    @Override
    public int getItemCount() {
        return countries.length - 1;
    }

    public class ElementHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewMood;
        private ImageView imageView;
        private Button button;
        private int position;

        public ElementHolder(@NonNull View itemView) {
            super(itemView);
            position = getAdapterPosition();
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewMood = itemView.findViewById(R.id.textViewMood);
            imageView = itemView.findViewById(R.id.imageView);
            button = itemView.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!Game.isImproveNeed && Game.moneyStatus > 0.17){
                        ImproveFragmentDialog dialog = new ImproveFragmentDialog(position + 1);
                        dialog.show(((AppCompatActivity)context).getSupportFragmentManager(), "Fragment");
                    }
                }
            });
        }

        void bind(String name, int mood, int id, int position){
            textViewName.setText(name);
            textViewMood.setText(mood + "%");
            imageView.setImageResource(id);
            this.position = position;
        }
    }
}
