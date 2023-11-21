package com.example.mycloset;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class AddItem extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // initializes a dropdown of clothing styles
        Spinner styleSpinner = findViewById(R.id.StyleDropdown);
        ArrayAdapter<CharSequence> styleAdapter=
                ArrayAdapter.createFromResource(this,
                        R.array.ClothingStyles,
                        android.R.layout.select_dialog_multichoice);
        styleAdapter.setDropDownViewResource(android.R.layout.select_dialog_multichoice);
        styleSpinner.setAdapter(styleAdapter);


        // initializes dropdown of Clothing type (top, bottom, or shoe)
        Spinner typeSpinner = findViewById(R.id.TypeDropdown);
        ArrayAdapter<CharSequence> typeAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.ClothingType,
                        android.R.layout.simple_list_item_single_choice);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        typeSpinner.setAdapter(typeAdapter);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}