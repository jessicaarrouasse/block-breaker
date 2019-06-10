import java.util.ArrayList;
import java.util.List;

public class LevelInformationFromFile implements LevelInformation {

    private List<Velocity> velocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private String background;

    public LevelInformationFromFile(List<Velocity> velocities, int paddleSpeed, int paddleWidth, String levelName, String background) {
        this.velocities = velocities;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.levelName = levelName;
        this.background = background;
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
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

}
