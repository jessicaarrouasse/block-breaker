import biuoop.DrawSurface;

import java.awt.*;

public class ColorBackground implements Sprite {
    private Color color;

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
