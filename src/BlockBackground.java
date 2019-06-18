import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

/**
 * The type Block background.
 */
public class BlockBackground {
    private Image image;
    private Color color;

    /**
     * Instantiates a new Block background.
     *
     * @param background the background
     */
    public BlockBackground(String background) {
        if (background.startsWith("color")) {
            color = ColorsParser.colorFromString(background);
        } else if (background.startsWith("image")) {
            String imgPath = background.split("\\(")[1].split("\\)")[0];
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(imgPath);
            try {
                image = ImageIO.read(is);
            } catch (IOException e) {
                System.out.println(e);
            }

        }
    }

    /**
     * Instantiates a new Block background.
     *
     * @param color the color
     */
    public BlockBackground(Color color) {
        this.color = color;
    }

    /**
     * Instantiates a new Block background.
     *
     * @param bg the bg
     */
    public BlockBackground(BlockBackground bg) {
        this.image = bg.image;
        this.color = bg.color;
    }


    /**
     * Draw on.
     *
     * @param d the d
     * @param x the x
     * @param y the y
     * @param w the w
     * @param h the h
     */
    public void drawOn(DrawSurface d, int x, int y, int w, int h) {
        if (image != null) {
            d.drawImage(x, y, image);
        } else {
            d.setColor(this.color);
            d.fillRectangle(x, y, w, h);
        }
    }

}
