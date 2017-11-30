package com.example.david.switcherapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import java.io.IOException;


public class LevelScreen extends AppCompatActivity {

    public ImageButton bStart;
    public ImageButton bEnd;
    public Drawable swapper;

    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_screen);

        //Snackbar mySnackbar = Snackbar.make(findViewById(android.R.id.content), "Don\'t let an orc get you!" ,Snackbar.LENGTH_LONG);
        //mySnackbar.show();

        //Context context = getApplicationContext();
        //CharSequence text = "Hello toast!";
        //int duration = Toast.LENGTH_LONG;
        //Toast toast = Toast.makeText(context, text, duration);
        //toast.show();

        //trying dialog instead

        Model testModel = new Model("Level1.txt",this);

        boolean isMoving;

        testModel.printBoard();
        do {
            try {
                System.in.read(); //waits for user to press enter
            } catch (IOException e) {}
            testModel.clear();
            isMoving = !testModel.update();
            testModel.printBoard();
        } while (testModel.getGameState() == 0);

        System.out.println();



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
