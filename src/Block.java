import java.awt.Color;
import biuoop.DrawSurface;
import java.util.List;
import java.util.ArrayList;


/**
 * Block class will design the block object with his position, color and hit points number.
 *
 * @author Jessica Arrouasse 328786348 username: anidjaj
 * @version 1.0
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle position;
    private int hitPoints;
    private Color color;
    private List<HitListener> hitListeners;


    /**
     * Block constructor, set the block fields.
     *
     * @param upperLeft upperLeft point of the block
     * @param width     width of the block
     * @param height    height of the block
     * @param color     color of the block
     * @param hits      initial hit points number
     */
    public Block(Point upperLeft, double width, double height, Color color, int hits) {
        this.position = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitPoints = hits;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Second constructor with a default initial hit points number.
     *
     * @param upperLeft upperLeft point of the block
     * @param width     width of the block
     * @param height    height of the block
     * @param color     color of the block
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this(upperLeft, width, height, color, 1);
    }

    /**
     * Accessor to the block's position.
     * @return the position of the collidable object
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.position;
    }

    /**
     * Is called when an object enter in collision with the block.
     * @param collisionPoint the point of collision on the collidable
     * @param currentVelocity the velocity of the object who enters in collision with the collidable
     * @param hitter the ball that hit
     * @return the new velocity of the object who enters in collision after the collision     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Point upperLeft = this.position.getUpperLeft();
        double width = this.position.getWidth();
        double height = this.position.getHeight();

        this.notifyHit(hitter);

        // decrement hitPoints if positive
        if (hitPoints > 1) {
            this.hitPoints--;
        }

        // corners check
        if (collisionPoint.equals(upperLeft) || collisionPoint.equals(
            new Point(upperLeft.getX() + width, upperLeft.getY()))) {
            return dy > 0 ? new Velocity(dx, -dy) : new Velocity(-dx, dy);
        } else if (collisionPoint.equals(new Point(upperLeft.getX(), upperLeft.getY() + height))
            || collisionPoint.equals(new Point(upperLeft.getX() + width, upperLeft.getY() + height))) {
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
     * @param surface the surface to draw on
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
        // draw the border
        surface.setColor(Color.BLACK);
        surface.drawRectangle(x, y, w, h);
    }
    /**
     * Notify the sprite that time has passed.
     */
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
        return Integer.toString(this.hitPoints);
    }

    /**
     * Get hit points.
     *
     * @return the hitpoints
     */
    public int getHitPoint() {
        return hitPoints;
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

    /**
     * Remove from game.
     *
     * @param game the game from we want to remove the block
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
        this.removeHitListener(game.getBlockRemover());
    }

    /**
     * Add hit listener to hit events.
     * @param hl the listener to add
     */
    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * Remove hit listener from the list of listeners to hit events.
     * @param hl the listener to remove
     */
    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * notify all the listener that the ball hit.
     * @param hitter the ball that hit
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

}
