import biuoop.DrawSurface;

import java.awt.Color;

public class DirectHitBackground implements Sprite {

    private Point center;

    public DirectHitBackground(Point center) {
        this.center = center;
    }

    @Override
    public void drawOn(DrawSurface d) {
        int x = (int)center.getX();
        int y = (int)center.getY();

        // Draw the background
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 20, d.getWidth(), d.getHeight());

        d.setColor(Color.BLUE);
        d.drawCircle(x, y, 20);
        d.drawCircle(x, y, 30);
        d.drawCircle(x, y, 40);
        d.drawLine(x - 75, y, x + 75, y);
        d.drawLine(x, y - 75, x, y + 75);
    }

    @Override
    public void timePassed() {

    }
}
