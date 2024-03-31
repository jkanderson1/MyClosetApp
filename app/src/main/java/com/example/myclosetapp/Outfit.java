package com.example.myclosetapp;

import android.content.Intent;

import com.bumptech.glide.Glide;
import com.example.myclosetapp.services.ItemsDataService;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Outfit  extends AppCompatActivity
{
    private ItemsDataService itemsData;

    private Button back;

    private static final String TAG = "Outfit";

    private List<Clothing> clothes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // preparing initial state

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfit);
        itemsData = ItemsDataService.getInstance();

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

    private void generateRandomOutfit() {
        // fetching items by type

        ArrayList<Clothing> itemsTop = itemsData.fetchItemsByType(ClothingType.TOP);
        ArrayList<Clothing> itemsBottom = itemsData.fetchItemsByType(ClothingType.BOTTOM);
        ArrayList<Clothing> itemsShoe = itemsData.fetchItemsByType(ClothingType.SHOE);

        Clothing selectedTopItem = itemsTop.get(new Random().nextInt(itemsTop.size()));
        Clothing selectedBottomItem = itemsBottom.get(new Random().nextInt(itemsBottom.size()));
        Clothing selectedShoeItem = itemsShoe.get(new Random().nextInt(itemsShoe.size()));

        //GridLayout itemsTopGrid = (GridLayout)findViewById(R.id.itemsTopGrid);
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
}
