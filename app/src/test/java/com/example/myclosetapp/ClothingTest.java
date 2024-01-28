package com.example.myclosetapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClothingTest {

    @Test
    public void setColors() {
    fail();
    }

    @Test
    public void getColors() {
        Clothing color = new Clothing();
        assertArrayEquals(new String[]{"red"}, color.getColors().toArray(new String[0]));
    }
   /* @Test
    public String setType(String type) {
        return type;
    }
    @Test
    public Boolean favoriteClothing() {

    }*/
}