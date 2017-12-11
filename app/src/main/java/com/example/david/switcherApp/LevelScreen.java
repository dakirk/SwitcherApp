package com.example.david.switcherApp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;



import java.io.IOException;

/**
 * This is the backend for the level screen, in which most of the game is played. It controls tile
 * rendering, tile swapping, turn control, and win/loss behavior.
 */
public class LevelScreen extends AppCompatActivity {

    public ImageButton bStart;
    public ImageButton bEnd;
    public static View view;
    private Model gameModel;
    private boolean gameIsOver = false;
    double x, y;
    CartPoint bStartPoint;
    CartPoint bEndPoint;
    // private boolean win = false;
    //private boolean lose = false;
    public static String level="Level1.txt";
    private int levelcounter;
    ImageView img;

    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        levelcounter = 1;
        setContentView(R.layout.activity_level_screen);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        view = findViewById(android.R.id.content);
        img = (ImageView) findViewById(R.id.gameOverMessage);

        GameBegin();
        System.out.println();

    }

    /**
     * Function that runs when each level starts. It determines whether to load a level or move on
     * to the end slide, and if there is a level to load, it initializes it.
     */
    public void GameBegin()
    {
        img.setImageResource(0);

        if(levelcounter<=8) {
            gameModel = new Model(level, this);
            String hint = gameModel.getHint();
            TextView textView = findViewById(R.id.textView17);
            textView.setText(hint);
            try {
                System.in.read(); //waits for user to press enter
            } catch (IOException e) {
            }
            gameModel.clear();
            gameModel.redraw();
            gameModel.printBoard();
            gameIsOver = false;
        }
        else{
            Intent intent = new Intent(this, EndGame.class);
            startActivity(intent);
        }

    }

    /**
     * Function called when a button is selected in the grid. It first checks for a game over, and
     * then determines what to do for tile selection.
     * @param v the View object used in this frame (?)
     */
    public void onClick(View v)
    {
        if (gameIsOver) { //activities to do when user has seen that game is over
            //Intent returnToHome = new Intent(this, MainActivity.class);
            //startActivity(returnToHome);
            GameBegin();
        } else {

            if(i==3) {
                gameModel.printBoard();
                bStart.setColorFilter(Color.argb(0, 0, 0, 0));
                bEnd.setColorFilter(Color.argb(0, 0, 0, 0));

                //bStart = null;
                //bEnd = null;
                i = 1;
            }
            if(i==1) {
                ButtonFrom(v);
                System.out.println("Button From selected");
            }
            else if(i==2) {
                ButtonTo(v);
                System.out.println("Button To selected");
            }
        }
    }

    /**
     * Determines how to handle a button press on the grid when nothing is yet selected. If the
     * selection is valid, it highlights and stores the button, and if not, it does nothing.
     * @param view the View object used in this frame
     */
    public void ButtonFrom(View view)
    {
        bStart = findViewById(view.getId());
        String Btag = (String)view.getTag();

        x = Double.parseDouble(String.valueOf(Btag.charAt(0)));
        y = Double.parseDouble(String.valueOf(Btag.charAt(1)));
        bStartPoint = new CartPoint(x,y);

        GameObject selectedObj = gameModel.getGameObject(bStartPoint);
        if (selectedObj != null) {
            char bStartPointObjType = selectedObj.getType();
            if (bStartPointObjType != 'W' && bStartPointObjType != 'P' && Character.toUpperCase(bStartPointObjType) != 'H') { //if not wall or hole
                bStart.setColorFilter(Color.argb(127, 255, 255, 255)); //tint button if swappable
                //in the future we could put more stuff here--determine whether to select or not based on type!
                i=2;
                System.out.print(view.getBackground());

            } else {
                i=1; //reject selection and repeat on next tap
            }
        } else {
            bStart.setColorFilter(Color.argb(127, 255, 255, 255)); //tint button if swappable
            i=2; //if selection null selection still valid
        }


        //System.out.println(bStartPoint);

    }

    /**
     * Determines how to handle a button press after something has already been selected. Otherwise,
     * it behaves the same as ButtonFrom.
     * @param v the View object used in this frame
     */
    public void ButtonTo(View v) {
        System.out.println("In Button To");
        bEnd = findViewById(v.getId());
        String Btag = (String) v.getTag();

        x = Double.parseDouble(String.valueOf(Btag.charAt(0)));
        y = Double.parseDouble(String.valueOf(Btag.charAt(1)));
        bEndPoint = new CartPoint(x, y);

        GameObject selectedObj = gameModel.getGameObject(bEndPoint);
        if (selectedObj != null) {
            char bEndPointObjType = selectedObj.getType();
            if (bEndPointObjType != 'W' && bEndPointObjType != 'P' && Character.toUpperCase(bEndPointObjType) != 'H') { //if not wall or hole
                bEnd.setColorFilter(Color.argb(127, 255, 255, 255)); //tint button if swappable
                //in the future we could put more stuff here--determine whether to select or not based on type!
                i = 3;
            } else {
                i = 2; //reject selection and repeat on next tap
            }
        } else {
            bEnd.setColorFilter(Color.argb(127, 255, 255, 255)); //tint button if swappable
            i = 3; //if selection null selection is still valid
        }

        //System.out.println(bEndPoint);
    }

    /**
     * Calls Wizard's magicSwap function on the two selected locations
     */
    public void Swap() {

        Wizard.magicSwap(bStartPoint, bEndPoint);

    }

    /**
     * Initializes a single button in the grid with the correct sprite
     * @param sprite the character representing the object type in that location and the sprite to
     *               be used to represent it
     * @param i the horizontal coordinate of the grid square
     * @param j the vertical coordinate of the grid square
     */
    public static void InitializeButton(char sprite, double i, double j) {
        if(i!=-1&&j!=-1) {
            String str = "";
            str = str + String.valueOf((int)i) + String.valueOf((int)j);
            ImageButton b = view.findViewWithTag(str);
            if(b!=null){
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
                        b.setImageResource(R.mipmap.wall);
                        break;
                    case 'H':
                        b.setImageResource(R.mipmap.covered_hole);
                        break;
                    case 'h':
                        b.setImageResource(R.mipmap.hole);
                        break;
                    case 'm':
                        b.setImageResource(R.mipmap.mine);
                        break;
                    case 'r':
                        b.setImageResource(R.mipmap.bomb_small);
                        break;
                    case 'R':
                        b.setImageResource(R.mipmap.bomb_large);
                        break;
                    case 'e':
                        b.setImageResource(R.mipmap.explosion);
                        break;
                    case 'P':
                        b.setImageResource(R.drawable.wizard);
                        break;
                    case 'g':
                        //b.setImageResource(android.R.color.transparent);
                        b.setImageResource(R.mipmap.grass_fill);
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

    /**
     * Function to execute one turn of the game, in which tiles are swapped, orcs move, and traps
     * trigger. It also deselects tiles if necessary to reset for the next turn.
     * @param view the View object used in this frame
     */
    public void Play(View view) {
        if (bStart != null) //clear tile selection
            bStart.setColorFilter(Color.argb(0, 0, 0, 0));
        if (bEnd != null) //clear tile selection
            bEnd.setColorFilter(Color.argb(0, 0, 0, 0));

        //if selection incomplete, nothing to swap
        if(i==1||i==2) {
            gameModel.redraw();
            gameModel.update();
            gameModel.printBoard();
            i=1;
            if (gameModel.getGameState() != 0){ //&& !gameIsOver) { //game is won or lost -- use to print win or loss screen
                System.out.println("Game over!");
                gameIsOver = true;

                if (gameModel.getGameState() == -1) {
                    img.setImageResource(R.mipmap.death_message);
                }
                if(gameModel.getGameState()== 1) {
                    //img = (ImageView) findViewById(R.id.gameOverMessage);
                    img.setImageResource(R.mipmap.win_message);
                    level = "Level"+Integer.toString(++levelcounter)+".txt";
                    //GameBegin();
                   // win = true;
                    gameIsOver = true;
                }
            }
        }
        else{ //if selection complete, same as above, but with a swap
            Swap();
            i = 1;
            //UPDATE CYCLE -- DO NOT RE-ARRANGE (might cause screwy behavior in-game)
            gameModel.redraw();
            System.out.println("Redraw complete");
            gameModel.update();
            System.out.println("Update complete");
            gameModel.printBoard();
            System.out.println("Board printed");
            //end of update cycle

            System.out.println(gameModel.getGameState());

            bStart.setColorFilter(Color.argb(0, 0, 0, 0)); //untint selected button
            bEnd.setColorFilter(Color.argb(0, 0, 0, 0)); //untint selected button

            if (gameModel.getGameState() != 0){ //&& !gameIsOver) { //game is won or lost -- use to print win or loss screen
                System.out.println("Game over!");
                gameIsOver = true;
                if (gameModel.getGameState() == -1) {
                    img.setImageResource(R.mipmap.death_message);

                }
                if(gameModel.getGameState()== 1) {
                  //  img = (ImageView) findViewById(R.id.gameOverMessage);
                    img.setImageResource(R.mipmap.win_message);
                    level = "Level"+Integer.toString(++levelcounter)+".txt";
                }
                //gameIsOver=true;
            }
            //gameIsOver = true;
        }
    }
}




