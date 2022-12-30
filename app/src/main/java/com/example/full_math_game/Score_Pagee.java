package com.example.full_math_game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Score_Pagee extends AppCompatActivity {

    TextView textView;
    ImageButton share;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_pagee);
        textView=findViewById(R.id.textView);
        share=findViewById(R.id.Share);
        int s=getIntent().getIntExtra("score",0);
        textView.setText("Current Score : "+s);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_SEND);
                i.setType("plain/Text");
                i.putExtra(Intent.EXTRA_TEXT,"Just score");
                startActivity(i);
            }
        });
    }
}