package com.example.david.switcherapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.support.design.widget.Snackbar;
import android.widget.Toast;
import android.widget.ImageView;


import java.io.IOException;


public class LevelScreen extends AppCompatActivity {

    public ImageButton bStart;
    public ImageButton bEnd;
    public Drawable swapper;
    public static View view;
    private Model gameModel;
    private boolean gameIsOver = false;
    double x, y;
    CartPoint bStartPoint;
    CartPoint bEndPoint;

    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_screen);
        view = findViewById(android.R.id.content);

        gameModel = new Model("Level7.txt",this);

        boolean isMoving;



       // do {
            //try {
              //  System.in.read(); //waits for user to press enter
            //} catch (IOException e) {}
            //testModel.clear();
           // isMoving = !testModel.update();
            try {
                System.in.read(); //waits for user to press enter
            } catch (IOException e) {}
            gameModel.clear();
            //isMoving = !gameModel.update();
            gameModel.printBoard();
       //     testModel.printBoard();
       // } while (testModel.getGameState() == 0);

        System.out.println();



    }
    public void onClick(View v)
    {


        if (gameIsOver) { //activities to do when user has seen that game is over
            Intent returnToHome = new Intent(this, MainActivity.class);
            startActivity(returnToHome);
        } else {

            if(i==1)
                ButtonFrom(v);
            else
            {
                if(i==2)
                    ButtonTo(v);
                if(i==3) //if two things selected already
                {
                    Swap();
                    i=1;

                    //UPDATE CYCLE -- DO NOT RE-ARRANGE (might cause screwy behavior in-game)
                    gameModel.update();
                    gameModel.printBoard();
                    //end of update cycle

                    System.out.println(gameModel.getGameState());


                    if (gameModel.getGameState() != 0 && !gameIsOver) { //game is won or lost -- use to print win or loss screen
                        System.out.println("Game over!");
                        ImageView img;
                        if (gameModel.getGameState() == -1) {
                            img = (ImageView)findViewById(R.id.gameOverMessage);
                            img.setImageResource(R.mipmap.death_message);
                        } else {
                            img = (ImageView)findViewById(R.id.gameOverMessage);
                            img.setImageResource(R.mipmap.win_message);

                        }
                        gameIsOver = true;
                    }
                }
            }


        }

    }
    public void ButtonFrom(View view)
    {
        bStart = findViewById(view.getId());
       // System.out.println("bStart: " + bStart);
        String Btag = (String)view.getTag();
       // System.out.println("BTag: "+Btag);
        x = Double.parseDouble(String.valueOf(Btag.charAt(0)));
        y = Double.parseDouble(String.valueOf(Btag.charAt(1)));
       // System.out.println("x: " +x+" y:"+y);
        bStartPoint = new CartPoint(x,y);
        //System.out.println(bStartPoint);
        i=2;
    }

    public void ButtonTo(View v)
    {
        bEnd = findViewById(v.getId());
        String Btag = (String)v.getTag();
        x = Double.parseDouble(String.valueOf(Btag.charAt(0)));
        y = Double.parseDouble(String.valueOf(Btag.charAt(1)));
        bEndPoint = new CartPoint(x,y);
       // System.out.println(bEndPoint);
        i=3;
    }

    public void Swap() {
        boolean flag = Wizard.magicSwap(bStartPoint, bEndPoint);
        System.out.println("flag: "+flag);

        /*if (flag) {
            swapper = bStart.getDrawable();
            bStart.setImageDrawable(bEnd.getDrawable());
            bEnd.setImageDrawable(swapper);
        }*/
    }

    public static void InitializeButton(char sprite, double i, double j)
    {
        if(i!=-1&&j!=-1) {
            String str = "";
            str = str + String.valueOf((int)i) + String.valueOf((int)j);
           // System.out.println(str);
            ImageButton b = view.findViewWithTag(str);
            //System.out.println(view.findViewWithTag("00"));
            if(b!=null){
                //System.out.println("Inside if");
                switch (sprite) {
                    case 'o':
                        b.setImageResource(R.drawable.orc);
                        break;
                    case 'b':
                        b.setImageResource(R.drawable.orc_brute);
                        break;
                    case 's':
                        b.setImageResource(R.drawable.orc_smart);
                        break;
                    case 'n':
                        b.setImageResource(R.drawable.orc_wary);
                        break;
                    case 'W':
                        b.setImageResource(R.drawable.wall);
                        break;
                    case 'h':
                        b.setImageResource(R.drawable.hole);
                        break;
                    case 'P':
                        b.setImageResource(R.drawable.wizard);
                        break;
                    case 'g':
                        b.setImageResource(android.R.color.transparent);
                        break;
                    default:
                        System.out.println("Button not found.");
                }
            }
            else if(b == null)
                {
                        System.out.println("Button Not Found");
                }
            }
        }


    }

