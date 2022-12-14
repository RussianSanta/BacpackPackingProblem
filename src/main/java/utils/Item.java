package utils;

public class Item {
    private final String name;
    private final int weight;
    private final int cost;

    public Item(String name, int weight, int cost) {
        this.name = name;
        this.weight = weight;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "{" + name + "," + (int) weight + "," + (int) cost + "}";
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getCost() {
        return cost;
    }
}
