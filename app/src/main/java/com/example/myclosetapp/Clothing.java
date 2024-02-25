package com.example.myclosetapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

public class Clothing {

    private final String ITEMS = "Items";
    private ArrayList<String> colors, seasons, styles;
    private String identification, pictureID, type;
    private Boolean favorite;
    private static Closet myCloset = Closet.getInstance();


    /**
     * Initializes a new instance of a clothing item with only an
     * Identification number
     *
     * all other variables are null
     *
     */
    public Clothing (){
        identification = createID();
        colors = null;
        seasons = null;
        styles = null;
        pictureID = null;
        favorite = false;
        type = null;
    }

    /**
     * adds clothing item to Firebase Realtime database
     *
     * Item is set as a child of "Items" and then as a child of its id
     */
    public void addToCloset(){
        myCloset.addClothes(this);
    }

    /**
     * Creates a randomly generated Integer that is converted into a string
     * @return identification
     */
    private String createID(){

        if (identification != null) {
            return identification;
        }
        return myCloset.createIDs();
    }

    public void addColorToArray(String color){
       if (this.colors == null){
           this.colors = new ArrayList<>();
       }
       this.colors.add(color);
    }

    public void addStylesToArray(String style){
        if (this.styles == null){
            this.styles = new ArrayList<>();
        }
        this.styles.add(style);
    }

    public void addSeasonsToArray(String season){
        if (this.seasons == null){
            this.seasons = new ArrayList<>();
        }
        this.seasons.add(season);
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

    public void setType(ClothingType type){
        this.type = type.toString();
    }

    public String getType(){return this.type;}
}
