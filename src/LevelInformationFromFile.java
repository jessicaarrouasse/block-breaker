import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level information from file.
 */
public class LevelInformationFromFile implements LevelInformation {

    private List<Velocity> velocities;
    private int paddleSpeed;
    private int paddleWidth;
    private int blocksStartX;
    private int blocksStartY;
    private int rowHeight;
    private String levelName;
    private String background;
    private String blockDefinitionsFilename;
    private List<String> lineBlocks;
    private int numBlocks;

    /**
     * Instantiates a new Level information from file.
     *
     * @param velocities               the velocities
     * @param details                  the details
     * @param startPoint               the start point
     * @param rowHeight                the row height
     * @param blockDefinitionsFilename the block definitions filename
     * @param lineBlocks               the line blocks
     * @param numBlocks                the num blocks
     */
    public LevelInformationFromFile(List<Velocity> velocities,
                                    LevelDetails details,
                                    Point startPoint,
                                    int rowHeight,
                                    String blockDefinitionsFilename,
                                    List<String> lineBlocks,
                                    int numBlocks) {
        this.velocities = velocities;
        this.paddleSpeed = details.getPaddleSpeed();
        this.paddleWidth = details.getPaddleWidth();
        this.levelName = details.getLevelName();
        this.background = details.getBackground();
        this.blocksStartX = (int) startPoint.getX();
        this.blocksStartY = (int) startPoint.getY();
        this.rowHeight = rowHeight;
        this.blockDefinitionsFilename = blockDefinitionsFilename;
        this.lineBlocks = lineBlocks;
        this.numBlocks = numBlocks;
    }

    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return paddleWidth;
    }

    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        if (background.startsWith("color")) {
            return new ColorBackground(ColorsParser.colorFromString(background));
        } else if (background.startsWith("image")) {
            return new ImageBackground(background);
        }
        return  null;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int currentX = this.blocksStartX;
        int currentY = this.blocksStartY;

        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(this.blockDefinitionsFilename);

        BlocksFromSymbolsFactory factory = BlocksDefinitionReader.fromReader(new InputStreamReader(is));

        for (String line: this.lineBlocks) {
            String[] symbols = line.split("");
            for (String symbol: symbols) {
                if (factory.isSpaceSymbol(symbol)) {
                    currentX += factory.getSpaceWidth(symbol);
                } else if (factory.isBlockSymbol(symbol)) {
                    Block block = factory.getBlock(symbol, currentX, currentY);
                    blocks.add(block);
                    currentX += block.getCollisionRectangle().getWidth();
                }
            }
            currentY += this.rowHeight;
            currentX = this.blocksStartX;
        }

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numBlocks;
    }

}
