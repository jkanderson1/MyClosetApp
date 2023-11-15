package com.example.mycloset;

import java.util.ArrayList;


public class Top extends Clothing{

    public int TopID;
    ArrayList colors = new ArrayList<String>();



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
        // TODO: add top to firebase
        return null;
    }
}
