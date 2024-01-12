package com.example.myclosetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class AddItem extends AppCompatActivity {
    ImageButton favFalse,favTrue ;
    ViewSwitcher swichFavs;

    Button finished, settings;
    ImageView itemPicture;
    Spinner typeSpinner, styleSpinner;
    TextView myCloset;
    Clothing clothing;
    String[] types,styles;
    EditText colorInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // For testing purposes
            ArrayList<String> colors = new ArrayList<>();
            colors.add("Blue");
            colors.add("White");
            String pictureId = "2";
            ArrayList<String> seasons = new ArrayList<>();
            seasons.add("Spring");
            seasons.add("Summer");
            ArrayList<String> style = new ArrayList<>();
            style.add("Business Casual");
            style.add("Business Formal");

            clothing = new Clothing();

        clothing.setColors(colors);
        clothing.setSeasons(seasons);
        clothing.setStyles(style);



        favoriteMe();
        //getColors();
        setStyleSpinner();
        setTypeSpinner();
        finishActivity();

        settings = findViewById(R.id.button3);
        settings.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(getApplicationContext(), Settings.class);
                                            startActivity(intent);
                                            finish();
                                        }
        });

        finished = findViewById(R.id.addImageButton);
        finished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(), AddImage.class);
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setStyleSpinner() {

    }

    /*private void getColors() {
        colorInput = findViewById(R.id.addItemColorTextInputEditText);

       colorInput.setOnEditorActionListener(new TextView
       .OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                ArrayList<String> colors = new ArrayList<String>();
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    colors.add(v.getText().toString());
                    clothing.setColors(colors);
                    handled = true;
                }
                return false;
            }
        });
    }*/



    private void finishActivity(){
        finished = findViewById(R.id.addItemToAddImage);
        finished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!clothing.getType().isEmpty() && !clothing.getColors().isEmpty() && !clothing.getStyles().isEmpty()){
                    clothing.addToCloset();
                    Intent intent  = new Intent(getApplicationContext(),
                            HomeScreen.class);
                    startActivity(intent);
                    finish();
                }
                // TODO: force type, style, and color to be filled before
                //  adding
                /*else {
                    if (clothing.getType().isEmpty()){
                        Toast.makeText(getApplicationContext(),
                                getResources().getText(R.string.typeHint), Toast.LENGTH_SHORT);

                    }
                    if (clothing.getStyles().isEmpty()){
                        Toast.makeText(getApplicationContext(),
                                getResources().getText(R.string.styleHint),
                                Toast.LENGTH_SHORT);
                    }
                    if (clothing.getColors().isEmpty()) {
                        Toast.makeText(getApplicationContext(),
                                getResources().getText(R.string.colorHint),
                                Toast.LENGTH_SHORT);
                    }
                }*/

            }
        });

    }

    private void favoriteMe(){
        swichFavs = (ViewSwitcher)findViewById(R.id.addItemSwitchFavorite);
        favFalse = findViewById(R.id.addItemisFavFalse);
        favTrue = findViewById(R.id.addItemisFavTrue);

        Animation in = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);

        swichFavs.setInAnimation(in);
        swichFavs.setOutAnimation(out);

        favFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clothing.setFavorite(true);
                swichFavs.showNext();
            }
        });
        favTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clothing.setFavorite(false);
                swichFavs.showPrevious();
            }
        });
    }


    private void setTypeSpinner(){
        typeSpinner = findViewById(R.id.addItemTypeSpinner);
        types = getResources().getStringArray(R.array.ClothingType);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, types);
        typeSpinner.setAdapter(adapter);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).toString().equalsIgnoreCase(ClothingType.BOTTOM.toString())){
                    clothing.setType(ClothingType.BOTTOM);
                } else if (parent.getItemAtPosition(position).toString().equalsIgnoreCase(ClothingType.TOP.toString())){
                    clothing.setType(ClothingType.TOP);
                } else if (parent.getItemAtPosition(position).toString().equalsIgnoreCase(ClothingType.SHOE.toString())){
                    clothing.setType(ClothingType.SHOE);
                }else {
                    onNothingSelected(parent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}