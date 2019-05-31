import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Green 3 background.
 */
public class Green3Background implements Sprite {

    private Point center;

    /**
     * Instantiates a new Green 3 background.
     *
     * @param center the center of the antenna
     */
    public Green3Background(Point center) {
        this.center = center;
    }

    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) center.getX();
        int y = (int) center.getY();

        // Draw the background
        d.setColor(Color.decode("0x2b8215"));
        d.fillRectangle(0, 20, d.getWidth(), d.getHeight());


        d.setColor(Color.darkGray);
        d.fillRectangle(x - 7, y, 14, 200);

        d.fillRectangle(x - 14, y + 200, 28, 50);


        d.setColor(Color.BLACK);
        d.fillRectangle(x - 50, y + 250, 100, 200);
        d.setColor(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                d.fillRectangle(x - 45 + (i * 19) , y + 255 + (j * 30), 13 , 22);
            }
        }


        d.setColor(Color.ORANGE);
        d.fillCircle(x, y, 15);
        d.setColor(Color.RED);
        d.fillCircle(x, y, 10);
        d.setColor(Color.WHITE);
        d.fillCircle(x, y, 5);
    }

    @Override
    public void timePassed() {

    }
}
