package ass3;


public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor.
     *
     * @param dx delta x of this velocity
     * @param dy delta y of this velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * create a velocity using the angle and speed.
     *
     * @param angle of the velocity
     * @param speed of the velocity
     * @return the new instance
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(angle * Math.PI) * speed;
        double dy = Math.sin(angle * Math.PI) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * @return the delta x of this velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the delta y of this velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * @param p a point with position (x,y)
     * @return a new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}