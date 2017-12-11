package com.example.david.switcherApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.pm.ActivityInfo;

public class MainActivity extends AppCompatActivity {

    //comment again again

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       // Math testImportedObject = new Math();
    }

    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, IntroSlides.class);
        LevelScreen.level = "Level1.txt";
        startActivity(intent);

    }

    public void Selection(View view)
    {
        Intent intentlevel = new Intent(this, level_select.class);
        startActivity(intentlevel);
    }
}
