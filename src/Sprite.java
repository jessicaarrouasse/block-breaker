import biuoop.DrawSurface;

/**
 * Sprite interface.
 *
 * @version 1.0
 *
 * @author Jessica Arrouasse 328786348
 * username: anidjaj
 */
public interface Sprite {
    /**
     * Draw the sprite on the screen.
     *
     * @param d the surface to draw on
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}
