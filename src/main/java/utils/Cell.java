package utils;

public class Cell {
    private final Item[] items;
    private final int price;

    public Cell(Item[] items, int price) {
        this.items = items;
        this.price = price;
    }

    public Item[] getItems() {
        return items;
    }

    public int getPrice() {
        return price;
    }
}
