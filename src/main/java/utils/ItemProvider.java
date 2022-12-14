package utils;

import java.util.ArrayList;

public class ItemProvider {
    private static ArrayList<Item> items;
    private static ArrayList<Item> backpack = new ArrayList<>();

    private static void genItems() {
        items = new ArrayList<>();
//      Наполнение предметами по условию задачи
        items.add(new Item("Первый",1, 2, 2));
        items.add(new Item("Второй",3, 4, 2));
        items.add(new Item("Третий",5, 6, 3));
        items.add(new Item("Четвертый",4, 7, 4));
        items.add(new Item("Пятый",3, 3, 5));
        items.add(new Item("Шестой",2, 1, 3));
    }

    public static void transferToBackpack(Item item) {
        item.decreaseCount();
        if (item.getCount() == 0) items.remove(item);
        Item backpackItem = null;
        for (Item i:backpack) {
            if (i.getName().equals(item.getName())) {
                backpackItem = i;
                break;
            }
        }
        if (backpackItem == null) {
            backpackItem = new Item(item.getName(), item.getWeight(), 1, item.getCost());
            backpack.add(backpackItem);
        } else {
            backpackItem.increaseCount();
        }
    }

    public static ArrayList<Item> getItems() {
        if (items == null) genItems();
        return items;
    }

    public static ArrayList<Item> getBackpack() {
        return backpack;
    }
}
