package com.example.david.switcherApp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * This frame allows the user to select a level, instead of playing through them all in order
 * @author Jillian Yong
 */
public class level_select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    /**
     * Determines which level to load
     * @param view the View object used in this frame
     */
    public void Selection(View view)
    {
        switch(view.getId()) {
            case R.id.lvl1:
                LevelScreen.level = "Level1.txt";
                break;
            case R.id.lvl2:
                LevelScreen.level = "Level2.txt";
                // it was the second button
                break;
            case R.id.lvl3:
                LevelScreen.level = "Level3.txt";
                // it was the second button
                break;
            case R.id.lvl4:
                LevelScreen.level = "Level4.txt";
                // it was the second button
                break;
            case R.id.lvl5:
                LevelScreen.level = "Level5.txt";
                // it was the second button
                break;
            case R.id.lvl6:
                LevelScreen.level = "Level6.txt";
                // it was the second button
                break;
            case R.id.lvl7:
                LevelScreen.level = "Level7.txt";
                // it was the second button
                break;
            case R.id.lvl8:
                LevelScreen.level = "Level8.txt";
                // it was the second button
                break;

        }
        Intent intentlevel = new Intent(this, LevelScreen.class);
        startActivity(intentlevel);
        //buttonselect = findViewById(v.getId())
    }

}
