package com.example.myclosetapp.services;

import com.example.myclosetapp.Clothing;
import com.example.myclosetapp.ClothingType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

public class ItemsDataService
{
    private static ItemsDataService instance;

    private ArrayList<Clothing> itemsCache;

    private ArrayList<String> imagesTop = new ArrayList<>();

    private ArrayList<String> imagesBottom = new ArrayList<>();

    private ArrayList<String> imagesShoe = new ArrayList<>();

    private ItemsDataService() {
        imagesTop.add("white_shirt.jpeg");
        imagesTop.add("BlueShirt.jpg");
        imagesTop.add("black_shirt.jpeg");
        imagesTop.add("green_shirt.jpeg");
        imagesTop.add("grey_shirt.jpeg");

        imagesBottom.add("white_pants.jpeg");
        imagesBottom.add("blue_jeans.jpeg");
        imagesBottom.add("black_jeans.jpeg");
        imagesBottom.add("grey_sweatpants.jpeg");
        imagesBottom.add("skirt.jpg");
        imagesBottom.add("shorts.jpeg");

        imagesShoe.add("sneakers.jpg");
        imagesShoe.add("black_shoe.webp");
    }

    public static ItemsDataService getInstance()
    {
        if (instance == null) {
            instance = new ItemsDataService();
        }

        return instance;
    }

    public ArrayList<Clothing> fetchItems()
    {
        if (itemsCache == null) {
            itemsCache = getRandomItems();
        }

        return itemsCache;
    }

    public ArrayList<Clothing> fetchItemsByType(ClothingType type)
    {
        ArrayList<Clothing> items = fetchItems();
        ArrayList<Clothing> result = new ArrayList<>();

        items.forEach(item -> {
            if (Objects.equals(item.getType(), type.toString())) {
                result.add(item);
            }
        });

        return result;
    }

    private ArrayList<Clothing> getRandomItems()
    {
        ArrayList<Clothing> result = new ArrayList<>();

        imagesTop.forEach(image -> result.add(generateRandomItem(ClothingType.TOP, image)));
        imagesBottom.forEach(image -> result.add(generateRandomItem(ClothingType.BOTTOM, image)));
        imagesShoe.forEach(image -> result.add(generateRandomItem(ClothingType.SHOE, image)));

        return result;
    }

    private Clothing generateRandomItem(ClothingType type, String image)
    {
        ArrayList<String> styles = new ArrayList<>();
        styles.add("Formal");
        styles.add("Business Casual");
        styles.add("Athletic");
        styles.add("Casual");

        ArrayList<String> colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Orange");
        colors.add("Yellow");
        colors.add("Green");
        colors.add("Blue");
        colors.add("Indigo");
        colors.add("Violet");
        colors.add("White");
        colors.add("Black");

        ArrayList<String> seasons = new ArrayList<>();
        seasons.add("Summer");
        seasons.add("Spring");
        seasons.add("Winter");
        seasons.add("Fall");

        Clothing item = new Clothing();
        item.setColors(getRandomElementsFromList(colors, getRandomNumberInRange(1, 2)));
        item.setStyles(getRandomElementsFromList(styles, getRandomNumberInRange(1, 2)));
        item.setSeasons(getRandomElementsFromList(seasons, getRandomNumberInRange(1, 4)));
        item.setType(type);
        item.setPictureID(image);

        return item;
    }

    private ArrayList<String> getRandomElementsFromList(ArrayList<String> list, int amount)
    {
        Collections.shuffle(list);
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            result.add(list.get(i));
        }

        return result;
    }

    private String getRandomElementFromList(ArrayList<String> list)
    {
        return list.get(new Random().nextInt(list.size()));
    }

    private int getRandomNumberInRange(int min, int max) {
        return (new Random().nextInt(max - min)) + min;
    }
}
