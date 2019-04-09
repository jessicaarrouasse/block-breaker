package ass3;

import java.awt.Color;
import biuoop.DrawSurface;

/**
 * Block class will design the block object with his position, color and hit points number.
 *
 * @version 1.0
 *
 * @author Jessica Arrouasse 328786348
 * username: anidjaj
 */
public class Block implements Collidable, Sprite {
    private Rectangle position;
    private int hitPoints;
    private Color color;

    /**
     * Block constructor, set the block fields.
     *
     * @param upperLeft upperLeft point of the block
     * @param width width of the block
     * @param height height of the block
     * @param color color of the block
     * @param hits initial hit points number
     */
    public Block(Point upperLeft, double width, double height, Color color, int hits) {
        this.position = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitPoints = hits;
    }

    /**
     * Second constructor with a default initial hit points number.
     *
     * @param upperLeft upperLeft point of the block
     * @param width width of the block
     * @param height height of the block
     * @param color color of the block
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this(upperLeft, width, height, color, 1);
    }

    /**
     * Accessor to the block's position.
     *
     * @return the position of the block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.position;
    }

    /**
     * Is called when an object enter in collision with the block.
     *
     * @param collisionPoint the point of collision on the block
     * @param currentVelocity the velocity of the object who enters in collision with the block
     *
     * @return the new velocity of the object who enters in collision after the collision
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Point upperLeft = this.position.getUpperLeft();
        double width = this.position.getWidth();
        double height = this.position.getHeight();

        // decrement hitPoints if positive
        if (hitPoints > 0) {
            this.hitPoints--;
        }

        // corners check
        if (collisionPoint.equals(upperLeft) || collisionPoint.equals(new Point(upperLeft.getX() + width, upperLeft.getY()))) {
            return dy > 0 ? new Velocity(dx, -dy) : new Velocity(-dx, dy);
        } else if (collisionPoint.equals(new Point(upperLeft.getX(), upperLeft.getY() + height)) || collisionPoint.equals(new Point(upperLeft.getX() + width, upperLeft.getY() + height))) {
            return dy < 0 ? new Velocity(dx, -dy) : new Velocity(-dx, dy);
        }

        // returns the new velocity depends if it's a vertical or horizontal collision
        if (this.position.isHorizontalCollision(collisionPoint)) {
            return new Velocity(dx, -dy);
        } else {
            return new Velocity(-dx, dy);
        }
    }

    /**
     * Draw the block on the given surface.
     *
     * @param surface the draw's surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        int x = (int) this.position.getUpperLeft().getX();
        int y = (int) this.position.getUpperLeft().getY();
        int w = (int) this.position.getWidth();
        int h = (int) this.position.getHeight();
        // draw the block
        surface.setColor(this.color);
        surface.fillRectangle(x, y, w, h);
        // draw the hitPoints number
        surface.setColor(Color.WHITE);
        surface.drawText(x + (w / 2), y + (h / 2), this.hitPointsToString(), 16);
        // draw the border
        surface.setColor(Color.BLACK);
        surface.drawRectangle(x, y, w, h);
    }

    @Override
    public void timePassed() { }

    /**
     * Add this block to the game.
     *
     * @param game the game we add the block to
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * Returns the string to show on the block.
     *
     * @return the hit points value or 'X' if 0
     */
    public String hitPointsToString() {
        if (this.hitPoints > 0) {
            return Integer.toString(this.hitPoints);
        }
        return "X";
    }

    /**
     * compare 2 blocks if there are the same.
     *
     * @param other a block to compare with the instance block
     * @return true if the blocks are equal, false otherwise
     */
    public boolean equals(Block other) {
        return this.position.equals(other.position);
    }

}
