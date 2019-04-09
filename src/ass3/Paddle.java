package ass3;

import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Paddle class contains game constants and all the sprites and collidables.
 *
 * @version 1.0
 *
 * @author Jessica Arrouasse 328786348
 * username: anidjaj
 */
public class Paddle implements Sprite, Collidable {

     private KeyboardSensor keyboard;
     private Rectangle position;
     private Point bottomRight;
     private double ballSpeed;
     private double[] zones = {20, 40, 60, 80};

    /**
     * Paddle constructor set the position of the paddle and initialize the keyboard object.
     *
     * @param keyboard keyboard object to catch the arrows press
     * @param cols width of the screen
     * @param rows height of the screen
     */
    public Paddle(KeyboardSensor keyboard, double cols, double rows, double ballSpeed) {
        this.keyboard = keyboard;
        this.position = new Rectangle(new Point((cols / 2) - 40, rows - 40), 100, 1);
        this.bottomRight = new Point(cols, rows);
        this.ballSpeed = ballSpeed;
    }

    /**
     * Move the paddle to the left.
     */
    public void moveLeft() {
        Point upperLeft = this.position.getUpperLeft();
        if (upperLeft.getX() > 20) {
            this.position.getUpperLeft().setX(this.position.getUpperLeft().getX() - 20);
        }
    }

    /**
     * Move the paddle to the right.
     */
    public void moveRight() {
        Point upperLeft = this.position.getUpperLeft();
        if (upperLeft.getX() + 100 < bottomRight.getX() - 20) {
            this.position.getUpperLeft().setX(this.position.getUpperLeft().getX() + 20);
        }
    }

    /**
     * Accessor to the position of the collision area.
     *
     * @return the position of the paddle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.position;
    }

    /**
     * Is called when an object enter in collision with the paddle.
     *
     * @param collisionPoint the point of collision on the paddle
     * @param currentVelocity the velocity of the object who enters in collision with the paddle
     *
     * @return the new velocity of the object who enters in collision after the collision
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double xUpper = this.position.getUpperLeft().getX();

        if (collisionPoint.getX() <= xUpper + this.zones[0]) {
            return Velocity.fromAngleAndSpeed(-150, this.ballSpeed);
        } else if (collisionPoint.getX() <= xUpper + this.zones[1]) {
            return Velocity.fromAngleAndSpeed(-120, this.ballSpeed);
        } else if (collisionPoint.getX() < xUpper + this.zones[2]) {
            return new Velocity(dx, -dy);
        }  else if (collisionPoint.getX() < xUpper + this.zones[3]) {
            return Velocity.fromAngleAndSpeed(-60, this.ballSpeed);
        } else {
            return Velocity.fromAngleAndSpeed(-30, this.ballSpeed);
        }

    }

    /**
     * Draw the paddle on the screen
     *
     * @param surface the surface to draw on
     */ @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.ORANGE);
        surface.fillRectangle((int) this.position.getUpperLeft().getX(),
              (int) this.position.getUpperLeft().getY(),
              (int) this.position.getWidth(),
              20);
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }


    /**
     * Add the paddle to the game.
     *
     * @param game the game we add the ball to
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
}
