package com.example.myclosetapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Inventory extends AppCompatActivity {

    public Button settings;
    private Button addItem;

    private Button generateOutfitButton;
    private Outfit outfit;

    Button Home;
    Button Back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

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
        }); {

        }
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

        outfit = new Outfit();
        generateOutfitButton = (Button) findViewById(R.id.generateButton);
        generateOutfitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Outfit.class);
                generateOutfit();
                startActivity(intent);
                finish();
            }
        });



    }
    private void generateOutfit() {
        outfit.generateRandomOutfit();
    }
}