import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Color background.
 */
public class ColorBackground implements Sprite {
    private Color color;

    /**
     * Instantiates a new Color background.
     *
     * @param color the color
     */
    public ColorBackground(Color color) {
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle(0, 20, d.getWidth(), d.getHeight());
    }

    @Override
    public void timePassed() {

    }
}
