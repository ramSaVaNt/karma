package DAALab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Item {
    int weight;
    int value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

public class frac3 {
    public static double getMaxValue(List<Item> items, int capacity) {
        // Sort the items by value per unit weight in decreasing order (greedy step)
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                double ratio1 = (double) item1.value / item1.weight;
                double ratio2 = (double) item2.value / item2.weight;
                if (ratio1 > ratio2) {
                    return -1;
                } else if (ratio1 < ratio2) {
                    return 1;
                }
                return 0;
            }
        });

        double totalValue = 0.0;
        int remainingCapacity = capacity;

        System.out.println("Item\tWeight\tValue\tFraction");

        for (Item item : items) {
            if (remainingCapacity <= 0) {
                break;
            }

            double fraction = 1.0;
            if (item.weight > 0) {
                fraction = Math.min(1.0, (double) remainingCapacity / item.weight);
            }

            System.out.printf("%d\t%d\t%d\t%.2f%n", items.indexOf(item) + 1, item.weight, item.value, fraction);

            if (item.weight <= remainingCapacity) {
                totalValue += item.value * fraction;
                remainingCapacity -= item.weight * fraction;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Item(10, 60));
        items.add(new Item(20, 100));
        items.add(new Item(30, 120));

        int capacity = 50;

        double maxValue = getMaxValue(items, capacity);
        System.out.println("\nMaximum value in the knapsack: " + maxValue);
    }
}
