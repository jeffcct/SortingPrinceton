import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;


public class InsertionSorter {

    private static <T extends Comparable<T>> void shift(T[] items, int start, int end) {
        for (int i = end - 1; i >= start; i--) {
            items[i + 1] = items[i];
        }
    }
 
    public static <T extends Comparable<T>> void sort(T[] items) {
        for (int end = 0; end < items.length; end++) {
            if (items[end] == null) {
                return;
            }
            T inserting = items[end];
            for (int start = end - 1; start > -1; start--) {
                if (items[start].compareTo(inserting) <= 0) { /*if (items[start] <= inserting)*/
                    shift(items, start + 1, end);
                    items[start + 1] = inserting;
                    break;
                }
                if (start == 0) {
                    shift(items, 0, end);
                    items[0] = inserting;
                }
            }
        }
    }

    public static Integer enterNumber(String queryText) {
        while (true) {
            try {
                StdOut.printf("%s", queryText);
                return Integer.parseInt(StdIn.readString());
            } catch(Exception e) {
                StdOut.printf("Please enter a number! %s", queryText);
            }
        }
    }

    public static void playerTester() {
        StdOut.printf("Please enter how many numbers you will enter: ");
        int numNumbers = enterNumber("Please enter how many numbers you will enter: ");
        Integer[] items = new Integer[numNumbers];
        for (int i = 0; i < numNumbers; i++) {
            StdOut.printf("Please enter a number to add to items: ");
            items[i] = enterNumber("Please enter a nubmer to add to items: ");
            //sort(items);
            StdOut.printf("So far you have entered %s%n", Arrays.toString(items));
        } 
        StdOut.printf("Sorting array.%n");
        sort(items);
        StdOut.printf("Finished sorting array. Array is now: %s%n", Arrays.toString(items));
    }

    public static void automaticTester() {
        

        int numNumbers = enterNumber("Please enter how many numbers you will enter: ");
        Integer[] items = new Integer[numNumbers];
        for (int i = 0; i < numNumbers; i++) {
            items[i] = StdRandom.uniformInt(-numNumbers * 2, numNumbers * 2);
        } 
        StdOut.printf("Sorting array.%n");
        long time = System.nanoTime();
        sort(items);
        long elapsed = System.nanoTime() - time;
        StdOut.printf("Finished sorting array. Array is now: %s%n", Arrays.toString(items));
        StdOut.printf("Sorting took %.5f seconds", elapsed / 1000000000.0);
    }

    public static void main(String[] args) {
        switch (args[0].toLowerCase()) {
            case "automatic":
                automaticTester();
                break;
            default:
                playerTester();
        }
    }
}
