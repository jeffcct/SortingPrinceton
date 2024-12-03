import java.util.Arrays;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdDraw;

public class ConvexHull {
    public static Point findMinY(Point[] points) {
        Point minimum = points[0];
        for (int i = 1; i < points.length; i++) {
            if (points[i].y < minimum.y) minimum = points[i];
        }
        return minimum;
    }

    public static Point[] sortByGradient(Point[] points, Point minPoint) {
        PointKeyPair[] pairedPoints = new PointKeyPair[points.length - 1];
        int maxIndex = 0;
        for (int i = 0 ; i < points.length; i++) {
            if (points[i] == null || points[i] == minPoint) continue;
            pairedPoints[maxIndex] = new PointKeyPair(points[i], minPoint.slope(points[i]));
            maxIndex += 1;
        }
        InsertionSorter.sort(pairedPoints);
        Point[] out = new Point[points.length - 1];
        for (int i = 0; i < pairedPoints.length; i++) {
            out[i] = pairedPoints[i].key;
        }
        return out;
    }

    public static Stack<Point> getHull(Point[] points) {
        Stack<Point> hull = new Stack<Point>();
        Point minPoint = findMinY(points);
        hull.push(minPoint);
        Point[] sortedPoints = sortByGradient(points, minPoint);
        hull.push(sortedPoints[0]);
        for (int i = 1; i < sortedPoints.length; i++) {
            while (!hull.isEmpty()) {
                Point current = hull.pop();
                Point previous = hull.pop();
                Point next = sortedPoints[i];
                int gradient = Point.gradient(previous, current, next);
                if (gradient == 2) {
                    hull.push(previous);
                    hull.push(current);
                    hull.push(next);
                    break;
                } else {
                    hull.push(previous);
                }
            }
        }
        return hull;
    }


    public static void visualize(Point[] points, Stack<Point> hull) {
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            StdDraw.point(p.x, p.y);
        }
        StdDraw.show();
        Point initial = hull.pop();
        for (Point p : hull) {
            StdDraw.line(p.x, p.y, initial.x, initial.y);
            initial = p;
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

    public static Point enterPoint(String queryText) {
        while (true) {
            try {
                StdOut.printf("%s", queryText);
                return new Point(StdIn.readDouble(), StdIn.readDouble());
            } catch(Exception e) {
                StdOut.printf("Please enter a two numbers seperated by spaces! %s", queryText);
            }
        }
    }

    public static void automaticTester() {
        int numPoints = enterNumber("Please enter how many points there are: ");
        Point[] points = new Point[numPoints];
        for (int i = 0; i < numPoints; i++) {
            points[i] = new Point(StdRandom.uniformInt(100, 30000), StdRandom.uniformInt(100, 30000));
        }
        StdOut.printf("Finished creating array: %s%n", Arrays.toString(points));
        StdOut.printf("Finding Hull.%n");
        Stack<Point> hull = getHull(points);
        StdOut.printf("Found Hull. Printing:");
        visualize(points, hull);
        StdDraw.show();
    }

    public static void playerTester() {
        int numPoints = enterNumber("Please enter how many points there are: ");
        Point[] points = new Point[numPoints];
        for (int i = 0; i < numPoints; i++) {
            points[i] = enterPoint("Please enter two numbers representing the x and y of the point.");
        }
        StdOut.printf("Finished creating array: %s%n", Arrays.toString(points));
        StdOut.printf("Finding Hull.%n");
        Stack<Point> hull = getHull(points);
        StdOut.printf("Found Hull. Printing:");
        while (!hull.isEmpty()) {
            StdOut.printf(hull.pop().toString());
        }
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

class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static int gradient(Point p1, Point p2, Point p3) {
        double val = (p2.y - p1.y) * (p3.x - p2.x) - (p2.x - p1.x) * (p3.y - p2.y);
        if (val == 0) {
            return 0;
        } 
        return (val > 0) ? 1 : 2;
    }

    public double slope(Point other) {
        return (other.y - this.y) / (other.x - this.x);
    }

    public String toString() {
        return String.format("Point: (%.3f, %.3f)", this.x, this.y);
    }
}

class PointKeyPair implements Comparable<PointKeyPair> {
    Point key;
    Double value;

    public PointKeyPair(Point key, Double value) {
        this.key = key;
        this.value = value;
    }


    @Override
    public int compareTo(PointKeyPair o) {
        if (this.value == null && o.value == null) return 0;
        if (this.value == null) return -1;
        if (o.value == null) return 1;
        return value.compareTo(o.value);
    }

    public String toString() {
        return String.format("PointKeyPair: (key: %s, value: %.2f)", key.toString(), value);
    }
}