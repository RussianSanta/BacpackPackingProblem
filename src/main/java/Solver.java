import utils.Cell;
import utils.Item;

import java.util.ArrayList;

import static utils.ItemProvider.getItems;
import static utils.ItemProvider.printItems;

public class Solver {
    static int c = 0;

    public static void main(String[] args) {
        int reqWeight = 50; //По условию задачи
        ArrayList<Item> items = getItems();
        System.out.println("{название,вес,количество,стоимость}");
        printItems(items);
        System.out.println("--------Начало расчетов--------");
        ArrayList<Item> backpack = findMaxPrice(items, reqWeight);
        System.out.println("--------Конец  расчетов--------");

        System.out.print("Содержимое рюкзака: ");
        printItems(backpack);
        System.out.println("Вес рюкзака = " + backpack.stream().map(Item::getWeight).reduce(0, Integer::sum));
        System.out.println("Цена рюкзака = " + backpack.stream().map(Item::getCost).reduce(0, Integer::sum));
    }

    private static ArrayList<Item> findMaxPrice(ArrayList<Item> items, int weight) {
        Cell[][] bp = new Cell[items.size()][weight];
        for (int i = 0; i < items.size(); i++) {
            for (int j = 0; j < weight; j++) {
                int currWeight = j + 1;
                Item currItem = items.get(i);
//              Первую строку заполняем просто
                if (i == 0) {
                    if (currItem.getWeight() <= currWeight) {
                        ArrayList<Item> cellItems = new ArrayList<>();
                        cellItems.add(currItem);
                        bp[0][j] = new Cell(cellItems, currItem.getCost());
                    } else bp[0][j] = new Cell(new ArrayList<>(), 0);
                } else {
                    if (currItem.getWeight() > currWeight) //если очередной предмет не влезает в рюкзак,
                        bp[i][j] = bp[i - 1][j];    //записываем предыдущий максимум
                    else {
                        /*рассчитаем цену очередного предмета + максимальную цену для (максимально возможный для рюкзака вес − вес предмета)*/
                        int newPrice;
                        if (currWeight - currItem.getWeight() > 0)
                            newPrice = currItem.getCost() + bp[i - 1][currWeight - currItem.getWeight() - 1].getPrice();
                        else newPrice = currItem.getCost();

                        if (bp[i - 1][j].getPrice() >= newPrice) //если предыдущий максимум больше
                            bp[i][j] = bp[i - 1][j]; //запишем его
                        else {
                            /*иначе фиксируем новый максимум: текущий предмет + стоимость свободного пространства*/
                            ArrayList<Item> cellItems = new ArrayList<>();
                            cellItems.add(currItem);
                            if (currWeight - currItem.getWeight() > 0)
                                cellItems.addAll(bp[i - 1][currWeight - currItem.getWeight() - 1].getItems());
                            bp[i][j] = new Cell(cellItems, newPrice);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < items.size(); i++) {
            for (int j = 0; j < weight; j++) {
                System.out.print(bp[i][j].getPrice() + " ");
            }
            System.out.print("\n");
        }

        int max = -1;
        ArrayList<Item> resultItems = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (bp[i][weight - 1].getPrice() > max) {
                resultItems = bp[i][weight - 1].getItems();
                max = bp[i][weight - 1].getPrice();
            }
        }

        return resultItems;
    }
}
