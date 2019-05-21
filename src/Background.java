import biuoop.DrawSurface;

import java.awt.Color;

public class Background implements Sprite {
    private Color color;

    public Background(Color color) {
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {

        // Draw the background
        d.setColor(color);
        d.fillRectangle(0, 20, 800, 600);
    }

    @Override
    public void timePassed() {

    }
}
