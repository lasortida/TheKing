package com.example.mygame.OnlineMode.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mygame.MainActivity;
import com.example.mygame.R;

public class MainActivityOnline extends AppCompatActivity {

    EditText personNameEditText;
    Button buttonJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_online);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        buttonJoin = findViewById(R.id.buttonStart);

        buttonJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String personName = "none";
                startActivity(new Intent(MainActivityOnline.this, LoadingActivity.class).putExtra("userName", personName));
            }
        });
    }
}