import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
//    private LineSegment[] segments;
    private ArrayList<LineSegment> found = new ArrayList<>();

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("The input is null.");

        checkDuplicated(points);
        checkDuplicated(points);

        int N = points.length;
        Point[] pointsCopy = Arrays.copyOf(points, N);


        for (int i = 0; i < N - 3; i++) {
            Point p = pointsCopy[i];
            Arrays.sort(pointsCopy, p.slopeOrder());


//            int first = 1;
//            double SLOPE = pointsCopy[i].slopeTo(pointsCopy[first]);
//            while (first < N) {
//                do {
//                    first++;
//
//                } while (first < N && Double.compare(pointsCopy[i].slopeTo(pointsCopy[first]), SLOPE) == 0);
//
//            }

            for (int origin = 0, first = 1, last = 2; last < N; last++) {
                while (last < N && Double.compare(pointsCopy[origin].slopeTo(pointsCopy[first]), pointsCopy[origin].slopeTo(pointsCopy[last])) == 0) {
                    last++;
                }
                if (last - first >= 3 && pointsCopy[origin].compareTo(pointsCopy[first]) < 0) {
                    found.add(new LineSegment(pointsCopy[origin], pointsCopy[last]));
                }
                first = last + 1;
            }
        }

    }



    // the number of line segments
    public           int numberOfSegments() {
        return found.size();

    }

    // the line segments
    public LineSegment[] segments() {
        return found.toArray(new LineSegment[found.size()]);

    }


    private void checkDuplicated(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i+1]) == 0) {
                throw new IllegalArgumentException("Duplicated Entries!");
            }
        }
    }

    private void checkNull(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("Null points in the input.");
            }
        }
    }

    public static void main(String[] args) {

        // read the n points from a file
//        In in = new In(args[0]);
//        int n = in.readInt();
//        Point[] points = new Point[n];
//        for (int i = 0; i < n; i++) {
//            int x = in.readInt();
//            int y = in.readInt();
//            points[i] = new Point(x, y);
//        }

        // draw the points
//        StdDraw.enableDoubleBuffering();
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//        for (Point p : points) {
//            p.draw();
//        }
//        StdDraw.show();

        Point o1 = new Point(10000, 0);
        Point o2 = new Point(0, 10000);
        Point o3 = new Point(3000,7000);
        Point o4 = new Point(7000, 3000);
        Point o5 = new Point(20000, 21000);
        Point o6 = new Point(3000, 4000);
        Point o7 = new Point(14000, 15000);
        Point o8 = new Point(6000, 7000);

        Point[] points = new Point[8];
        points[0] = o1;
        points[1] = o2;
        points[2] = o3;
        points[3] = o4;
        points[4] = o5;
        points[5] = o6;
        points[6] = o7;
        points[7] = o8;

        Point[] pointsCopy = Arrays.copyOf(points, points.length);

        for (int i = 0; i < points.length; i++) {
            System.out.println(pointsCopy[i].toString());
        }

//        Arrays.sort(pointsCopy);

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(pointsCopy);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
//            segment.draw();
        }
//        StdDraw.show();
    }
}