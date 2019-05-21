import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FinalFour implements LevelInformation {
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
        velocities.add(new Velocity(2, -2));
        velocities.add(new Velocity(-2, -2));
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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new FinalFourBackground(new Point(150, 450), new Point(600, 350));
    }

    @Override
    public List<Block> blocks() {
        int y = 100;
        int width = 50;
        List blocks = new ArrayList<>();
        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 15; i++) {
                blocks.add(new Block(new Point(25 + (i * width), y + (j * 20)), width, 20, colors[j]));
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
