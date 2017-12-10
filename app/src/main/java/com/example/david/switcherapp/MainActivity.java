package com.example.david.switcherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

public class MainActivity extends AppCompatActivity {

    //comment again again

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
       // Math testImportedObject = new Math();
    }

    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, IntroSlides.class);
        startActivity(intent);

    }
}
