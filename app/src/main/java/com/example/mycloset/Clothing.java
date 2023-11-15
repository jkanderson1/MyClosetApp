package com.example.mycloset;

public abstract class Clothing {

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
