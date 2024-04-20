package com.example.myclosetapp;

import android.content.Intent;

import com.bumptech.glide.Glide;
import com.example.myclosetapp.services.ItemsDataService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Outfit  extends AppCompatActivity
{
    private Button back;

    private List<Clothing> clothes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // preparing initial state

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfit);

        back = (Button) findViewById(R.id.BackButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Inventory.class);
                startActivity(intent);
                finish();
            }
        });

        generateRandomOutfit();
    }

    // generates the random outfit for user
    private void generateRandomOutfit() {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

        dbRef.get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("database", "Error getting data", task.getException());
            }
            else {
                ArrayList<Clothing> itemsTop = new ArrayList<>();
                ArrayList<Clothing> itemsBottom = new ArrayList<>();
                ArrayList<Clothing> itemsShoe = new ArrayList<>();
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
                                itemsTop.add(item);
                                break;
                            case "BOTTOM":
                                itemsBottom.add(item);
                                break;
                            case "SHOE":
                                itemsShoe.add(item);
                                break;
                            default:
                                Log.e("database", "Item with invalid type was found.");
                        }
                    }
                });

                Clothing selectedTopItem = itemsTop.get(new Random().nextInt(itemsTop.size()));
                Clothing selectedBottomItem = itemsBottom.get(new Random().nextInt(itemsBottom.size()));
                Clothing selectedShoeItem = itemsShoe.get(new Random().nextInt(itemsShoe.size()));

                ImageView imageTop = (ImageView) findViewById(R.id.imageTop);

                String imageUrl = "https://firebasestorage.googleapis.com/v0/b/login-register-firebase-529e6.appspot.com/o/images%2F"
                        + selectedTopItem.getPictureID()
                        + "?alt=media";

                Glide.with(this)
                        .load(imageUrl)
                        .into(imageTop);

                ImageView imageBottom = (ImageView) findViewById(R.id.imageBottom);

                imageUrl = "https://firebasestorage.googleapis.com/v0/b/login-register-firebase-529e6.appspot.com/o/images%2F"
                        + selectedBottomItem.getPictureID()
                        + "?alt=media";

                Glide.with(this)
                        .load(imageUrl)
                        .into(imageBottom);

                ImageView imageShoe = (ImageView) findViewById(R.id.imageShoe);

                imageUrl = "https://firebasestorage.googleapis.com/v0/b/login-register-firebase-529e6.appspot.com/o/images%2F"
                        + selectedShoeItem.getPictureID()
                        + "?alt=media";

                Glide.with(this)
                        .load(imageUrl)
                        .into(imageShoe);
            }
        });
    }
}
