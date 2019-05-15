import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * SpriteCollection will contains the sprites list.
 *
 * @author Jessica Arrouasse 328786348 username: anidjaj
 * @version 1.0
 */
public class SpriteCollection {

    private List<Sprite> sprites = new ArrayList<>();

    /**
     * Add a sprite to the list.
     *
     * @param s sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {

        // Make a copy of the sprite before iterating over them.
        List<Sprite> tmp = new ArrayList<>(this.sprites);

        for (Sprite s : tmp) {
            s.timePassed();
        }
    }

    /**
     * Call drawOn(d) on all sprites.
     *
     * @param d surface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}
