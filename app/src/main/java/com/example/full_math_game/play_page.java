package com.example.full_math_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class play_page extends AppCompatActivity {

    ImageButton btn_Check;
    ImageButton btn_Clock;
    TextView textViewQuestion;
    TextView textViewResult;
    TextView timer;
    TextView Score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_page);
        btn_Check=findViewById(R.id.btnCheck);
        btn_Clock=findViewById(R.id.btnClose);
        textViewQuestion=findViewById(R.id.textViewQuestion);
        textViewResult=findViewById(R.id.textViewResult);
        timer=findViewById(R.id.txtTime);
        Score=findViewById(R.id.txtScore);
        time();
        btn_Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyAnswer(true);
            }
        });
        btn_Clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyAnswer(false);
            }
        });
        generateQuestions();
    }
    private int seconds =60;
    private boolean stoptimer=false;
    private void time()
    {

        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                timer.setText("Time :"+seconds);
                seconds--;
               if(seconds<0)
               {
                   Intent intent =new Intent(play_page.this,Score_Pagee.class);
                   intent.putExtra("score",score);
                   startActivity(intent);
                   stoptimer=true;
               }
               if(stoptimer==false)
               {

                   handler.postDelayed(this,1000);
               }
            }
        });
    }
    boolean isResultCorrect;
    private void generateQuestions()
    {

        Random r=new Random();
        int a=r.nextInt(100);
        int b=r.nextInt(100);
        int result=a+b;
        float f=r.nextFloat();
        isResultCorrect=true;
        if(f>0.5f)
        {
            result=r.nextInt(100);
            isResultCorrect=false;
        }
        textViewQuestion.setText(a+"+"+b);
        textViewResult.setText("="+result);

    }
    int score=0;
    private void verifyAnswer(boolean answer)
    {
        if(answer==isResultCorrect)
        {
            score=score+1;
            Score.setText("Score:"+score);
        }
        else
        {
            Vibrator vibrator = null;
                vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

            vibrator.vibrate(500);
        }
        generateQuestions();

    }
    @Override
    protected void onPause() {
        super.onPause();
        stoptimer = false;
        finish();
    }
}