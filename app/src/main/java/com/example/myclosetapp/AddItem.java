package com.example.myclosetapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.Collections;

public class AddItem extends AppCompatActivity {
    ImageButton favFalse,favTrue ;
    ViewSwitcher switchFavs;
    Button finished, settings;
    ImageView itemPicture;
    Spinner typeSpinner;
    TextView myCloset, styleInput,colorInput;
    Clothing clothing;
    String[] types,styles,colors;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // For testing purposes
            String pictureId = "2";
            ArrayList<String> seasons = new ArrayList<>();
            seasons.add("Spring");
            seasons.add("Summer");


            clothing = new Clothing();

        clothing.setSeasons(seasons);
        clothing.setPictureID(pictureId);



        favoriteMe();
        showImage();
        getColors();
        setStyleTV();
        setTypeSpinner();
        finishActivity();
        createSettings();
    }

    private void createSettings(){
        settings = findViewById(R.id.addItemToSettings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void showImage(){
        itemPicture = findViewById(R.id.addItemImage);
    }

    private void getColors(){
        colorInput = findViewById(R.id.addItemColorTV);
        colors = getResources().getStringArray(R.array.ColorOptions);
        boolean[] selectedColors = new boolean[colors.length];
        ArrayList<Integer> colorList = new ArrayList<>();
        colorInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(AddItem.this);
                builder.setTitle(R.string.colorHint);
                builder.setCancelable(false);
                builder.setMultiChoiceItems(colors, selectedColors, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked){
                            colorList.add(which);
                            Collections.sort(colorList);
                        } else {
                            colorList.remove(Integer.valueOf(which));
                        }
                    }
                });

                builder.setPositiveButton(R.string.Okay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i =0; i<colorList.size(); i++){
                            stringBuilder.append(colors[colorList.get(i)]);
                            clothing.addColorToArray(colors[colorList.get(i)]);

                            if(i != colorList.size()-1){
                                stringBuilder.append("\n");
                            }
                        }
                        colorInput.setText(stringBuilder.toString());
                    }
                });
                builder.setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }
    /*private void getColors(){
        colorInput = findViewById(R.id.addItemColorTextInputEditText);
        if (colorInput.getText() != null) {
            String addME = String.valueOf(colorInput.getText());
            ArrayList<String> addColor = new ArrayList<>();
            addColor.add(addME);
            clothing.setColors(addColor);
        }


    }*/

    private void setStyleTV(){
        styleInput = findViewById(R.id.addItemStyleTV);
        styles = getResources().getStringArray(R.array.ClothingStyles);
        boolean[] selectedStyles = new boolean[styles.length];
        ArrayList<Integer> styleList = new ArrayList<>();

        styleInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(AddItem.this);
                builder.setTitle(R.string.styleHint);
                builder.setCancelable(false);
                builder.setMultiChoiceItems(styles, selectedStyles, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked){
                            styleList.add(which);
                            Collections.sort(styleList);
                        } else {
                            styleList.remove(Integer.valueOf(which));
                        }
                    }
                });

                builder.setPositiveButton(R.string.Okay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i<styleList.size(); i++){
                            stringBuilder.append(styles[styleList.get(i)]);
                            clothing.addStylesToArray(styles[styleList.get(i)]);
                            if (i != styleList.size()-1){
                                stringBuilder.append("\n");
                            }
                        }
                        styleInput.setText(stringBuilder.toString());
                    }
                });
                builder.setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });
    }

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
        switchFavs = (ViewSwitcher)findViewById(R.id.addItemSwitchFavorite);
        favFalse = findViewById(R.id.addItemisFavFalse);
        favTrue = findViewById(R.id.addItemisFavTrue);

        Animation in = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);

        switchFavs.setInAnimation(in);
        switchFavs.setOutAnimation(out);

        favFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clothing.setFavorite(true);
                switchFavs.showNext();
            }
        });
        favTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clothing.setFavorite(false);
                switchFavs.showPrevious();
            }
        });
    }

    private void setTypeSpinner(){
        typeSpinner = findViewById(R.id.addItemTypeSpinner);
        types = getResources().getStringArray(R.array.ClothingType);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
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