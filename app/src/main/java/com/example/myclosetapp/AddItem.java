package com.example.myclosetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class AddItem extends AppCompatActivity {
    ImageButton favFalse,favTrue ;
    ViewSwitcher swichFavs;
    Button finished;
    ImageView itemPicture;
    Spinner typeSpinner, styleSpinner;
    TextView myCloset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        favFalse = findViewById(R.id.addItemisFavFalse);
        favFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: create clothing class
                // TODO: set clothing.favorite to true

            }
        });

        finished = findViewById(R.id.addItemFinished);
        finished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(),
                        HomeScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }

    protected void favoriteMe(ImageButton favorite, ImageButton notFavorite){
        // TODO: idk if this makes any sort of sense
    }

}