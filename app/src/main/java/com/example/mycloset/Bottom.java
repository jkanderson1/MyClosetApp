package com.example.mycloset;

public class Bottom extends Clothing {



    /**
     * @param id - identifier of object found in firebase
     * @return Clothing item
     */
    @Override
    public Clothing getItem(String id) {
        if(identification== null){
            setItem();
            return null;
        }

        return null;
    }

    /**
     * Adds clothing item to firebase database, and sets appropriate string id
     *
     * @return id
     */
    @Override
    public String setItem() {
        //TODO: add Bottom to firebase
        return null;
    }
}
