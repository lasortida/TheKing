package com.example.mygame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ElementHolder> {

    int number;
    ArrayList<String> data;

    Adapter(int number, ArrayList<String> data){
        this.number = number;
        this.data = data;
    }

    @NonNull
    @Override
    public ElementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.element;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutId, parent, false);
        ElementHolder viewHolder = new ElementHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ElementHolder holder, int position) {
        String text = data.get(position);
        holder.bind(text);
    }

    @Override
    public int getItemCount() {
        return number;
    }

    class ElementHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ElementHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item);
        }

        void bind(String text){
            textView.setText(text);
        }
    }
}
