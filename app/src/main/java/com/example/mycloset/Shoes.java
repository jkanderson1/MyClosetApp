package com.example.mycloset;

public class Shoes extends Clothing {

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
     * @return id
     */
    @Override
    public String setItem() {
        // TODO: add shoe to firebase
        return null;
    }
}
