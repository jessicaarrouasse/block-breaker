import java.awt.Color;
import java.util.Map;

/**
 * The type Block creator factory.
 */
public class BlockCreatorFactory implements BlockCreator {

    private int width;
    private int height;
    private BlockBackground fill;
    private Color stroke;
    private int hitPoints;
    private Map<Integer, BlockBackground> fillK;

    /**
     * Instantiates a new Block creator factory.
     *
     * @param width     the width
     * @param height    the height
     * @param fill      the fill
     * @param hitPoints the hit points
     * @param fillK     the fill k
     * @param stroke    the stroke
     */
    public BlockCreatorFactory(int width,
                               int height,
                               BlockBackground fill,
                               int hitPoints,
                               Map<Integer, BlockBackground> fillK,
                               Color stroke) {
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
