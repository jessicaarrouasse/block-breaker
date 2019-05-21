import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WideEasy implements LevelInformation {
    private Color[] colors = {
            Color.RED,
            Color.ORANGE,
            Color.YELLOW,
            Color.GREEN,
            Color.BLUE,
            Color.PINK,
            Color.CYAN
    };

    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List velocities = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            velocities.add(Velocity.fromAngleAndSpeed(-40 - (i * 10), 5));
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
        List blocks = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            blocks.add(new Block(new Point(25 + (i * width), y), width, 20, colors[i % colors.length]));
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
