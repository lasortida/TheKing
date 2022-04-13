package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class StartOfNewGameActivity extends AppCompatActivity {

    String[] textArray = {
            "Взгляните на Карту Мира!", "А вот и страна, которой вам придётся править!", "Давайте перейдём к делу! Let's go!"
    };
    int countOfTap = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_of_new_game);

        ImageView map = findViewById(R.id.imageViewMap);
        TextView task = findViewById(R.id.textViewTask);

        map.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (countOfTap < 3){
                    if (countOfTap == 1){
                        map.setImageResource(R.drawable.world_map_rus_with);
                    }
                    task.setText(textArray[countOfTap]);
                    countOfTap++;
                }
                else{
                    startActivity(new Intent(StartOfNewGameActivity.this, StartMainProcessActivity.class));
                }
                return false;
            }
        });
    }
}