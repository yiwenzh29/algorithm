import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] segments;
    private ArrayList<LineSegment> found;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("The input is null.");

        checkDuplicated(points);
        checkDuplicated(points);

        int N = points.length;
        Point[] pointsCopy = points.clone();

        for (int i = 0; i < N; i++) {
            Point p = pointsCopy[i];
            Arrays.sort(pointsCopy, p.slopeOrder());

            for (int origin = 0, first = 1, last = 2; last < N - 1; last++) {
                while (last < N && pointsCopy[origin].slopeTo(pointsCopy[first]) == pointsCopy[origin].slopeTo(pointsCopy[last])) {
                    last++;
                }
                if (last - first >= 3) {
                    found.add(new LineSegment(pointsCopy[origin], pointsCopy[last]));
                }
                first = last + 1;
            }
        }





        segments = found.toArray(new LineSegment[found.size()]);

    }

    // the number of line segments
    public           int numberOfSegments() {
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
}