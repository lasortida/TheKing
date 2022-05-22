package com.example.mygame.OnlineMode.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mygame.ImageWithParams;
import com.example.mygame.OnlineMode.Activities.GovernMenuActivityOnline;
import com.example.mygame.OnlineMode.Activities.RelationshipActivityOnline;
import com.example.mygame.OnlineMode.Classes.CountryOnline;
import com.example.mygame.OnlineMode.Classes.GameOnline;
import com.example.mygame.R;

public class TradeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    CountryOnline me;

    int[][] ids = new int[][]{
            new int[]{
                    R.drawable.coin_zero, R.drawable.coin_first, R.drawable.coin_second, R.drawable.coin_third, R.drawable.coin_fourth, R.drawable.coin_fivth, R.drawable.coin_full
            },
            new int[]{
                    R.drawable.bomb_zero, R.drawable.bomb_first, R.drawable.bomb_second, R.drawable.bomb_third, R.drawable.bomb_fourth, R.drawable.bomb_fivth, R.drawable.bomb_full
            },
            new int[]{
                    R.drawable.tie_zero, R.drawable.tie_first, R.drawable.tie_second, R.drawable.tie_third, R.drawable.tie_fourth, R.drawable.tie_fivth, R.drawable.tie_full
            },
            new int[]{
                    R.drawable.anvil_zero, R.drawable.anvil_first, R.drawable.anvil_second, R.drawable.anvil_third, R.drawable.anvil_fourth, R.drawable.anvil_fivth, R.drawable.anvil_full
            },
            new int[]{
                    R.drawable.bread_zero, R.drawable.bread_first, R.drawable.bread_second, R.drawable.bread_third, R.drawable.bread_fourth, R.drawable.bread_fivth, R.drawable.bread_full
            }
    };

    int[] result;
    int[] downResult = {
            R.drawable.coin_full, R.drawable.bomb_full, R.drawable.tie_full, R.drawable.anvil_full, R.drawable.bread_full
    };
    int indexUp = 0;
    int indexDown = 0;
    CountryOnline trader;
    int indexOfTrader;
    GameOnline game;
    int userCodeTrader;

    public TradeFragment(GameOnline game, int indexOfTrader, int userCodeTrader) {
        this.game = game;
        this.me = game.countries[game.yourCountryId];
        this.trader = game.countries[indexOfTrader];
        this.indexOfTrader = indexOfTrader;
        this.userCodeTrader = userCodeTrader;
        result = getMyStateIds(me);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trade, container, false);
        ImageView up = view.findViewById(R.id.imageViewFirst);
        ImageView down = view.findViewById(R.id.imageViewSecond);

        while ((getPositionOfValue(me.getOneStatus(indexUp)) < 2 || getPositionOfValue(me.getOneStatus(indexUp)) >= 6) && indexUp != 5){
            indexUp++;
        }
        up.setImageResource(result[indexUp]);
        down.setImageResource(downResult[indexDown]);

        ImageView upNext = view.findViewById(R.id.imageViewUpNext);
        ImageView upPrev = view.findViewById(R.id.imageViewUpPrev);
        ImageView downNext = view.findViewById(R.id.imageViewDownNext);
        ImageView downPrev = view.findViewById(R.id.imageViewDownPrev);

        upNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (indexUp == 4){
                    indexUp = 0;
                }
                else{
                    indexUp++;
                }
                while ((getPositionOfValue(me.getOneStatus(indexUp)) < 2 || getPositionOfValue(me.getOneStatus(indexUp)) >= 6)){
                    indexUp++;
                }
                up.setImageResource(result[indexUp]);
            }
        });

        upPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (indexUp == 0){
                    indexUp = 4;
                }
                else{
                    indexUp--;
                }
                while ((getPositionOfValue(me.getOneStatus(indexUp)) < 2 || getPositionOfValue(me.getOneStatus(indexUp)) >= 6)){
                    indexUp--;
                }
                up.setImageResource(result[indexUp]);
            }
        });

        downNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (indexDown == 4){
                    indexDown = 0;
                }
                else{
                    indexDown++;
                }
                down.setImageResource(downResult[indexDown]);
            }
        });

        downPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (indexDown == 0){
                    indexDown = 4;
                }
                else{
                    indexDown--;
                }
                down.setImageResource(downResult[indexDown]);
            }
        });

        Button ready = view.findViewById(R.id.buttonAccept);
        ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.post.tradeWith = trader.id;
                game.post.stateUp = indexUp;
                game.post.stateDown = indexDown;

                me.wasTrade = true;
                trader.foreignTrade.put(indexDown, indexUp);
                me.indexOfTradeStatus = indexUp;
                me.removeOneStateOfStatus(indexUp);
                game.countries[game.yourCountryId] = me;
                game.countries[indexOfTrader] = trader;
                startActivity(new Intent(getActivity(), GovernMenuActivityOnline.class).putExtra("GAME", game));
            }
        });
        return view;
    }

    public int[] getMyStateIds(CountryOnline country){
        int[] result = new int[5];
        for (int i = 0; i < 5; ++i){
            result[i] = ids[i][getPositionOfValue(country.getOneStatus(i))];
        }
        return result;
    }

    public int getPositionOfValue(double value){
        if (value <= 0){
            return 0;
        }
        if (value > 0 && value <= 0.17){
            return 1;
        }
        if (value > 0.17 && value <= 0.34){
            return 2;
        }
        if (value > 0.34 && value <= 0.51){
            return 3;
        }
        if (value > 0.51 && value <= 0.68){
            return 4;
        }
        if (value > 0.68 && value < 0.85){
            return 5;
        }
        if (value >= 0.85){
            return 6;
        }
        return 0;
    }
}