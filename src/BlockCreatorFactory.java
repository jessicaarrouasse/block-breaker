import java.awt.*;
import java.util.Map;

public class BlockCreatorFactory implements BlockCreator {

    private int width;
    private int height;
    private BlockBackground fill;
    private Color stroke;
    private int hitPoints;
    private Map<Integer, BlockBackground> fillK;

    public BlockCreatorFactory(int width, int height, BlockBackground fill, int hitPoints, Map<Integer, BlockBackground> fillK, Color stroke) {
        this.width = width;
        this.height = height;
        this.fill = fill;
        this.hitPoints = hitPoints;
        this.fillK = fillK;
        this.stroke = stroke;
    }

    @Override
    public Block create(int xpos, int ypos) {
        return new Block(new Point((double) xpos, (double) ypos), width, height, fill, hitPoints, fillK, stroke);
    }
}
