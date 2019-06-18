import biuoop.DrawSurface;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

/**
 * The type Image background.
 */
public class ImageBackground implements Sprite {

    private Image img;

    /**
     * Instantiates a new Image background.
     *
     * @param src the src
     */
    public ImageBackground(String src) {
        String imgPath = src.split("\\(")[1].split("\\)")[0];
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(imgPath);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 20, img);
    }

    @Override
    public void timePassed() {

    }
}
