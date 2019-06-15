import java.awt.*;

import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


/**
 * Block class will design the block object with his position, color and hit points number.
 *
 * @author Jessica Arrouasse 328786348 username: anidjaj
 * @version 1.0
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle position;
    private int hitPoints;
    private BlockBackground background;
    private Color stroke;
    private List<HitListener> hitListeners;
    Map<Integer, BlockBackground> fillK;


    public Block(Point upperLeft, double width, double height, BlockBackground background, int hits, Map<Integer, BlockBackground> fillK, Color stroke) {
        this.position = new Rectangle(upperLeft, width, height);
        this.background = background;
        this.hitPoints = hits;
        this.fillK = fillK;
        this.hitListeners = new ArrayList<>();
        this.stroke = stroke;
    }

    public Block(Point upperLeft, double width, double height, BlockBackground background, int hits) {
        this(upperLeft, width, height, background, hits, new HashMap<>(), null);
    }

    /**
     * Second constructor with a default initial hit points number.
     *
     * @param upperLeft upperLeft point of the block
     * @param width     width of the block
     * @param height    height of the block
     * @param background     background of the block
     */
    public Block(Point upperLeft, double width, double height, BlockBackground background) {
        this(upperLeft, width, height, background, 1, new HashMap<>(), null);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.position;
    }

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

    @Override
    public void drawOn(DrawSurface surface) {
        int x = (int) this.position.getUpperLeft().getX();
        int y = (int) this.position.getUpperLeft().getY();
        int w = (int) this.position.getWidth();
        int h = (int) this.position.getHeight();
        // draw the block
        BlockBackground background = fillK.get(this.hitPoints);
        if (background != null) {
            background.drawOn(surface, x, y, w, h);
        } else {
            this.background.drawOn(surface, x, y, w, h);
        }

        // draw the border
        if (stroke != null) {
            surface.setColor(stroke);
            surface.drawRectangle(x, y, w, h);
        }
    }
    /**
     * Notify the sprite that time has passed.
     */
    public void timePassed() { }

    /**
     * Add this block to the gameLevel.
     *
     * @param gameLevel the gameLevel we add the block to
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
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
     * Remove from gameLevel.
     *
     * @param gameLevel the gameLevel from we want to remove the block
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
        this.removeHitListener(gameLevel.getBlockRemover());
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

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
