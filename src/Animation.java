import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * print a new frame.
     *
     * @param d the suface
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return true if the game should stop
     */
    boolean shouldStop();
}