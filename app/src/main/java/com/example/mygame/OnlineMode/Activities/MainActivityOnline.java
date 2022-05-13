package com.example.mygame.OnlineMode.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mygame.R;

public class MainActivityOnline extends AppCompatActivity {

    EditText personNameEditText;
    Button buttonJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_online);

        personNameEditText = findViewById(R.id.editTextPersonName);
        buttonJoin = findViewById(R.id.buttonStart);

        buttonJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String personName = String.valueOf(personNameEditText.getText());
            }
        });
    }
}