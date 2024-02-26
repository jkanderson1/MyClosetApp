package com.example.myclosetapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Color;
import com.google.android.gms.tasks.OnCompleteListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
// import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import java.lang.reflect.Array;
import java.util.ArrayList;

import java.io.IOException;
import java.io.InputStream;

    public class Outfit {
        private FirebaseFirestore db;
        private CollectionReference outfitsCollection;
        private String id; // Unique identifier for the outfit
        private int topColor;
        private int bottomColor;
        private int shoesColor;
        private String style;
        private boolean favorite;

        public Outfit(String id, int topColor, int bottomColor, int shoesColor, String style, boolean favorite) {
            this.id = id;
            this.topColor = topColor;
            this.bottomColor = bottomColor;
            this.shoesColor = shoesColor;
            this.style = style;
            this.favorite = favorite;
            db = FirebaseFirestore.getInstance();
            outfitsCollection = db.collection("outfits");
        }

        public void matchOutfits(Bitmap image, final MatchOutfitsListener listener) {
            // Convert image to array of colors
            int[] pixels = new int[image.getWidth() * image.getHeight()];
            image.getPixels(pixels, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());

            int[] dominantColors = getDominantColors(pixels);

            for (int color : dominantColors) {
                outfitsCollection.whereEqualTo("color", color)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    ArrayList<String> matchedOutfits = new ArrayList<>();
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        matchedOutfits.add(document.getId());
                                    }
                                    listener.onMatchOutfits(matchedOutfits);
                                } else {
                                    listener.onError(task.getException().getMessage());
                                }
                            }
                        });
            }
        }

        private int[] getDominantColors(int[] pixels) {
            int[] dominantColors = new int[]{topColor, bottomColor, shoesColor};
            return dominantColors;
        }

        public interface MatchOutfitsListener {
            void onMatchOutfits(ArrayList<String> matchedOutfits);
            void onError(String errorMessage);
        }
    }

    /*
    private FirebaseFirestore db;
    private CollectionReference outfitsCollection;


    private int Top;
        private int Bottom;
        private int Shoes;
        // private String Season;
        private String Style;
        private boolean Favorite;

        public Outfit() {
            db = FirebaseFirestore.getInstance();
            outfitsCollection = db.collection("outfits");
     }

     public void matchOutfits(Bitmap image, final MatchOutfitsListener listener) {


            // convert image to array of colors
            int[] pixels = new int[image.getWidth() * image.getHeight()];
            image.getPixels(pixels, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());

            int[] dominantColors = getDominantColors(pixels);

            for(int color: dominantColors) {
                outfitsCollection.whereEqualTo("color", color)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    ArrayList<String> matchedOutfits = new ArrayList<>();
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        matchedOutfits.add(document.getId());
                                    }
                                    listener.onMatchOutfits(matchedOutfits);
                                } else {
                                    listener.onError(task.getException().getMessage());
                                }

                            }
                        });

            }


     }

     private int[] getDominantColors(int[] pixels) {
            int[] dominantColors = new int[]{Color.RED, Color.BLUE, Color.YELLOW, Color.BLACK, Color.WHITE};
            return dominantColors;
     }

     public interface MatchOutfitsListener {
            void onMatchOutfits(ArrayList<String> matchedOutfits);
            void onError(String errorMessage);
     }

     /*
        public void setTop(int Top) {
        this.Top = Top;
    }
        public void setBottom(int Bottom) {
        this.Bottom = Bottom;
    }
        public void setShoes(int Shoes) {
        this.Shoes = Shoes;
    }
        // public void setSeason(String Season) {
        // this.Season = Season;
   // }
        public void setStyle(String Style) {
        this.Style = Style;
    }
        public void setFavorite(boolean Favorite){
        this.Favorite = Favorite;
    }

        public int getTop() {
        return Top;
    }
        public int getBottom() {
        return Bottom;
    }
        public int getShoes() {
        return Shoes;
    }
       // public String getSeason() {
       // return Season;
   // }
        public String getStyle() {
        return Style;
    }
        public boolean isFavorite() {
        return Favorite;
    }

        public Outfit getOutfit() {
        return this;
    }

        public void setOutfit(Outfit outfit) {
            this.Top = outfit.getTop();
            this.Bottom = outfit.getBottom();
            this.Shoes = outfit.getShoes();
           // this.Season = outfit.getSeason();
            this.Style = outfit.getStyle();
            this.Favorite = outfit.isFavorite();
        }




*/


