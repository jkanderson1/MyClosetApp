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

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddItem extends AppCompatActivity {
    ImageButton favFalse,favTrue ;
    ViewSwitcher swichFavs;
    Button finished;
    ImageView itemPicture;
    Spinner typeSpinner, styleSpinner;
    TextView myCloset;
    Clothing testItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // For testing purposes
            ArrayList<String> colors = new ArrayList<>();
            colors.add("Blue");
            colors.add("White");
            String pictureId = "2";
            ArrayList<String> seasons = new ArrayList<>();
            seasons.add("Spring");
            seasons.add("Summer");
            ArrayList<String> style = new ArrayList<>();
            style.add("Business Casual");
            style.add("Business Formal");

            testItem = new Clothing();



        swichFavs = (ViewSwitcher)findViewById(R.id.addItemSwitchFavorite);

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
                // for testing purposes
                    testItem.setColors(colors);
                    testItem.setSeasons(seasons);
                    testItem.setStyles(style);
                    testItem.addToCloset();

                Intent intent  = new Intent(getApplicationContext(),
                        HomeScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }

    protected void favoriteMe(ImageButton favorite, ImageButton notFavorite){
        testItem.setFavorite(true);
    }

}