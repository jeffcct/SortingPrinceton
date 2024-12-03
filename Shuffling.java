import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Shuffling {

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

    public static String[] shuffle(String[] items) {
        StringKeyPair[] pairedStrings = new StringKeyPair[items.length];
        for (int i = 0 ; i < items.length; i++) {
            if (items[i] == null) break;
            pairedStrings[i] = new StringKeyPair(items[i], StdRandom.uniformDouble());
        }
        ShellSorter.sort(pairedStrings);
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) break;
            items[i] = pairedStrings[i].key;
        }
        return items;
    }

    public static void automaticTester() {
        int numItems = enterNumber("Please enter how many numbers there are: ");
        
        String[] items = new String[numItems];
        for (int i = 0; i < numItems; i++) {
            items[i] = String.format("Item %d", i);
        }
        StdOut.printf("Finished creating array: %s%n", Arrays.toString(items));
        StdOut.printf("Started Sorting.%n");
        shuffle(items);
        StdOut.printf("Finished sorting array. Array is now: %s%n", Arrays.toString(items));
    }

    public static void playerTester() {
        int numItems = enterNumber("Please enter how many numbers there are: ");
        
        String[] items = new String[numItems];
        for (int i = 0; i < numItems; i++) {
            StdOut.printf("Enter the next item: ");
            items[i] = StdIn.readString();
        }
        StdOut.printf("Finished creating array: %s%n", Arrays.toString(items));
        StdOut.printf("Started Sorting.%n");
        shuffle(items);
        StdOut.printf("Finished sorting array. Array is now: %s%n", Arrays.toString(items));
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


class StringKeyPair implements Comparable<StringKeyPair> {
    String key;
    Double value;

    public StringKeyPair(String key, Double value) {
        this.key = key;
        this.value = value;
    }


    @Override
    public int compareTo(StringKeyPair o) {
        if (this.value == null && o.value == null) return 0;
        if (this.value == null) return -1;
        if (o.value == null) return 1;
        return value.compareTo(o.value);
    }
}