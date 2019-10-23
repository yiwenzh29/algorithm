import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] segments;
    private ArrayList<LineSegment> found;


    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points){
        if (points == null)
            throw new IllegalArgumentException("The input is null.");

        checkNull(points);
        checkDuplicated(points);

        Point[] pointsCopy = points.clone();

        int N = points.length;

        for (int p = 0; p < N - 3; p++) {
            for (int q = p+1; q < N - 2; q++) {
                double slopepq = pointsCopy[p].slopeTo(points[q]);
                for (int r = q+1; r < N - 1; r++) {
                    double slopepr = pointsCopy[p].slopeTo(points[r]);
                    for (int s = r+1; s < N; s++) {
                        double slopeps = pointsCopy[p].slopeTo(points[s]);
                        if (slopepq == slopepr && slopepq == slopeps) {
                            found.add(new LineSegment(pointsCopy[p], pointsCopy[s]));
                        }
                    }
                }
            }
        }

        segments = found.toArray(new LineSegment[found.size()]);


    }

    // the number of line segments
    public int numberOfSegments() {

        return segments.length;

    }

    // the line segments
    public LineSegment[] segments() {

        return segments;
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
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
