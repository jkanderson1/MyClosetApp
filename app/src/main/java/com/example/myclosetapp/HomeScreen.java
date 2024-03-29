package com.example.myclosetapp;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeScreen extends AppCompatActivity {
    FloatingActionButton addItem;
     Button settings, inventory, remove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        addItem = findViewById(R.id.homeScreenToAddItem);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),
                        AddItem.class);
                startActivity(intent);
                finish();
            }
        });

        settings = findViewById(R.id.button2);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
                finish();
            }

        });

        inventory = findViewById(R.id.inventoryButton);
        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Inventory.class);
                startActivity(intent);
                finish();
            }
        });

        //testing remove functionality
       /* remove = findViewById(R.id.HS_RemoveItem);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // for testing purposes
                Clothing test = new Clothing();
                String id = test.getIdentification();
                test.setType(ClothingType.TOP);
                test.addToCloset();
                Toast added = Toast.makeText(HomeScreen.this,"added",LENGTH_SHORT);
                added.show();
                test.removeFromCloset();
                Toast remove = Toast.makeText(HomeScreen.this, "removed",
                        LENGTH_SHORT);
                remove.show();
            }
        });*/
    }
}