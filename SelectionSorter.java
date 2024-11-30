import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class SelectionSorter {
    private static <T extends Comparable<T>> void swap(T[] items, int i, int j) {
        T temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    public static <T extends Comparable<T>> void sort(T[] items) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                return;
            }
            int minimum = i; 
            for (int j = i + 1; j < items.length; j++) {
                if (items[j] == null) {
                    continue;
                }
                if (items[j].compareTo(items[minimum]) < 0) {
                    minimum = j;
                }
            }
            swap(items, i, minimum);
        }
    }

    public static Integer enterNumber(String queryText) {
        while (true) {
            try {
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
            sort(items);
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
            items[i] = enterNumber("Please enter a nubmer to add to items: ");
        } 
        StdOut.printf("Sorting array.%n");
        sort(items);
        StdOut.printf("Finished sorting array. Array is now: %s%n", Arrays.toString(items));
    }

    public static void main(String[] args) {
        switch (args[0].toLowerCase()) {
            case "automatic":
                long time = System.nanoTime();
                automaticTester();
                long elapsed = System.nanoTime() - time;
                StdOut.printf("Sorting took %.5f seconds", elapsed / 1000000000.0);
                break;
            default:
                playerTester();
        }
    }
}