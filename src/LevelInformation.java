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
    List<Block> blocks();

    /**
     * Number of blocks to remove int.
     *
     * @return the int
     */
    int numberOfBlocksToRemove();
}
