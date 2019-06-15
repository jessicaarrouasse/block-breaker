import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BlockBackground {
    private String image;
    private Color color;
    private String xxx;

    public BlockBackground(String background) {
        xxx = background;
        if (background.startsWith("color")) {
            color = ColorsParser.colorFromString(background);
        } else if (background.startsWith("image")) {
            this.image = background.split("\\(")[1].split("\\)")[0];

        }
    }

    public BlockBackground(Color color) {
        this.color = color;
    }


    public void drawOn(DrawSurface d, int x, int y, int w, int h) {
        if (this.image != null) {
            Image img = null;
            try {
                img = ImageIO.read(new File(this.image));
            } catch (IOException e) {
                System.out.println(e);
            }

            d.drawImage(x, y, img);
        } else {
            d.setColor(this.color);
            d.fillRectangle(x, y, w, h);
        }
    }

}
