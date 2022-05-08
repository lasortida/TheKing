package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AlliancesMenuActivity extends AppCompatActivity {

    TextView textBrief;
    static TextView topic;
    static FragmentManager fragmentManager;

    Button back;
    Button create;

    static boolean isFragmentLoaded = false;
    static AllianceFragment fragment;
    static CreateAllianceFragment createFragment;

    static RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alliances_menu);
        Bundle args = getIntent().getExtras();
        Game game = (Game) args.getSerializable("GAME");
        fragmentManager = getSupportFragmentManager();

        textBrief = findViewById(R.id.textViewBrief);
        back = findViewById(R.id.buttonBack);
        create = findViewById(R.id.buttonCreate);
        recyclerView = findViewById(R.id.recycler);
        topic = findViewById(R.id.textViewTopic);

        if (game.activeAlliances.size() != 0){
            textBrief.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(manager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(new AllianceAdapter(game));
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFragmentLoaded){
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.hide(fragment);
                    transaction.commit();
                    recyclerView.setVisibility(View.VISIBLE);
                    topic.setText("Список Альянсов");
                    isFragmentLoaded = false;
                }
                else {
                    startActivity(new Intent(AlliancesMenuActivity.this, GovernMenuActivity.class).putExtra("GAME", game));
                }
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topic.setText("Создать Альянс");
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                createFragment = new CreateAllianceFragment();
                transaction.add(R.id.frame, createFragment);
                transaction.commit();
                isFragmentLoaded = true;
            }
        });
    }

    public static void showAlliance(Alliance alliance){
        recyclerView.setVisibility(View.INVISIBLE);
        topic.setText("Альянс:");
        fragment = new AllianceFragment(alliance);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frame, fragment);
        transaction.commit();
        isFragmentLoaded = true;
    }
}