package com.example.mycloset;

import java.util.Random;

public abstract class Clothing {

    public String identification;

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
     *
     * @param id - identifier of object found in firebase
     * @return Clothing item
     */
    public abstract Clothing getItem(String id);

    /**
     * Adds clothing item to firebase database, and sets appropriate string id
     * @return id
     */
    public abstract String setItem();
}
