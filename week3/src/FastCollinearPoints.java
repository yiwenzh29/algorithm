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

        checkNull(points);
        checkDuplicated(points);

        int N = points.length;
        Point[] pointsCopy = Arrays.copyOf(points, N);

        Arrays.sort(pointsCopy);




        for (int i = 0; i < N - 3; i++) {
            Point p = pointsCopy[i];

            Point[] candidates = new Point[pointsCopy.length -i-1];
            int dist = i;
            for (int j = 0; j < candidates.length; j++)
                candidates[j] = pointsCopy[j+1+dist];

            Arrays.sort(candidates, p.slopeOrder());

            int first = 0;


            while (first < candidates.length ) {
                double SLOPE = p.slopeTo(candidates[first]);
                int second = first;

                while (second < candidates.length && p.slopeTo(candidates[second]) == SLOPE) {
                    second++;
                }

                if (second - first >= 3 && p.slopeTo(candidates[second-1]) == SLOPE) {


                        found.add(new LineSegment(p, candidates[second-1]));
                }

                first = second;
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

        Arrays.sort(pointsCopy);

        for (int j = 0; j < points.length; j++) {
            System.out.println(pointsCopy[j].toString());
        }
        System.out.println();


        for (int i = 0; i < pointsCopy.length - 6; i++) {
            Point p = pointsCopy[i];
            for (int k = i+1; k < pointsCopy.length; k++) {
                System.out.println(p.slopeTo(pointsCopy[k]));
            }

            Point[] candidates = new Point[pointsCopy.length -i-1];
            int dist = i;
            for (int j = 0; j < candidates.length; j++)
                candidates[j] = pointsCopy[j+1+dist];

            System.out.println();
//            System.out.println(candidates[6]);

            for (int q = 0; q < candidates.length; q++) {
                System.out.println(candidates[q].toString());
            }
            System.out.println();
//
//            Arrays.sort(candidates, p.slopeOrder());
//            for (int j = 0; j < candidates.length; j++) {
//                System.out.println(candidates[j].toString());
//            }
//            System.out.println();
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