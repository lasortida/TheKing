package com.example.mygame.OnlineMode.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygame.OnlineMode.Classes.AdapterRelationshipOnline;
import com.example.mygame.OnlineMode.Classes.CountryOnline;
import com.example.mygame.OnlineMode.Classes.GameOnline;
import com.example.mygame.OnlineMode.Fragments.TradeFragment;
import com.example.mygame.R;

public class RelationshipActivityOnline extends AppCompatActivity {

    Button back;
    TextView appeal;
    static View frame;
    static FragmentManager manager;
    static CountryOnline me;
    static RecyclerView recyclerView;
    static GameOnline game;
    CountDownTimer timer;
    public static boolean timerStop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relationship_online);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        timerStop = false;

        Bundle args = getIntent().getExtras();
        game = (GameOnline) args.getSerializable("GAME");
        me = game.countries[game.yourCountryId];

        recyclerView = findViewById(R.id.recycler);
        back = findViewById(R.id.buttonBack);
        appeal = findViewById(R.id.textViewAppeal);
        frame = findViewById(R.id.frame);

        manager = getSupportFragmentManager();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new AdapterRelationshipOnline(game.getListOfForeignCountries(), game.countries[game.yourCountryId]));

        timer = new CountDownTimer(game.time, 1000){

            @Override
            public void onTick(long l) {
                game.time = l;
                if (timerStop){
                    cancel();
                }
            }

            @Override
            public void onFinish() {
                if (!timerStop){
                    Log.d("timer", "relationship");
                    startActivity(new Intent(RelationshipActivityOnline.this, RestActivity.class).putExtra("GAME", game));
                }
            }
        };
        timer.start();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerStop = true;
                startActivity(new Intent(RelationshipActivityOnline.this, GovernMenuActivityOnline.class).putExtra("GAME", game));
            }
        });
    }

    public static void setAddView(int indexOfTrader){
        recyclerView.setVisibility(View.INVISIBLE);
        FragmentTransaction transaction = manager.beginTransaction();
        TradeFragment tradeFragment = new TradeFragment(game, indexOfTrader);
        transaction.add(R.id.frame, tradeFragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(this, "Нажмите кнопку назад!", Toast.LENGTH_SHORT);
        toast.show();
    }
}
