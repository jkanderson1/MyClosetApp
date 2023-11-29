package com.example.mycloset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.lang.reflect.Array;

public class AddItem extends AppCompatActivity {
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    // Initializing all necessary variables
    Spinner styleSpinner, typeSpinner;
    Button finished;

    ImageButton picture, favorite;

    Clothing clothing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // initializes a dropdown of clothing styles
        styleSpinner = findViewById(R.id.StyleDropdown);
        ArrayAdapter<CharSequence> styleAdapter=
                ArrayAdapter.createFromResource(this,
                        R.array.ClothingStyles,
                        android.R.layout.select_dialog_multichoice);
        styleAdapter.setDropDownViewResource(android.R.layout.select_dialog_multichoice);
        styleSpinner.setAdapter(styleAdapter);


        // initializes dropdown of Clothing type (top, bottom, or shoe)
        typeSpinner = findViewById(R.id.TypeDropdown);
        ArrayAdapter<CharSequence> typeAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.ClothingType,
                        android.R.layout.simple_list_item_single_choice);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        typeSpinner.setAdapter(typeAdapter);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            // Type spinner is single select so this will also save the type
            // to be added to the Clothing object
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                if (item.equalsIgnoreCase("top")){
                    clothing = new Top();
                } else if(item.equalsIgnoreCase("bottom")){
                    clothing = new Bottom();
                } else {
                    clothing = new Shoes();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // initializes Photo button, and navigates to Add Photo page
        picture = findViewById(R.id.AddPictureButton);
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        AddImage.class);
                startActivity(intent);
                finish();
                //TODO: figure out how to get back to this screen from add
                // image screen
            }
        });

        // initialize Button, will return to home screen and will add
        // clothing item to firebase
        finished = findViewById(R.id.AddItemButton);
        finished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: set the rest of the variables in clothing

                Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                startActivity(intent);
                finish();
            }
        });

        // initialize favorite button and change color on click
        favorite = findViewById(R.id.FavoriteMe);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: figure out how to get the image to change colors
                clothing.setFavorite(true);
            }
        });
    }
}