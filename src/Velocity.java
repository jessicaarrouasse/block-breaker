/**
 * Velocity class represents the angle and the speed as a delta x and y.
 *
 * @version 1.0
 *
 * @author Jessica Arrouasse 328786348
 * username: anidjaj
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Velocity onstructor.
     *
     * @param dx delta x of this velocity
     * @param dy delta y of this velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Create a velocity using the angle and speed.
     *
     * @param angle of the velocity
     * @param speed of the velocity
     * @return the new instance
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dy = -Math.cos(Math.toRadians(angle)) * speed;
        double dx = Math.sin(Math.toRadians(angle)) * speed;
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

    /**
     * @return the speed
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }
}
