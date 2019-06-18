/**
 * The type Level details.
 */
public class LevelDetails {
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private String background;

    /**
     * Instantiates a new Level details.
     *
     * @param paddleSpeed the paddle speed
     * @param paddleWidth the paddle width
     * @param levelName   the level name
     * @param background  the background
     */
    public LevelDetails(int paddleSpeed, int paddleWidth, String levelName, String background) {
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.levelName = levelName;
        this.background = background;
    }

    /**
     * Gets paddle speed.
     *
     * @return the paddle speed
     */
    public int getPaddleSpeed() {
        return paddleSpeed;
    }

    /**
     * Gets paddle width.
     *
     * @return the paddle width
     */
    public int getPaddleWidth() {
        return paddleWidth;
    }

    /**
     * Gets level name.
     *
     * @return the level name
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * Gets background.
     *
     * @return the background
     */
    public String getBackground() {
        return background;
    }
}
