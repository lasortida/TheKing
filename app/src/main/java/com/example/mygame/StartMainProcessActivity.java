package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.templates.ThumbnailTemplate;
import android.view.View;
import android.widget.ImageView;

public class StartMainProcessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_govern_menu_start);
        ImageView curtain = findViewById(R.id.curtain);
        curtain.setImageAlpha(0);
        ImageView button = findViewById(R.id.imageViewButton);
        Thread thread = new Thread(){
            boolean isFinish = false;

            @Override
            public void run() {
                for (int i = 0; i < 255; ++i){
                    curtain.setImageAlpha(i);
                    try {
                        Thread.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                startActivity(new Intent(getApplicationContext(), MapActivity.class));
            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setImageResource(R.drawable.red_button_on);
                thread.start();
            }
        });
    }
}