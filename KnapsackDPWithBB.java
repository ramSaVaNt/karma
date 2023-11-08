package DAALab;
/*
import java.util.Arrays;

public class KnapsackDPWithBB {
    public static int knapsack(int capacity, int[] weights, int[] values, int n) {
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], values[i - 1] + dp[i - 1][w - weights[i - 1]]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        int maxProfit = dp[n][capacity];

        // Print capacity, items, values, and weights
        System.out.println("Knapsack Capacity: " + capacity);
        System.out.println("Number of Items: " + n);
        System.out.println("Values of Items: " + Arrays.toString(values));
        System.out.println("Weights of Items: " + Arrays.toString(weights));

        return maxProfit;
    }

    public static void main(String[] args) {
        int capacity = 10;
        int[] weights = {2, 2, 4, 5};
        int[] values = {3, 7, 2, 9};
        int n = weights.length;

        int maxProfit = knapsack(capacity, weights, values, n);
        System.out.println("Maximum Profit Earned: " + maxProfit);
    }
}
*/
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Item {
    int weight;
    int value;
    double bound;
    int level;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.bound = 0.0;
        this.level = 0;
    }
}

public class KnapsackDPWithBB {
    public static int knapsack(int capacity, Item[] items) {
        Arrays.sort(items, Comparator.comparingDouble(i -> -i.value / (double) i.weight));

        int n = items.length;
        int maxProfit = 0;
        int currentWeight = 0;

        PriorityQueue<Item> queue = new PriorityQueue<>(Comparator.comparingDouble(i -> -i.bound));
        Item root = new Item(0, 0);
        queue.offer(root);

        // Print capacity, items, values, and weights
        System.out.println("Knapsack Capacity: " + capacity);
        System.out.println("Number of Items: " + n);
        System.out.print("Values of Items: ");
        for (Item item : items) {
            System.out.print(item.value + " ");
        }
        System.out.println();
        System.out.print("Weights of Items: ");
        for (Item item : items) {
            System.out.print(item.weight + " ");
        }
        System.out.println();

        while (!queue.isEmpty()) {
            Item current = queue.poll();
            int level = current.level;

            if (level == n) {
                if (current.value > maxProfit) {
                    maxProfit = current.value;
                }
                continue;
            }

            // Include the current item
            Item include = new Item(current.weight + items[level].weight, current.value + items[level].value);
            include.level = level + 1;
            include.bound = calculateBound(include, items, capacity, n);

            if (include.bound > maxProfit) {
                queue.offer(include);
            }

            // Exclude the current item
            Item exclude = new Item(current.weight, current.value);
            exclude.level = level + 1;
            exclude.bound = calculateBound(exclude, items, capacity, n);

            if (exclude.bound > maxProfit) {
                queue.offer(exclude);
            }
        }

        // Print the selected items
        System.out.println("Maximum Profit Earned: " + maxProfit);

        return maxProfit;
    }

    private static double calculateBound(Item current, Item[] items, int capacity, int n) {
        if (current.weight > capacity) {
            return 0.0;
        }

        double bound = current.value;
        int remainingWeight = capacity - current.weight;
        int level = current.level;

        while (level < n && items[level].weight <= remainingWeight) {
            bound += items[level].value;
            remainingWeight -= items[level].weight;
            level++;
        }

        if (level < n) {
            bound += (double) remainingWeight * items[level].value / items[level].weight;
        }

        return bound;
    }

    public static void main(String[] args) {
        int capacity = 10;
        Item[] items = {
            new Item(2, 3),
            new Item(2, 7),
            new Item(4, 2),
            new Item(5, 9)
        };

        int maxProfit = knapsack(capacity, items);
    }
}
