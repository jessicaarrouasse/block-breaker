import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Final four background.
 */
public class FinalFourBackground implements Sprite {

    private Point center1;
    private Point center2;

    /**
     * Instantiates a new Final four background.
     *
     * @param center1 the center of the first cloud
     * @param center2 the center of the second cloud
     */
    public FinalFourBackground(Point center1, Point center2) {
        this.center1 = center1;
        this.center2 = center2;
    }

    /**
     * Draw a cloud on the background.
     *
     * @param d the surface
     * @param center the center of the cloud
     */
    private void drawCloud(DrawSurface d, Point center) {
        int x = (int) center.getX();
        int y = (int) center.getY();

        d.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            d.drawLine(x - (i * 10), y, x - (i * 20), y + 600);
        }

        d.setColor(Color.GRAY);
        d.fillCircle(x - 1, y + 13, 20);
        d.fillCircle(x - 20, y - 6, 25);
        d.setColor(Color.DARK_GRAY);
        d.fillCircle(x - 40, y + 5, 35);
        d.setColor(Color.decode("0xAAAAAA"));
        d.fillCircle(x - 60, y - 7, 33);
        d.fillCircle(x - 80, y , 30);
    }

    @Override
    public void drawOn(DrawSurface d) {

        // Draw the background
        d.setColor(Color.decode("0x1688d0"));
        d.fillRectangle(0, 20, d.getWidth(), d.getHeight());

        drawCloud(d, center1);
        drawCloud(d, center2);
    }

    @Override
    public void timePassed() {

    }
}
