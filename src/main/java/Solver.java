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
        findMaxPrice(items, reqWeight);
//        ArrayList<Item> backpack = getBackpack();
//        System.out.println("--------Конец  расчетов--------");
//        System.out.println("Содержимое рюкзака: " + backpack);
//        System.out.println("Вес рюкзака = " + backpack.stream().map(StreamHelper::getWeight).reduce(0.0, Double::sum));
//        System.out.println("Цена рюкзака = " + backpack.stream().map(StreamHelper::getPrice).reduce(0.0, Double::sum));
    }

    private static void findMaxPrice(ArrayList<Item> items, int weight) {
        int[][] bp = new int[items.size()][weight];
        for (int i = 0; i < items.size(); i++) {
            for (int j = 0; j < weight; j++) {
                int currWeight = j + 1;
                Item currItem = items.get(i);
//              Первую строку заполняем просто
                if (i == 0) {
                    if (currItem.getWeight() <= currWeight) {
                        bp[0][j] = currItem.getCost();
                    } else bp[0][j] = 0;
                } else {
                    if (currItem.getWeight() > currWeight) //если очередной предмет не влезает в рюкзак,
                        bp[i][j] = bp[i - 1][j];    //записываем предыдущий максимум
                    else {
                        /*рассчитаем цену очередного предмета + максимальную цену для (максимально возможный для рюкзака вес − вес предмета)*/
                        int newPrice;
                        if (currWeight - currItem.getWeight() > 0)
                            newPrice = currItem.getCost() + bp[i - 1][currWeight - currItem.getWeight() - 1];
                        else newPrice = currItem.getCost();

                        if (bp[i - 1][j] >= newPrice) //если предыдущий максимум больше
                            bp[i][j] = bp[i - 1][j]; //запишем его
                        else {
                            /*иначе фиксируем новый максимум: текущий предмет + стоимость свободного пространства*/
                            bp[i][j] = newPrice;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < items.size(); i++) {
            for (int j = 0; j < weight; j++) {
                System.out.print(bp[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
