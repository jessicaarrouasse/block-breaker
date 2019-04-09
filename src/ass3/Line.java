package ass3;

import java.util.List;

/**
 * Line class contains a start point, end point and middle point.
 *
 * @version 1.1
 *
 * @author Jessica Arrouasse 328786348
 * username: anidjaj
 */

public class Line {

    private Point start;
    private Point end;
    private Point middle;

    /**
     * constructor keep middle points x and y.
     *
     * @param start point of this line
     * @param end points of this line
     */
    public Line(Point start, Point end) {

        this.start = start;
        this.end = end;

        double middleX = (this.start.getX() + this.end.getX()) / 2;
        double middleY = (this.start.getY() + this.end.getY()) / 2;

        this.middle = new Point(middleX, middleY);
    }

    /**
     * Line constructor.
     *
     * @param x1 x of the start point of  this line
     * @param y1 y of the start ponts of this line
     * @param x2 x of the end ponts of this line
     * @param y2 y of the end ponts of this line
     */
    public Line(double x1, double y1, double x2, double y2) {

        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * @return the length of this line
     */
    public double length() {

        return this.start.distance(end);
    }

    /**
     * @return the middle point of the line
     */
    public Point middle() {

        return this.middle;

    }

    /**
     * access to the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {

        return this.start;
    }

    /**
     * access to the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {

        return this.end;
    }

    /**
     * check if this line intersect with other.
     *
     * @param other the line to check
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;
    }

    /**
     * found the slope of this line with help of formula y1-y2/x1-x2.
     *
     * @return the slope of this line or the max Double if it's a vertical line
     */
    public double getSlope() {
        double div = this.start.getX() - this.end.getX();
        if (div == 0) {
            return Double.MAX_VALUE;
        }
        return (this.start.getY() - this.end.getY()) / div;
    }

    /**
     * found the intercept of this line.
     *
     * @return the intercept of this lineor the max Double if it's a vertical line
     */
    public double getIntercept() {
        double slope = this.getSlope();
        if (slope == Double.MAX_VALUE) {
            return Double.MAX_VALUE;
        }
        return this.start.getY() - (slope * this.start.getX());
    }

    /**
     * Find the intersection point.
     *
     * @param other the line to check
     * @return the intersection point if the lines intersect and null otherwise.
     */
    public Point intersectionWith(Line other) {
        double a1 = this.getSlope();
        double a2 = other.getSlope();

        // for parallel and nested lines
        if (a1 == a2) {
            //if one of the lines is actually a point
            if (this.start().equals(this.end()) && this.start().isInLimits(other)) {
                return this.start();
            }
            if (other.start().equals(other.end()) && other.start().isInLimits(this)) {
                return other.start();
            }
            return null;
        }

        double b1 = this.getIntercept();
        double b2 = other.getIntercept();

        double x;
        double y;

        if (a1 == Double.MAX_VALUE) {
            //if 'this' is a vertical line
            x = this.start().getX();
            y = (a2 * x) + b2;
        } else if (a2 == Double.MAX_VALUE) {
            //if 'this' is a vertical line
            x = other.start().getX();
            y = (a1 * x) + b1;
        } else {
            x = (b2 - b1) / (a1 - a2);
            y = (a1 * x) + b1;
        }

        Point intersection = new Point(x, y);

        // check if the intersection is in the limits of the lines
        if (intersection.isInLimits(this) && intersection.isInLimits(other)) {
            return intersection;
        } else {
            return null;
        }
    }

    /**
     * Check if this line and other are the same.
     *
     * @param other the line to check
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));
    }

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    /**
     * Find the closest intersection from the start of the line with the rectangle.
     *
     * @param rect the rectangle to check
     * @return the closest intersection point or null
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // list of intersection points with the rectangle
        List<Point> intersections = rect.intersectionPoints(this);
        double minDist = this.length();
        Point minP = null;

        // if no intersection
        if (intersections.isEmpty()) {
            return null;
        } else {
            for (Point intersec : intersections) {
                // find the closest intersection
                if (this.start.distance(intersec) <= minDist) {
                    minP = intersec;
                    minDist = this.start.distance(intersec);
                }
            }
        }

        return minP;
    }

}
