import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Direct hit background.
 */
public class DirectHitBackground implements Sprite {

    private Point center;

    /**
     * Instantiates a new Direct hit background.
     *
     * @param center the center of the target
     */
    public DirectHitBackground(Point center) {
        this.center = center;
    }

    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) center.getX();
        int y = (int) center.getY();

        // Draw the background
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 20, d.getWidth(), d.getHeight());

        d.setColor(Color.BLUE);
        d.drawCircle(x, y, 40);
        d.drawCircle(x, y, 70);
        d.drawCircle(x, y, 100);
        d.drawLine(x - 130, y, x + 130, y);
        d.drawLine(x, y - 130 , x, y + 130);
    }

    @Override
    public void timePassed() {

    }
}
