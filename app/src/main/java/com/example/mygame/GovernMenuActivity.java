package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GovernMenuActivity extends AppCompatActivity {

    View mapButton;
    View nextButton;
    Button jobButton;
    View curtain;
    TextView warning;
    ImageView done;

    ImageWithParams coinView;
    ImageWithParams bombView;
    ImageWithParams tieView;
    ImageWithParams anvilView;
    ImageWithParams breadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_govern_menu);
        mapButton = findViewById(R.id.map_button);
        nextButton = findViewById(R.id.next_button);
        warning = findViewById(R.id.textViewSignature);
        curtain = findViewById(R.id.curtain);
        jobButton = findViewById(R.id.buttonTasks);
        done = findViewById(R.id.imageViewDone);

        coinView = new ImageWithParams(findViewById(R.id.imageViewCoin), 0);
        bombView = new ImageWithParams(findViewById(R.id.imageViewBomb), 1);
        tieView = new ImageWithParams(findViewById(R.id.imageViewTie), 2);
        anvilView = new ImageWithParams(findViewById(R.id.imageViewAnvil), 3);
        breadView = new ImageWithParams(findViewById(R.id.imageViewBread), 4);

        Bundle arguments = getIntent().getExtras();
        Game game = (Game) arguments.getSerializable("GAME");

        setImagesWithGame(game);
        if (game.isWeekDone()){
            done.setVisibility(View.VISIBLE);
        }

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GovernMenuActivity.this, MapActivity.class).putExtra("INTERMEDIATE", 1));
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (game.isWeekDone()){
                    curtain.setAlpha(0);
                    curtain.setVisibility(View.VISIBLE);
                    mapButton.setEnabled(false);
                    jobButton.setEnabled(false);
                    nextButton.setEnabled(false);
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
                            game.setWeek();
                            startActivity(new Intent(GovernMenuActivity.this, MapActivity.class).putExtra("GAME", game));
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
                if (!game.isWeekDone()){
                    startActivity(new Intent(GovernMenuActivity.this, JobActivity.class).putExtra("GAME", game));
                }
                else{
                    warning.setText("Вы больше не можете рассматривать обращения. Вы это уже сделали!");
                }
            }
        });
    }

    public void setImagesWithGame(Game game){
        coinView.setNewImage(game.getMoneyStatus());
        bombView.setNewImage(game.getArmyMood());
        tieView.setNewImage(game.getBourgeoisMood());
        anvilView.setNewImage(game.getWorkersMood());
        breadView.setNewImage(game.getFoodStatus());
    }
}