package com.example.david.switcherApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * This is the slide that appears when all levels are completed.
 *
 * @author Srinidhi Venkatesan Kalavai
 */
public class EndGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
    }

    /**
     * Quit game when the back button is pressed
     */
    @Override
    public void onBackPressed(){
        /*
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();*/
        finishAffinity();


    }
}
