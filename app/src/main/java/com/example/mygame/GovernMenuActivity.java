package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GovernMenuActivity extends AppCompatActivity {

    View mapButton;
    View nextButton;
    View curtain;
    TextView warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_govern_menu);
        mapButton = findViewById(R.id.map_button);
        nextButton = findViewById(R.id.next_button);
        warning = findViewById(R.id.textViewSignature);
        curtain = findViewById(R.id.curtain);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GovernMenuActivity.this, MapActivity.class).putExtra("INTERMEDIATE", 1));
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curtain.setAlpha(0);
                curtain.setVisibility(View.VISIBLE);
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
                        startActivity(new Intent(GovernMenuActivity.this, MapActivity.class));
                    }
                }.start();
            }
        });
    }
}