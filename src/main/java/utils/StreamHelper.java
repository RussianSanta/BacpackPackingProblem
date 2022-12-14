package utils;

public class StreamHelper {
    public static double getWeight(Item a) {
        return a.getWeight()* a.getCount();
    }
    public static double getPrice(Item a) {
        return a.getCost()* a.getCount();
    }
}
