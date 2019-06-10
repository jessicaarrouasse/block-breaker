import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class ImageBackground implements Sprite {

    private String src;

    public ImageBackground(String src) {
        this.src = src.split("\\(")[1].split("\\)")[0];
    }

    @Override
    public void drawOn(DrawSurface d) {

        Image img = null;
        try {
            img = ImageIO.read(new File(src));
        } catch (IOException e) {
            System.out.println(e);
        }

        d.drawImage(0, 20, img);
    }

    @Override
    public void timePassed() {

    }
}
