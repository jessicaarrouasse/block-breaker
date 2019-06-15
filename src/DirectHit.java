import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Direct hit.
 */
public class DirectHit implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0, -15));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new DirectHitBackground(new Point(400, 160));
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Point(390, 150), 20, 20, new BlockBackground(Color.RED)));
        return blocks;    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
