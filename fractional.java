package DAALab;
import java.util.PriorityQueue;

class MinHeapNode implements Comparable<MinHeapNode> {
    char data;
    int freq;
    MinHeapNode left, right;

    public MinHeapNode(char data, int freq) {
        this.data = data;
        this.freq = freq;
        left = right = null;
    }

    @Override
    public int compareTo(MinHeapNode other) {
        return Integer.compare(this.freq, other.freq);
    }
}

public class fractional {
    public static void printCodes(MinHeapNode root, String str) {
        if (root == null) {
            return;
        }
        if (root.data != '$') {
            System.out.println(root.data + ": " + str);
        }
        printCodes(root.left, str + "0");
        printCodes(root.right, str + "1");
    }

    public static void huffmanCode(char[] data, int[] freq, int size) {
        PriorityQueue<MinHeapNode> minHeap = new PriorityQueue<>();

        for (int i = 0; i < size; i++) {
            minHeap.add(new MinHeapNode(data[i], freq[i]));
        }

        while (minHeap.size() > 1) {
            MinHeapNode left = minHeap.poll();
            MinHeapNode right = minHeap.poll();
            MinHeapNode temp = new MinHeapNode('$', left.freq + right.freq);
            temp.left = left;
            temp.right = right;
            minHeap.add(temp);
        }

        printCodes(minHeap.poll(), "");
    }

    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D'};
        int[] freq = {23, 12, 34, 10};
        huffmanCode(data, freq, 4);
    }
}
