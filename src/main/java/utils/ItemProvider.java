package utils;

import java.util.ArrayList;

public class ItemProvider {
    private static ArrayList<Item> items;
    private static ArrayList<Item> backpack = new ArrayList<>();

    private static void genItems() {
        items = new ArrayList<>();
//      Наполнение предметами по условию задачи
        for (int i = 0; i < 2; i++) {
            items.add(new Item("Первый", 1, 2));
        }
        for (int i = 0; i < 4; i++) {
            items.add(new Item("Второй", 3, 2));
        }
        for (int i = 0; i < 6; i++) {
            items.add(new Item("Третий", 5, 3));
        }
        for (int i = 0; i < 7; i++) {
            items.add(new Item("Четвертый", 4, 4));
        }
        for (int i = 0; i < 3; i++) {
            items.add(new Item("Пятый", 3, 5));
        }
        items.add(new Item("Шестой", 2, 3));
    }

    public static ArrayList<Item> getItems() {
        if (items == null) genItems();
        return items;
    }

    public static void printItems(ArrayList<Item> items) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        int count = 0;
        Item lastItem = null;
        for (Item item : items) {
            if (count > 0 && lastItem != null && !item.getName().equals(lastItem.getName())) {
                builder.append("{")
                        .append(lastItem.getName())
                        .append(",")
                        .append(lastItem.getWeight())
                        .append(",").append(count)
                        .append(",")
                        .append(lastItem.getCost())
                        .append("}, ");
                count = 1;
            } else {
                count++;
            }
            lastItem = item;
        }
        builder.append("{")
                .append(lastItem.getName())
                .append(",")
                .append(lastItem.getWeight())
                .append(",").append(count)
                .append(",")
                .append(lastItem.getCost())
                .append("}");
        builder.append("]");
        System.out.println(builder);
    }

    public static ArrayList<Item> getBackpack() {
        return backpack;
    }
}
