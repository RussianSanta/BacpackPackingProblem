package utils;

import java.util.ArrayList;

public class Cell {
    private final ArrayList<Item> items;
    private final int price;

    public Cell(ArrayList<Item> items, int price) {
        this.items = items;
        this.price = price;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getPrice() {
        return price;
    }
}
