package com.example.mycloset;

import java.util.ArrayList;

public class Shoes extends Clothing {

    ArrayList colors = new ArrayList<String>();
    ArrayList season = new ArrayList<String>();
    ArrayList style = new ArrayList<String>();
    String Picture;
    Boolean Favorite;

    /**
     * @param id - identifier of object found in firebase
     * @return Clothing item
     */
    @Override
    public Clothing getItem(String id) {
        return null;
    }

    /**
     * Adds clothing item to firebase database, and sets appropriate string id
     *
     * @return id
     */
    @Override
    public String setItem() {
        return null;
    }
}
