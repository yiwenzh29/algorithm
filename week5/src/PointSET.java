import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import java.util.TreeSet;

public class PointSET {

    private TreeSet<Point2D> points;


    public PointSET() {
        points = new TreeSet<>();
    }

    // construct an empty set of points
    public boolean isEmpty() {
        return points.isEmpty();


    }                      // is the set empty?
    public int size() {
        return points.size();

    }                         // number of points in the set
    public void insert(Point2D p) {
        checkNullArg(p);
        if (!points.contains(p))
            points.add(p);

    }              // add the point to the set (if it is not already in the set)
    public boolean contains(Point2D p) {
        checkNullArg(p);
        return points.contains(p);


    }            // does the set contain point p?
    public void draw() {
        for (Point2D p : points) {
            p.draw();
        }

    }                        // draw all points to standard draw
    public Iterable<Point2D> range(RectHV rect) {
        checkNullArg(rect);

        Stack<Point2D> stackP = new Stack<Point2D>();

        for (Point2D p : points) {
            if (rect.contains(p))
                stackP.push(p);
        }

        return stackP;


    }            // all points that are inside the rectangle (or on the boundary)
    public Point2D nearest(Point2D p) {
        checkNullArg(p);
        if (size() == 0)
            return null;

        Point2D neighborP = null;

        for (Point2D point : points) {
            if (neighborP == null) {
                neighborP = point;
            }

            if ((point.distanceSquaredTo(p)) < neighborP.distanceSquaredTo(p)) {
                neighborP = point;
            }
        }

        return neighborP;


    }             // a nearest neighbor in the set to point p; null if the set is empty

    private void checkNullArg(Object obj) {
        if (obj == null)
            throw new IllegalArgumentException();
    }

    public static void main(String[] args) {

        PointSET ptest = new PointSET();
        System.out.println(ptest.isEmpty());

    }                 // unit testing of the methods (optional)
}
