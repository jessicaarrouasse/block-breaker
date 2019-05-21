import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * @return the Number of balls.
     */
    int numberOfBalls();

    /**
     * @return the Initial velocity of each ball velocities list.
     */
    // Note that initialBallVelocities().size() == numberOfBalls()
    List<Velocity> initialBallVelocities();

    /**
     * @return the Paddle speed.
     */
    int paddleSpeed();

    /**
     * @return the Paddle width
     */
    int paddleWidth();

    /**
     * @return the Level name
     */
    // the level name will be displayed at the top of the screen.
    String levelName();

    /**
     * Gets background.
     *
     * @return the sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * @return the Blocks list
     */
    // The Blocks that make up this level, each block contains
    // its size, color and location.
    List<Block> blocks();

    /**
     * Number of blocks to remove int.
     *
     * @return the int
     */
    // Number of levels that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    int numberOfBlocksToRemove();
}
