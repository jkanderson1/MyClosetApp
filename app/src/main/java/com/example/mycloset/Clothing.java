package com.example.mycloset;

import com.google.firebase.Firebase;

import java.util.ArrayList;
import java.util.Random;

public abstract class Clothing {

    public String identification;   // randomly generated string to identify
    // item in firebase
    public ArrayList<String> colors;    // array list of colors in the item
    public ArrayList<String> seasons;   // arraylist of seasons appropriate
    // for item
    public ArrayList<String> styles;    // arraylist of Styles appropriate
    // for item
    public String pictureID;   // randomly generated string identification of
    // associated image
    public Boolean favorite;    // if the item is favorited

    // TODO: Connect clothing to firebase

    /**
     * Initializes a new instance of a clothing item with only an
     * Identification number
     *
     * all other variables are null
     */
    public Clothing (){
        identification = createID();
        colors = null;
        seasons = null;
        styles = null;
        pictureID = null;
        favorite = null;
    }

    /**
     * Creates a randomly generated Integer that is converted into a string
     * @return identification
     */
    protected String createID(){

        if (identification != null) {
            return identification;
        }
        Random random= new Random();
        // TODO: add functionality to access all ID's in firebase and then
        //  make sure generated ID is unique;
        return Integer.toString(random.nextInt());
    }

    /**
     * sets colors array
     * @param colors
     */
    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    /**
     * @return color array
     */
    public ArrayList<String> getColors() {
        return colors;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Boolean getFavorites(){
        return favorite;
    }

    public void setSeasons(ArrayList<String> seasons){
        this.seasons = seasons;
    }

    public ArrayList<String> getSeasons() {
        return seasons;
    }

    public void setStyles(ArrayList<String> styles) {
        this.styles = styles;
    }

    public ArrayList<String> getStyles() {
        return styles;
    }

    public void setPictureID(String pictureID) {
        this.pictureID = pictureID;
    }

    public String getPictureID() {
        return pictureID;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getIdentification() {
        return identification;
    }
}
