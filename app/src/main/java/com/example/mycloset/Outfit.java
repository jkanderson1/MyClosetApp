package com.example.mycloset;

import java.util.Random;
import androidx.appcompat.app.AppCompatActivity;

public class Outfit extends AppCompatActivity {
    private int Top;
    private int Bottom;
    private int Shoes;
    private String Season;
    private String Style;
    private boolean Favorite;

        public Outfit() {

        }
        public void setTop(int Top) {
        this.Top = Top;
        }
        public void setBottom(int Bottom) {
            this.Bottom = Bottom;
        }
        public void setShoes(int Shoes) {
            this.Shoes = Shoes;
        }
        public void setSeason(String Season) {
            this.Season = Season;
        }
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
        public String getSeason() {
            return Season;
        }
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
            this.Season = outfit.getSeason();
            this.Style = outfit.getStyle();
            this.Favorite = outfit.isFavorite();
        }
        public static Outfit randomGenerator(String Season) {
            Random random = new Random();
            int randomTop = random.nextInt(10)+1;
            int randomBottom = random.nextInt(10)+1;
            int randomShoes = random.nextInt(10)+1;

            return new Outfit();
        }



    }



