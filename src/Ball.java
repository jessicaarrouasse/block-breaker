import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Ball class will design the ball object.
 *
 * @version 1.1
 *
 * @author Jessica Arrouasse 328786348
 * username: anidjaj
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Ball constructor, set default velocity and limits.
     *
     * @param center point of this ball
     * @param r the radius of this ball
     * @param color of this ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * constructor.
     *
     * @param x point's center of this ball
     * @param y point's center of this ball
     * @param r radius of this ball
     * @param color of this ball
     */
    public Ball(double x, double y, int r, Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * accessor.
     *
     * @return x point of the center of this ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * accessor.
     *
     * @return access to the y point of the center of this ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * accessor.
     *
     * @return radius of this ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * accessor.
     *
     * @return access to the color of this ball
     */
    public Color getColor() {
        return this.color;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * @param v the velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * @param ge the gameEnvironment of the ball
     */
    public void setGameEnvironment(GameEnvironment ge) {
        this.gameEnvironment = ge;
    }

    /**
     * @param dx delta x
     * @param dy delta y
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * access to the velocity of the ball.
     *
     * @return velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Move the center of the ball to his next position.
     */
    public void moveOneStep() {

        Point end = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(this.center, end);

        // get the closest collision with a collidable on the trajectory.
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);


        if (collisionInfo != null) {
            // on collision, set the ball in the collision point and change his velocity
            this.center = collisionInfo.collisionPoint();
            //to do ball hitter
            this.setVelocity(collisionInfo.collisionObject().
            hit(this, collisionInfo.collisionPoint(), this.getVelocity()));
        } else {
            this.center = end;
        }
    }

    /**
     * Is used every time the defined time passed.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Add this ball to the gameLevel.
     *
     * @param gameLevel the gameLevel we add the ball to
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * Remove from gameLevel.
     *
     * @param gameLevel the gameLevel from we want to remove the ball
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }


}