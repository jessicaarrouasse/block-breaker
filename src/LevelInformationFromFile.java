import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

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

    public LevelInformationFromFile(List<Velocity> velocities, int paddleSpeed, int paddleWidth, String levelName, String background, int blocksStartX, int blocksStartY, int rowHeight, String blockDefinitionsFilename, List<String> lineBlocks, int numBlocks) {
        this.velocities = velocities;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.levelName = levelName;
        this.background = background;
        this.blocksStartX = blocksStartX;
        this.blocksStartY = blocksStartY;
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
        Reader file;
        int currentX = this.blocksStartX;
        int currentY = this.blocksStartY;
        try {
            file = new FileReader(this.blockDefinitionsFilename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        BlocksDefinitionReader bdr = new BlocksDefinitionReader();
        BlocksFromSymbolsFactory factory = bdr.fromReader(file);

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
