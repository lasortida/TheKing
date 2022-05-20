package com.example.mygame.OnlineMode.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygame.Adapter;
import com.example.mygame.Country;
import com.example.mygame.Game;
import com.example.mygame.ImproveFragmentDialog;
import com.example.mygame.R;

public class AdapterRelationshipOnline extends RecyclerView.Adapter<AdapterRelationshipOnline.ElementHolder> {

    CountryOnline[] countries;
    Context context;
    CountryOnline me;

    public AdapterRelationshipOnline(CountryOnline[] countries, CountryOnline me){
        this.me = me;
        this.countries = countries;
    }

    @NonNull
    @Override
    public AdapterRelationshipOnline.ElementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        int layoutId = R.layout.element;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutId, parent, false);
        AdapterRelationshipOnline.ElementHolder elementHolder = new AdapterRelationshipOnline.ElementHolder(view);
        return elementHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRelationshipOnline.ElementHolder holder, int position) {
        CountryOnline country = countries[position];
        String name = country.name;
        int mood = country.relationshipValue;
        int id = country.idOfImage;
        holder.bind(name, mood, id, position);
    }

    @Override
    public int getItemCount() {
        return countries.length;
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
                    if (!me.wasTrade && me.ifCanTrade()) {
                        TradeFragmentDialog dialog = new TradeFragmentDialog(countries[getAdapterPosition()].id);
                        dialog.show(((AppCompatActivity)context).getSupportFragmentManager(), "Fragment");
                    }
                    else{
                        Toast toast = Toast.makeText(context, "Вы уже устроили один обмен или у вас недостаточно ресурсов!", Toast.LENGTH_LONG);
                        toast.show();
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
