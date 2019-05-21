import biuoop.DrawSurface;

import java.awt.Color;

public class FinalFourBackground implements Sprite {

    private Point center1;
    private Point center2;

    public FinalFourBackground(Point center1, Point center2) {
        this.center1 = center1;
        this.center2 = center2;
    }

    private void drawCloud(DrawSurface d, Point center) {
        int x = (int)center.getX();
        int y = (int)center.getY();

        d.setColor(Color.WHITE);
        for (int i = 0; i < 10 ; i++) {
            d.drawLine(x - (i * 10), y, x - (i * 20), y + 600);
        }

        d.setColor(Color.GRAY);
        d.fillCircle(x - 1, y + 13, 20);
        d.fillCircle(x - 20, y - 6, 25);
        d.setColor(Color.DARK_GRAY);

        d.fillCircle(x - 40, y +5, 35);
        d.setColor(Color.WHITE);
        d.fillCircle(x - 60, y - 7, 33);
        d.fillCircle(x - 80, y , 30);


    }

    @Override
    public void drawOn(DrawSurface d) {

        // Draw the background
        d.setColor(Color.cyan);
        d.fillRectangle(0, 20, d.getWidth(), d.getHeight());

        drawCloud(d, center1);
        drawCloud(d, center2);
//        for (int i = -200; i < 700; i+=15) {
//            d.drawLine(x , y, x + i , y + 80);
//        }
    }

    @Override
    public void timePassed() {

    }
}
