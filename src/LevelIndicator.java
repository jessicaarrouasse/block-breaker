import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type LivesIndicator.of the player.
 */
public class LevelIndicator implements Sprite {
    private String levelName;
    private Rectangle position;

    /**
     * Instantiates a new Level indicator.
     *
     * @param levelName the level name
     */
    public LevelIndicator(String levelName) {
        this.levelName = levelName;
        this.position = new Rectangle(new Point(0, 700), 100, 20);
    }

    @Override
    public void drawOn(DrawSurface d) {
        String level = "Level Name: " + this.levelName;

        d.setColor(Color.BLACK);
        d.drawText(600, 15, level, 16);
    }

    @Override
    public void timePassed() {

    }
}

