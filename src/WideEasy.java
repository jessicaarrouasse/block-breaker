import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type WideEasy.
 */
public class WideEasy implements LevelInformation {
    private Color[] colors = {
            Color.RED,
            Color.RED,
            Color.ORANGE,
            Color.ORANGE,
            Color.YELLOW,
            Color.YELLOW,
            Color.GREEN,
            Color.GREEN,
            Color.GREEN,
            Color.BLUE,
            Color.BLUE,
            Color.PINK,
            Color.PINK,
            Color.CYAN,
            Color.CYAN
    };

    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(-45 - (i * 10), 6));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new WideEasyBackground(new Point(150, 175));
    }

    @Override
    public List<Block> blocks() {
        int y = 250;
        int width = 50;
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < colors.length; i++) {
            blocks.add(new Block(new Point(25 + (i * width), y), width, 20, colors[i]));
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
