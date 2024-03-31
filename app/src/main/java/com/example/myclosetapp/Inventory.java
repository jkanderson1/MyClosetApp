package com.example.myclosetapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myclosetapp.services.ItemsDataService;
import com.google.firebase.database.DatabaseReference;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import java.util.ArrayList;

public class Inventory extends AppCompatActivity
{
    private ItemsDataService itemsData;

    public Button settings;

    private Button addItem;

    private Button generateOutfitButton;

    private DatabaseReference db;

    private Button Home;

    private Button Back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // preparing initial state

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        itemsData = ItemsDataService.getInstance();

        // fetching of items

        ArrayList<Clothing> itemsTop = itemsData.fetchItemsByType(ClothingType.TOP);
        ArrayList<Clothing> itemsBottom = itemsData.fetchItemsByType(ClothingType.BOTTOM);
        ArrayList<Clothing> itemsShoe = itemsData.fetchItemsByType(ClothingType.SHOE);

        // rendering items in Top grid

        GridLayout itemsTopGrid = (GridLayout)findViewById(R.id.itemsTopGrid);

        itemsTop.forEach(item -> {
            ImageView image = new ImageView(this.getBaseContext());
            String imageUrl = "https://firebasestorage.googleapis.com/v0/b/login-register-firebase-529e6.appspot.com/o/images%2F"
                    + item.getPictureID()
                    + "?alt=media";

            Glide.with(this)
                    .load(imageUrl)
                    .into(image);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.topMargin = 5;
            params.rightMargin = 5;
            params.bottomMargin = 5;
            params.leftMargin = 5;
            params.height = 340;
            params.width = 340;
            itemsTopGrid.addView(image, params);
        });

        // rendering items on Bottom grid

        GridLayout itemsBottomGrid = (GridLayout)findViewById(R.id.itemsBottomGrid);

        itemsBottom.forEach(item -> {
            ImageView image = new ImageView(this.getBaseContext());
            String imageUrl = "https://firebasestorage.googleapis.com/v0/b/login-register-firebase-529e6.appspot.com/o/images%2F"
                    + item.getPictureID()
                    + "?alt=media";

            Glide.with(this)
                    .load(imageUrl)
                    .into(image);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.topMargin = 5;
            params.rightMargin = 5;
            params.bottomMargin = 5;
            params.leftMargin = 5;
            params.height = 340;
            params.width = 340;
            itemsBottomGrid.addView(image, params);
        });

        // rendering items on Shoe grid

        GridLayout itemsShoeGrid = (GridLayout)findViewById(R.id.itemsShoeGrid);

        itemsShoe.forEach(item -> {
            ImageView image = new ImageView(this.getBaseContext());
            String imageUrl = "https://firebasestorage.googleapis.com/v0/b/login-register-firebase-529e6.appspot.com/o/images%2F"
                    + item.getPictureID()
                    + "?alt=media";

            Glide.with(this)
                    .load(imageUrl)
                    .into(image);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.topMargin = 5;
            params.rightMargin = 5;
            params.bottomMargin = 5;
            params.leftMargin = 5;
            params.height = 340;
            params.width = 340;
            itemsShoeGrid.addView(image, params);
        });

        settings = (Button) findViewById(R.id.button1);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
                finish();
            }

        });

        addItem = (Button) findViewById(R.id.addItemTV);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddItem.class);
                startActivity(intent);
                finish();
            }
        });

        Home = (Button) findViewById(R.id.HomeScreen);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                startActivity(intent);
                finish();
            }
        });

        Back = (Button) findViewById(R.id.backButton);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                startActivity(intent);
                finish();
            }
        });

        //outfit = new Outfit();
        generateOutfitButton = (Button) findViewById(R.id.generateButton);
        generateOutfitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Outfit.class);
                startActivity(intent);
                // generateOutfit();
                finish();
            }
        });
    }
}