import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;

public class KdTree {

    private static boolean HOR = true;
    private static boolean VER = false;

    private class Node {
        private Node left;
        private Node right;
        private RectHV rect;
        private Point2D point;

        private int size;
        private boolean direction;

        public Node(Point2D point, boolean direction, RectHV rect) {
            this.point = point;
            this.direction = direction;
            this.size = 1;
            this.rect = rect;
        }

        public int compare(double p1, double p2) {
            if (p1 > p2)
                return 1;
            else if (p1 < p2)
                return -1;
            else
                return 0;
        }

        public int compareTo(Point2D p) {
            if (this.direction == HOR) {
                int i = compare(this.point.x(), p.x());
                if (i != 0) {
                    return i;
                }
                return compare(this.point.y(), p.y());
            }

            else {
                int i = compare(this.point.y(), p.y());
                if (i != 0) {
                    return i;
                }
                return compare(this.point.x(), p.x());
            }
        }

        public int compareTo(RectHV r) {
            if (this.direction == HOR) {
                if (this.point.x() > r.xmax())
                    return 1;
                if (this.point.x() < r.xmin())
                    return -1;
            }
            else {
                if (this.point.y() > r.ymax())
                    return 1;
                if (this.point.y() < r.ymin())
                    return -1;
            }

            return 0;
        }

        public RectHV newRect(Point2D p) {
            double xmin = this.rect.xmin();
            double xmax = this.rect.xmax();
            double ymin = this.rect.ymin();
            double ymax = this.rect.ymax();
            if (this.compareTo(p) > 0) {
                if (this.direction == HOR)
                    xmax = this.point.x();
                else
                    ymax = this.point.y();
            }
            else {
                if (this.direction == HOR)
                    xmin = this.point.x();
                else
                    ymin = this.point.y();
                }
            return new RectHV(xmin,ymin,xmax,ymax);
        }


    }

    private Node root = null;
    private int size = 0;
    private Point2D minPoint = null;
    private double minDistance = 0.0;

    public KdTree() {

    }

    public boolean isEmpty() {
//        return root == null;
        return size == 0;
    }

    public int size() {
        return getSize(root);
    }

    public void insert(Point2D p) {
        root = insert(root, p, HOR, null);
    }

    private int getSize(Node n) {
        if (n == null)
            return 0;
        return n.size;
    }

    private Node insert(Node n, Point2D p, boolean d, Node parent) {
        checkNull(p);

        if (n == null) {
            RectHV r;
            if (parent == null) {
                r = new RectHV(0, 0, 1, 1);
            }
            else
                r = parent.newRect(p);
        }

        if (n.compareTo(p) > 0) {
            n.left = insert(n.left, p, !d, n);
        }
        else if (n.compareTo(p) < 0) {
            n.right = insert(n.right, p, !d, n);
        }
        else {
            n.point = p;
        }

        n.size = 1 + getSize(n.left) + getSize(n.right);

        return n;
    }

    public boolean contains(Point2D p) {
        checkNull(p);
        return search(root, p) != null;
    }

    private Node search(Node n, Point2D p) {
        if (n == null)
            return null;

        if (n.compareTo(p) > 0)
            return search(n.left, p);
        else if (n.compareTo(p) <0)
            return search(n.right, p);

        return n;
    }

    public Iterable<Point2D> range(RectHV r) {
        checkNull(r);

        Stack<Point2D> stackP = new Stack<Point2D>();

        rangeRect(root, r, stackP);

        return stackP;
    }

    private void rangeRect(Node n, RectHV r, Stack<Point2D> s) {
        if (n == null)
            return;

        if (n.compareTo(r) > 0)
            rangeRect(n.left, r, s);
        else if (n.compareTo(r) < 0)
            rangeRect(n.right, r, s);
        else {
            if (r.contains(n.point))
                s.push(n.point);
            rangeRect(n.left, r, s);
            rangeRect(n.right, r, s);
        }
    }

    public Point2D nearest(Point2D p) {
        minPoint = root.point;
        minDistance = minPoint.distanceSquaredTo(p);

        searchNearest(root,p);
        return minPoint;
    }

    private void searchNearest(Node n, Point2D p) {
        checkNull(p);
        double d = n.point.distanceSquaredTo(p);


        if (minDistance > d) {
            minPoint = n.point;
            minDistance = d;
        }

        if (n.left != null && n.right != null) {
            double left = n.left.rect.distanceSquaredTo(p);
            double right = n.right.rect.distanceSquaredTo(p);

            if (left < right) {
                searchNearest(n.left, p);
                if (right < minDistance)
                    searchNearest(n.right, p);
            } else {
                searchNearest(n.right, p);
                if (left < minDistance)
                    searchNearest(n.left, p);
            }
            return;
        }

        if (n.left != null) {
            if (n.left.rect.distanceSquaredTo(p) < minDistance)
                searchNearest(n.left, p);
        }
        if (n.right != null) {
            if (n.right.rect.distanceSquaredTo(p) < minDistance)
                searchNearest(n.right, p);
        }
    }

    private void checkNull(Object obj) {
        if (obj == null)
            throw new IllegalArgumentException();

    }

}
