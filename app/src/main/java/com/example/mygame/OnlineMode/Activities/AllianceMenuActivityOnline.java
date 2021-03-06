package com.example.mygame.OnlineMode.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mygame.OnlineMode.Classes.AllianceAdapterOnline;
import com.example.mygame.OnlineMode.Classes.AllianceOnline;
import com.example.mygame.OnlineMode.Classes.GameOnline;
import com.example.mygame.OnlineMode.Fragments.AllianceFragmentOnline;
import com.example.mygame.OnlineMode.Fragments.MyAllianceFragment;
import com.example.mygame.OnlineMode.Fragments.MyOwnAllianceFragment;
import com.example.mygame.R;

public class AllianceMenuActivityOnline extends AppCompatActivity {

    TextView textBrief;
    static TextView topic;
    static FragmentManager fragmentManager;

    Button back;
    Button create;

    public static boolean isFragmentAllianceLoaded = false;
    public static boolean isFragmentCreateAllianceLoaded = false;
    public static boolean isMyOwnFragmentLoaded = false;
    static AllianceFragmentOnline fragment;
    static MyAllianceFragment fragmentCreate;
    static MyOwnAllianceFragment myOwnFragment;

    static RecyclerView recyclerView;
    static GameOnline game;

    CountDownTimer timer;
    public static boolean timerStop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alliances_menu_online);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        timerStop = false;

        Bundle args = getIntent().getExtras();
        game = (GameOnline) args.getSerializable("GAME");
        fragmentManager = getSupportFragmentManager();

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
                    Log.d("timer", "allianceMenu");
                    startActivity(new Intent(AllianceMenuActivityOnline.this, RestActivity.class).putExtra("GAME", game));
                }
            }
        };
         timer.start();

        textBrief = findViewById(R.id.textViewBrief);
        back = findViewById(R.id.buttonBack);
        create = findViewById(R.id.buttonCreate);
        recyclerView = findViewById(R.id.recycler);
        topic = findViewById(R.id.textViewTopic);

        if (game.alliances.size() != 0){
            textBrief.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(manager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(new AllianceAdapterOnline(game));
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFragmentAllianceLoaded){
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.hide(fragment);
                    transaction.commit();
                    recyclerView.setVisibility(View.VISIBLE);
                    topic.setText("???????????? ????????????????");
                    isFragmentAllianceLoaded = false;
                }
                else if (isFragmentCreateAllianceLoaded){
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.hide(fragmentCreate);
                    transaction.commit();
                    if (game.alliances.size() == 0){
                        textBrief.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.INVISIBLE);
                    }
                    else{
                        topic.setText("???????????? ????????????????");
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                    isFragmentCreateAllianceLoaded = false;
                }
                else if (isMyOwnFragmentLoaded){
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.hide(myOwnFragment);
                    transaction.commit();
                    recyclerView.setVisibility(View.VISIBLE);
                    topic.setText("???????????? ????????????????");
                    isMyOwnFragmentLoaded = false;
                }
                else {
                    timerStop = true;
                    startActivity(new Intent(AllianceMenuActivityOnline.this, GovernMenuActivityOnline.class).putExtra("GAME", game));
                }
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (game.countries[game.yourCountryId].idOfAlliance == -1){
                    textBrief.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);
                    fragmentCreate = new MyAllianceFragment(game);
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.add(R.id.frame, fragmentCreate);
                    transaction.commit();
                    isFragmentCreateAllianceLoaded = true;
                }
            }
        });
    }

    public static void showAlliance(AllianceOnline alliance){
        recyclerView.setVisibility(View.INVISIBLE);
        topic.setText("????????????:");
        if (alliance.idOfOwner != game.yourCountryId){
            fragment = new AllianceFragmentOnline(game, alliance);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.frame, fragment);
            transaction.commit();
            isFragmentAllianceLoaded = true;
        }
        else{
            myOwnFragment = new MyOwnAllianceFragment(game);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.frame, myOwnFragment);
            transaction.commit();
            isMyOwnFragmentLoaded = true;
        }
    }

    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(this, "?????????????? ???????????? ??????????!", Toast.LENGTH_SHORT);
        toast.show();
    }

}