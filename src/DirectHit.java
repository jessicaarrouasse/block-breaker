import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List velocities = new ArrayList<>();
        velocities.add(new Velocity(0, -2));
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
        return new DirectHitBackground(new Point(410, 160));
    }

    @Override
    public List<Block> blocks() {
        List blocks = new ArrayList<>();
        blocks.add(new Block(new Point(400, 150), 20, 20, Color.RED));
        return blocks;    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
