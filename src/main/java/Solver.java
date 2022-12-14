import utils.Item;
import utils.StreamHelper;

import java.util.ArrayList;

import static utils.ItemProvider.getBackpack;
import static utils.ItemProvider.getItems;

public class Solver {
    static int c = 0;

    public static void main(String[] args) {
        int reqWeight = 50; //По условию задачи
        ArrayList<Item> items = getItems();
        System.out.println("{название,вес,количество,стоимость}");
        System.out.println(items);
        System.out.println("--------Начало расчетов--------");
//        findMaxPrice(items, reqWeight);
//        ArrayList<Item> backpack = getBackpack();
//        System.out.println("--------Конец  расчетов--------");
//        System.out.println("Содержимое рюкзака: " + backpack);
//        System.out.println("Вес рюкзака = " + backpack.stream().map(StreamHelper::getWeight).reduce(0.0, Double::sum));
//        System.out.println("Цена рюкзака = " + backpack.stream().map(StreamHelper::getPrice).reduce(0.0, Double::sum));
    }

    private static void findMaxPrice(ArrayList<Item> items, int weight) {
        ArrayList<ArrayList<Item>> backpacks = new ArrayList<>();
        double[][] bp = new double[items.size()][];
        for (int i = 0; i < items.size(); i++) {
            ArrayList<Item> backpack = new ArrayList<>();
            for (int j = 0; j < weight; j++) {
                int currWeight = j + 1;
                Item currItem = items.get(i);
//              Если вес предмета меньше веса столбца
                if (currItem.getWeight() <= currWeight) {
                    int max = -1;
//                  Если в рюкзаке ничего нет или положили меньше, чем всего есть вещей
                    if (backpack.get(i) == null || backpack.get(i).getCount() < currItem.getCount()) {

                    }
                }
            }
        }
    }

//    private static void findMaxPrice(ArrayList<Item> items, int weight) {
//        if (items.size() > 0) {
//            double max = -1;
//            Item maxItem = null;
//            for (Item item : items) {
////              Тут выбирается предмет, который будет переложен в рюкзак
//                if (item.getCost()/item.getWeight() > max && item.getWeight() <= weight) {
//                    maxItem = item;
//                    max = item.getCost()/ item.getWeight();
//                }
////                if (item.getCost() > max && item.getWeight() <= weight) {
////                    maxItem = item;
////                    max = item.getCost();
////                }
//
//            }
//            if (maxItem != null) {
//                weight -= maxItem.getWeight();
//                transferToBackpack(maxItem);
//                if (c > 9) System.out.print(c++ + ") ");
//                else System.out.print(c++ + ")  ");
//                System.out.println(items);
//                findMaxPrice(items, weight);
//            }
//        }
//    }
}
