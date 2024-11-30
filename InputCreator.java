import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class InputCreator {
    public static void main(String[] args) {
        int numNumbers = Integer.parseInt(args[0]);
        StdOut.printf("%d%n", numNumbers);
        for (int i = 0; i < numNumbers; i++) {
            StdOut.printf("%d%n", StdRandom.uniformInt(-2 * numNumbers, 2 * numNumbers));
        }
    }
}
