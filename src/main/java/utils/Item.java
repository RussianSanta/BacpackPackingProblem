package utils;

public class Item {
    private final String name;
    private final double weight;
    private final double cost;
    private int count;

    public Item(String name, double weight, int count, double cost) {
        this.name = name;
        this.weight = weight;
        this.cost = cost;
        this.count = count;
    }

    @Override
    public String toString() {
        return "{" + name + "," + (int) weight + "," + count + "," + (int) cost + "}";
    }

    public String getName() {
        return name;
    }

    public double decreaseCount() {
        if (count > 0) return --count;
        return 0;
    }

    public double increaseCount() {
        return ++count;
    }

    public double getWeight() {
        return weight;
    }

    public double getCost() {
        return cost;
    }

    public int getCount() {
        return count;
    }
}
