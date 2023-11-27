package com.example.mycloset;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AddItem extends AppCompatActivity {
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // initializes a dropdown of clothing styles
        Spinner stylesSpinner = findViewById(R.id.StyleSpinner);
        ArrayAdapter<CharSequence> styleAdapter=
                ArrayAdapter.createFromResource(this,
                        R.array.ClothingStyles,
                        android.R.layout.simple_spinner_item);
        styleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        stylesSpinner.setAdapter(styleAdapter);
    }
}