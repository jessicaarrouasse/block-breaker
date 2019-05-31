import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Paddle class contains game constants and all the sprites and collidables.
 *
 * @author Jessica Arrouasse 328786348 username: anidjaj
 * @version 1.0
 */
public class Paddle implements Sprite, Collidable {

     private KeyboardSensor keyboard;
     private Rectangle position;
     private Point bottomRight;
     private double paddleSpeed;
     private double screenWidth;
     private int margin;
     private double[] zones;

    /**
     * Paddle constructor set the position of the paddle and initialize the keyboard object.
     *
     * @param keyboard    keyboard object to catch the arrows press
     * @param cols        width of the screen
     * @param rows        height of the screen
     * @param paddleSpeed the paddle speed
     * @param paddleWidth the paddle width
     * @param margin      the margin
     */
    public Paddle(KeyboardSensor keyboard, double cols, double rows, int paddleSpeed, int paddleWidth, int margin) {
        this.keyboard = keyboard;
        this.position = new Rectangle(new Point((cols / 2) - (paddleWidth / 2), rows - 40), paddleWidth, 1);
        this.bottomRight = new Point(cols, rows);
        this.paddleSpeed = paddleSpeed;
        this.screenWidth = cols;
        this.margin = margin;
        this.zones = new double[]{
                paddleWidth * 0.2,
                paddleWidth * 0.4,
                paddleWidth * 0.6,
                paddleWidth * 0.8,
        };
    }

    /**
     * Move the paddle to the left.
     */
    public void moveLeft() {
        Point upperLeft = this.position.getUpperLeft();
        if (upperLeft.getX() > paddleSpeed + this.margin) {
            this.position.getUpperLeft().setX(this.position.getUpperLeft().getX() - paddleSpeed);
        } else if (upperLeft.getX() > this.margin) {
            this.position.getUpperLeft().setX(this.margin);
        }
    }

    /**
     * Move the paddle to the right.
     */
    public void moveRight() {
        Point upperLeft = this.position.getUpperLeft();
        if (upperLeft.getX() + this.position.getWidth() < bottomRight.getX() - paddleSpeed - this.margin) {
            this.position.getUpperLeft().setX(this.position.getUpperLeft().getX() + paddleSpeed);
        } else if (upperLeft.getX() + this.position.getWidth() < bottomRight.getX() - this.margin) {
            this.position.getUpperLeft().setX(this.screenWidth - this.margin - this.position.getWidth());
        }
    }

    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.position;
    }

    /**
     * Hit velocity.
     *
     * @param hitter          the hitter
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double ballSpeed = currentVelocity.getSpeed();
        double xUpper = this.position.getUpperLeft().getX();

        //velocity accordind to the collision's point with the paddle
        if (collisionPoint.getX() <= xUpper + this.zones[0]) {
            return Velocity.fromAngleAndSpeed(-150, ballSpeed);
        } else if (collisionPoint.getX() <= xUpper + this.zones[1]) {
            return Velocity.fromAngleAndSpeed(-120, ballSpeed);
        } else if (collisionPoint.getX() < xUpper + this.zones[2]) {
            return new Velocity(dx, -dy);
        }  else if (collisionPoint.getX() < xUpper + this.zones[3]) {
            return Velocity.fromAngleAndSpeed(-60, ballSpeed);
        } else {
            return Velocity.fromAngleAndSpeed(-30, ballSpeed);
        }

    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.ORANGE);
        surface.fillRectangle((int) this.position.getUpperLeft().getX(),
                (int) this.position.getUpperLeft().getY(),
                (int) this.position.getWidth(),
                20);
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.position.getUpperLeft().getX(),
                (int) this.position.getUpperLeft().getY(),
                (int) this.position.getWidth(),
                20);
    }

    /**
     * Notify the sprite that time has passed.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }


    /**
     * Add the paddle to the gameLevel.
     *
     * @param gameLevel the gameLevel where we add the ball to
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * remove the paddle from the gameLevel.
     *
     * @param gameLevel the gameLevel where we remove the ball to
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }
}
