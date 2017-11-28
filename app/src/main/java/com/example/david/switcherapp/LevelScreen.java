package com.example.david.switcherapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LevelScreen extends AppCompatActivity{

    public ImageButton bStart;
    public ImageButton bEnd;
    public Drawable swapper;

    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_screen);
       // Intent intent = getIntent();

       // setButtons();
    }
    public void onClick(View v)
    {
        if(i==1)
            ButtonFrom(v);
        else
        {
            if(i==2)
                ButtonTo(v);
            if(i==3)
            {
                Swap();
                i=1;
            }
        }
    }
    public void ButtonFrom(View view)
    {
        bStart = findViewById(view.getId());
        i=2;
    }

    public void ButtonTo(View v)
    {
        bEnd = findViewById(v.getId());
        i=3;
    }

    public void Swap()
    {
        swapper = bStart.getBackground();
        bStart.setBackground(bEnd.getBackground());
        bEnd.setBackground(swapper);
    }
}
