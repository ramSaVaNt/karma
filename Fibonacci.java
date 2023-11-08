package DAALab;
import java.util.Scanner;
public class Fibonacci {

    // Iterative (non-recursive) approach
	//TC: O(n), SC:O(1)
    public static long iterativeFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        long[] fib = new long[n + 1];
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }

    // Recursive approach 
    //TC: O(2^n), SC:O(n)
    public static long recursiveFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
    }

    public static void printFibonacciSeries(int n, int approach) {
        System.out.print("Fibonacci series: ");
        if (approach == 1) {
            for (int i = 0; i <= n; i++) {
                System.out.print(iterativeFibonacci(i) + " ");
            }
        } else if (approach == 2) {
            for (int i = 0; i <= n; i++) {
                System.out.print(recursiveFibonacci(i) + " ");
            }
        } else {
            System.out.println("Invalid choice. Please choose 1 for iterative or 2 for recursive.");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();

        System.out.print("Choose an approach (1 for iterative, 2 for recursive): ");
        int approach = scanner.nextInt();

        if (approach == 1) {
            long startTime = System.nanoTime();
            long result = iterativeFibonacci(n);
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            System.out.println("Iterative Fibonacci result: " + result);
            System.out.println("Time elapsed (nanoseconds): " + timeElapsed);
            printFibonacciSeries(n, approach);
        } else if (approach == 2) {
            long startTime = System.nanoTime();
            long result = recursiveFibonacci(n);
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            System.out.println("Recursive Fibonacci result: " + result);
            System.out.println("Time elapsed (nanoseconds): " + timeElapsed);
            printFibonacciSeries(n, approach);
        } else {
            System.out.println("Invalid choice. Please choose 1 for iterative or 2 for recursive.");
        }
    }
}
