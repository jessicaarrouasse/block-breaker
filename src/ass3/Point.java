package ass3;

/**
 * Point class.
 *
 * @version 1.0
 *
 * @author Jessica Arrouasse 328786348
 */

public class Point {

    private double x;
    private double y;

    /**
     * Point constructor.
     *
     * @param y of a point
     * @param x  of a point
     */
    public Point(double x, double y) {

        this.x = x;
        this.y = y;
    }

    /**
     * distance between 2 ponts.
     *
     * @param other a point
     * @return distance from this point to the other point
     */
    public double distance(Point other) {

        return Math.sqrt(((this.x - other.getX()) * (this.x - other.getX()))
               + ((this.y - other.getY()) * (this.y - other.getY())));
    }

    /**
     * compare 2 points if there are the same.
     *
     * @param other a point to compare with the instance point
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        double epsilon = 0.001;
        return Math.abs(this.x - other.getX()) < epsilon && Math.abs(this.y - other.getY()) < epsilon;
    }

    /**
     * give access to x point.
     *
     * @return x value of this point
     */
    public double getX() {

        return this.x;
    }

    /**
     * give access to y point.
     *
     * @return y value of this point
     */
    public double getY() {

        return this.y;
    }

    public void setX(double x) {
    	this.x = x;
    }

    public void setY(double y) {
    	this.y = y;
    }

    /**
     * check if this point is between the limit points of the line.
     *
     * @param line to check
     * @return true if the point is in the line, false otherwise
     */
    public boolean isInLimits(Line line) {
        double epsilon = 0.001;
        if (this.x > Math.max(line.start().getX(), line.end().getX()) + epsilon
                || this.x < Math.min(line.start().getX(), line.end().getX()) - epsilon) {
            return false;
        }

        if (this.y > Math.max(line.start().getY(), line.end().getY()) + epsilon
                || this.y < Math.min(line.start().getY(), line.end().getY()) - epsilon) {
            return false;
        }

        return true;

    }
    
    public String toString() {
    	return "(" + this.x + "," + this.y + ")";
    }
}
