import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
//    private LineSegment[] segments;
    private ArrayList<LineSegment> found = new ArrayList<>();


    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points){
        if (points == null)
            throw new IllegalArgumentException("The input is null.");

        checkNull(points);
        checkDuplicated(points);


        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);


        int N = pointsCopy.length;

        for (int p = 0; p < N - 3; p++) {
            for (int q = p+1; q < N - 2; q++) {
                double slopepq = pointsCopy[p].slopeTo(pointsCopy[q]);
                for (int r = q+1; r < N - 1; r++) {
                    double slopepr = pointsCopy[p].slopeTo(pointsCopy[r]);
                    for (int s = r+1; s < N; s++) {
                        double slopeps = pointsCopy[p].slopeTo(pointsCopy[s]);
                        if (slopepq == slopepr && slopepq == slopeps) {
                            found.add(new LineSegment(pointsCopy[p], pointsCopy[s]));
                        }
                    }
                }
            }
        }



    }

    // the number of line segments
    public int numberOfSegments() {

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
        points[5] = o6;points[6] = o7;points[7] = o8;

        Point[] pointsCopy = Arrays.copyOf(points, points.length);

        for (int i = 0; i < points.length; i++) {
            System.out.println(pointsCopy[i].toString());
        }

        Arrays.sort(pointsCopy);






        // draw the points
//        StdDraw.enableDoubleBuffering();
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//        for (Point p : points) {
//            p.draw();
//        }
//        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(pointsCopy);


        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);

//            segment.draw();
        }
//        StdDraw.show();
    }

}
