import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Green3 implements LevelInformation {

    private static final int COL = 800;
    private static final int BLOCKS = 11;
    private static final int LEVELS = 6;
    private static final int MARGIN = 25;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 20;
    private static final int FIRST_LINE_HEIGHT = 150;


    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List velocities = new ArrayList<>();
        velocities.add(new Velocity(2, -2));
        velocities.add(new Velocity(-2, -2));
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
        return "Grenn 3";
    }

    @Override
    public Sprite getBackground() {
        return new Green3Background(new Point(120, 175));
    }

    @Override
    public List<Block> blocks() {

        Color[] colors = {
                Color.GRAY,
                Color.RED,
                Color.YELLOW,
                Color.ORANGE,
                Color.PINK,
                Color.GREEN
        };

        List blocks = new ArrayList<>();

        for (int j = BLOCKS - 1; j > BLOCKS - LEVELS; j--) {
            for (int i = 1; i <= j; i++) {
                blocks.add(new Block(
                        new Point(COL - (i * BLOCK_WIDTH) - MARGIN,
                                FIRST_LINE_HEIGHT + ((BLOCKS - j) * BLOCK_HEIGHT)),
                        BLOCK_WIDTH,
                        BLOCK_HEIGHT,
                        colors[BLOCKS - j]
                ));
            }
        }


        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
