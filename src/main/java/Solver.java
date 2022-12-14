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
//                  Если предмет не влез, то берем предыдущее значение
                    if (currItem.getWeight() > currWeight)
                        bp[i][j] = bp[i - 1][j];
//                  Если влез, то считаем итоговую цену, складывая цену предмета и максимальную цену для оставшегося веса
                    else {
                        int newPrice;
                        if (currWeight - currItem.getWeight() > 0)
                            newPrice = currItem.getCost() + bp[i - 1][currWeight - currItem.getWeight() - 1].getPrice();
                        else newPrice = currItem.getCost();
//                      Если предыдущий максимум был больше, то его и запишем
                        if (bp[i - 1][j].getPrice() >= newPrice)
                            bp[i][j] = bp[i - 1][j]; //запишем его
//                      Если новая цена оказалась больше, то внесем информацию в таблицу
                        else {
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
//      Вывод таблицы, можно закомментировать
        for (int i = 0; i < items.size(); i++) {
            for (int j = 0; j < weight; j++) {
                System.out.print(bp[i][j].getPrice() + " ");
            }
            System.out.print("\n");
        }
//      Поиск максимального значения цены в последнем столбце
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
