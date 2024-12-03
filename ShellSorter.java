import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays;

public class ShellSorter {
    private static <T extends Comparable<T>> void swap(T[] items, int i, int j) {
        T temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    private static int getMaxH(int N) {
        int h = 1;
        while (h < N / 3) h = 3*h + 1;
        return h;
    }

    private static int nextH(int previousH) {
        return previousH / 3;
    }

    public static <T extends Comparable<T>> void sort(T[] items) {
        int N = items.length;
        int h = getMaxH(N);

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && items[j].compareTo(items[j - h]) <= 0; j -= h) {
                    swap(items, j, j - h);
                }
            }
            h = nextH(h);
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
        int numNumbers = enterNumber("Please enter how many numbers you will enter: ");
        Integer[] items = new Integer[numNumbers];
        for (int i = 0; i < numNumbers; i++) {
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
        if (args.length == 0) {
            playerTester();
            return;
        }
        switch (args[0].toLowerCase()) {
            case "automatic":
                automaticTester();
                break;
            default:
                playerTester();
        }
    }
}
