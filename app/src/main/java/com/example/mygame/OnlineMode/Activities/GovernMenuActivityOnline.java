package com.example.mygame.OnlineMode.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mygame.ImageWithParams;
import com.example.mygame.OnlineMode.Classes.AdapterToInvitations;
import com.example.mygame.OnlineMode.Classes.CountryOnline;
import com.example.mygame.OnlineMode.Classes.GameOnline;
import com.example.mygame.R;

public class GovernMenuActivityOnline extends AppCompatActivity {

    View mapButton;
    View nextButton;
    Button jobButton;
    View curtain;
    TextView warning;
    ImageView done;
    TextView textViewWeek;
    Button relationshipButton;
    Button allianceMenu;
    Button offers;
    TextView counter;
    TextView countOfOffers;

    ImageWithParams coinView;
    ImageWithParams bombView;
    ImageWithParams tieView;
    ImageWithParams anvilView;
    ImageWithParams breadView;

    GameOnline game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_govern_menu_online);

        mapButton = findViewById(R.id.map_button);
        nextButton = findViewById(R.id.next_button);
        warning = findViewById(R.id.textViewSignature);
        curtain = findViewById(R.id.curtain);
        jobButton = findViewById(R.id.buttonTasks);
        done = findViewById(R.id.imageViewDone);
        textViewWeek = findViewById(R.id.textViewWeek);
        relationshipButton = findViewById(R.id.buttonRelationshipMenu);
        allianceMenu = findViewById(R.id.buttonAllianceMenu);
        counter = findViewById(R.id.textViewCounter);
        offers = findViewById(R.id.buttonInvite);
        countOfOffers = findViewById(R.id.countOfNotice);


        Bundle arguments = getIntent().getExtras();
        game = (GameOnline) arguments.getSerializable("GAME");

        new CountDownTimer(game.time, 1000){

            @Override
            public void onTick(long l) {
                game.time = l;
                int minutes = (int)(l / 60000);
                int seconds = (int)((l % 60000) / 1000);
                if (seconds >= 10){
                    counter.setText(minutes + ":" + seconds);
                }
                else{
                    counter.setText(minutes + ":0" + seconds);
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();

        coinView = new ImageWithParams(findViewById(R.id.imageViewCoin), 0);
        bombView = new ImageWithParams(findViewById(R.id.imageViewBomb), 1);
        tieView = new ImageWithParams(findViewById(R.id.imageViewTie), 2);
        anvilView = new ImageWithParams(findViewById(R.id.imageViewAnvil), 3);
        breadView = new ImageWithParams(findViewById(R.id.imageViewBread), 4);

        textViewWeek.setText(" Неделя: " + game.numberOfWeek);

        AdapterToInvitations adapter = new AdapterToInvitations(game, game.getNotes());
        countOfOffers.setText(String.valueOf(adapter.getItemCount()));

        setImagesWithGame(game.countries[game.yourCountryId]);

        if (game.isJobDone){
            done.setVisibility(View.VISIBLE);
        }

        allianceMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GovernMenuActivityOnline.this, AllianceMenuActivityOnline.class).putExtra("GAME", game));
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GovernMenuActivityOnline.this, MapActivityOnline.class).putExtra("INTERMEDIATE", 1).putExtra("RESOURCES", game.countries[game.yourCountryId].idOfMarker));
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (game.isJobDone){
                    curtain.setAlpha(0);
                    curtain.setVisibility(View.VISIBLE);
                    mapButton.setEnabled(false);
                    jobButton.setEnabled(false);
                    nextButton.setEnabled(false);
                    allianceMenu.setEnabled(false);
                    offers.setEnabled(false);
                    relationshipButton.setEnabled(false);
                    new Thread(){
                        @Override
                        public void run() {
                            float c = 0;
                            for (int i = 0; i < 400; ++i){
                                curtain.setAlpha(c);
                                c += 0.0025;
                                try {
                                    Thread.sleep(4);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            startActivity(new Intent(GovernMenuActivityOnline.this, RestActivity.class).putExtra("GAME", game).putExtra("RANGE", 1));
                        }
                    }.start();
                }
                else{
                    warning.setText("Для начала вы должны рассмотреть 3 обращения!");
                }
            }
        });
        jobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!game.isJobDone){
                    startActivity(new Intent(GovernMenuActivityOnline.this, JobActivityOnline.class).putExtra("GAME", game));
                }
                else{
                    warning.setText("Вы больше не можете рассматривать обращения. Вы это уже сделали!");
                }
            }
        });
        relationshipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GovernMenuActivityOnline.this, RelationshipActivityOnline.class).putExtra("GAME", game));
            }
        });
        offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GovernMenuActivityOnline.this, InvitationActivity.class).putExtra("GAME", game).putExtra("ADAPTER", adapter));
            }
        });
    }

    public void setImagesWithGame(CountryOnline country){
        coinView.setNewImage(country.moneyStatus);
        bombView.setNewImage(country.armyStatus);
        tieView.setNewImage(country.businessStatus);
        anvilView.setNewImage(country.workerStatus);
        breadView.setNewImage(country.foodStatus);
    }
}