import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Wide easy background.
 */
public class WideEasyBackground implements Sprite {

    private Point center;

    /**
     * Instantiates a new Wide easy background.
     *
     * @param center the center of the sun
     */
    public WideEasyBackground(Point center) {
        this.center = center;
    }

    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) center.getX();
        int y = (int) center.getY();

        // Draw the background
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 20, d.getWidth(), d.getHeight());

        d.setColor(Color.decode("#EFE8B0"));
        for (int i = -200; i < 700; i += 15) {
            d.drawLine(x , y, x + i , y + 80);
        }
        d.fillCircle(x, y, 65);
        d.setColor(Color.decode("#ECD849"));
        d.fillCircle(x, y, 55);
        d.setColor(Color.decode("#FFE218"));
        d.fillCircle(x, y, 45);
    }

    @Override
    public void timePassed() {

    }
}
