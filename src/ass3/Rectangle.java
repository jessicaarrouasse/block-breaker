package ass3;

import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle class contains the upper-left point of the rectangle, his wide and his height.
 *
 * @version 1.0
 *
 * @author Jessica Arrouasse 328786348
 * username: anidjaj
 */
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Rectangle constructor.
     *
     * @param upperLeft upper-left point of the rectangle
     * @param width width of the rectangle
     * @param height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
       this.upperLeft = upperLeft;
       this.width = width;
       this.height = height;
    }

    /**
     * Return a (possibly empty) list of intersection points with the line.
     *
     * @param line the line to check
     * @return the list of intersections
     */
    public List<Point> intersectionPoints(Line line) {
        double x = this.upperLeft.getX();
        double y = this.upperLeft.getY();

        // create the intersections list
        List<Point> intersections = new ArrayList<Point>();

        // create the four side lines to check
        Line[] sides = {
            new Line(x, y, x + this.width, y),
            new Line(x, y + this.height, x + this.width, y + this.height),
            new Line(x, y, x, y + height),
            new Line(x + this.width, y, x + this.width, y + this.height)
        };

        // add the intersection point of each line if there is one
        for (Line side : sides) {
           if (line.isIntersecting(side)) {
               intersections.add(line.intersectionWith(side));
           }
        }

        return intersections;
    }

    /**
     * Accessor to the upperLeft.
     *
     * @return the upperLeft
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Accessor to the width.
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Accessor to the height.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Find if the collision point is on a vertical or horizontal side of the rectangle.
     *
     * @param collisionPoint the point to verify
     * @return true if the point is on a vertical side, false otherwise
     */
    public boolean isVerticalCollision(Point collisionPoint) {
        double xUpper = this.getUpperLeft().getX();
        double epsilon = 0.001;

        if ((collisionPoint.getX() < xUpper + epsilon
             && collisionPoint.getX() > xUpper - epsilon)
            || (collisionPoint.getX() < xUpper + this.width + epsilon
                && collisionPoint.getX() > xUpper + this.width - epsilon)) {
            return true;
        }
        return false;
    }


    /**
     * Find if the collision point is on a vertical or horizontal side of the rectangle.
     *
     * @param collisionPoint the point to verify
     * @return true if the point is on a horizontal side, false otherwise
     */
    public boolean isHorizontalCollision(Point collisionPoint) {
        double yUpper = this.getUpperLeft().getY();
        double epsilon = 0.001;

        if ((collisionPoint.getY() < yUpper + epsilon
            && collisionPoint.getY() > yUpper - epsilon)
            || (collisionPoint.getY() < yUpper + this.height + epsilon
                && collisionPoint.getY() > yUpper + this.height - epsilon)) {
            return true;
        }
        return false;
    }


    /**
     * compare 2 rectangles if there are the same.
     *
     * @param other a rectangle to compare with the instance rectangle
     * @return true if the rectangles are equal, false otherwise
     */
    public boolean equals(Rectangle other) {
        double epsilon = 0.001;
        return Math.abs(this.width - other.width) < epsilon
                && Math.abs(this.height - other.height) < epsilon
                && this.upperLeft.equals(other.upperLeft);
    }

    /**
     * Verify if a point is into the rectangle.
     *
     * @param p the point to check
     * @return true if the point is in the rectangles, false otherwise
     */
    public boolean isInside(Point p) {
        double epsilon = 0.001;
        return p.getX() > this.upperLeft.getX() + epsilon
                && p.getX() < this.upperLeft.getX() + this.width - epsilon
                && p.getY() > this.upperLeft.getY() + epsilon
                && p.getY() < this.upperLeft.getY() + this.height - epsilon;
    }
}
