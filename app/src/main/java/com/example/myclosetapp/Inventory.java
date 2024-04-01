package com.example.myclosetapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myclosetapp.services.ItemsDataService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory extends AppCompatActivity
{
    private GridLayout itemsTopGrid;

    private GridLayout itemsBottomGrid;

    private GridLayout itemsShoeGrid;

    public Button settings;
    private Button addItem;

    private Button generateOutfitButton;

    Button Home;
    Button Back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // preparing initial state
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        itemsTopGrid = (GridLayout)findViewById(R.id.itemsTopGrid);
        itemsBottomGrid = (GridLayout)findViewById(R.id.itemsBottomGrid);
        itemsShoeGrid = (GridLayout)findViewById(R.id.itemsShoeGrid);
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

        dbRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("database", "Error getting data", task.getException());
                }
                else {
                    Iterable<DataSnapshot> items = task.getResult().getChildren();
                    items.forEach(rawItem -> {
                        if (
                            rawItem.child("pictureID").exists()
                            && rawItem.child("type").exists()
                        ) {
                            Clothing item = new Clothing();
                            item.setPictureID((String) rawItem.child("pictureID").getValue());
                            item.setIdentification((String) rawItem.child("identification").getValue());
                            item.setType(ClothingType.valueOf(
                                    (String) rawItem.child("type").getValue()
                            ));

                            switch (item.getType()) {
                                case "TOP":
                                    addItemIntoGrid(itemsTopGrid, item);
                                    break;
                                case "BOTTOM":
                                    addItemIntoGrid(itemsBottomGrid, item);
                                    break;
                                case "SHOE":
                                    addItemIntoGrid(itemsShoeGrid, item);
                                    break;
                                default:
                                    Log.e("database", "Item with invalid type was found.");
                            }

                            Log.d("database", item.toString());
                        }
                    });
                }

                Log.i("database", "onComplete triggered");
            }
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

    private void addItemIntoGrid(GridLayout grid, Clothing item)
    {
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
        grid.addView(image, params);
    }
}