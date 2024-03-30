package com.example.myclosetapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Inventory extends AppCompatActivity {

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = storage.getReference();
    StorageReference imageRef;
    public Button settings;
    private Button addItem;

    private Button generateOutfitButton;
    private Outfit outfit;

    private ImageButton Home;

    private RecyclerView recyclerView;
    private ImageAdapter adapter;
    private List<Uri> imageURLs;


    Button Back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        imageViewSetUp();

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

        Home = findViewById(R.id.homeButton);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        HomeScreen.class);
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

    private void imageViewSetUp(){
        recyclerView = findViewById(R.id.RecyclerViewInventory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        imageURLs = new ArrayList<>();
            // for testing purposes
        imageRef = storageReference.child("images/10a7b6b0-5611-4aba-8749" +
                "-54cff724de83");
        imageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    Uri downloadURI = task.getResult();
                    if(downloadURI != null){
                        imageURLs.add(downloadURI);
                    }
                }
            }
        });

        adapter = new ImageAdapter(Inventory.this, imageURLs);
        recyclerView.setAdapter(adapter);
    }

}