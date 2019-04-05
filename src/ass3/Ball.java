package ass3;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Ball class will design the ball object.
 *
 * @version 1.0
 *
 * @author Jessica Arrouasse 328786348
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

    /**
     * draw the ball on the given DrawSurface with the color of this ball.
     *
     * @param surface the draw's surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
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
     * @param gameEnvironment the gameEnvironment of the ball
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
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
        
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);

        if (collisionInfo != null) {
        	this.center = collisionInfo.collisionPoint();
        	this.setVelocity(collisionInfo.collisionObject().hit(collisionInfo.collisionPoint(), this.getVelocity()));
        } else {
            // update the center point
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }
    
	@Override
	public void timePassed() {
		this.moveOneStep();
	}
	
	public void addToGame(Game game) {
		game.addSprite(this);
	}
}