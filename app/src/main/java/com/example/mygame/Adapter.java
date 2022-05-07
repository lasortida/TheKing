package com.example.mygame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ElementHolder> {

    Country[] countries;
    int[] images = {
            R.drawable.granderberg, R.drawable.goland, R.drawable.dogsland, R.drawable.goldland, R.drawable.stoneland, R.drawable.airland, R.drawable.diamondland, R.drawable.greenland
    };

    public Adapter(Country[] countries){
        this.countries = countries;
    }

    @NonNull
    @Override
    public ElementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
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
        holder.bind(name, mood, id);
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

        public ElementHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewMood = itemView.findViewById(R.id.textViewMood);
            imageView = itemView.findViewById(R.id.imageView);
            button = itemView.findViewById(R.id.button);
        }

        void bind(String name, int mood, int id){
            textViewName.setText(name);
            textViewMood.setText(mood + "%");
            imageView.setImageResource(id);
        }
    }
}
