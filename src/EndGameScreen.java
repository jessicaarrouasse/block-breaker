import biuoop.DrawSurface;

/**
 * The type Pause screen.
 */
public class EndGameScreen implements Animation {
    private boolean lose;
    private Counter score;

    /**
     * Instantiates a new Pause screen.
     *
     * @param  score final score
     * @param  lose true if lose
     */
    public EndGameScreen(Counter score, boolean lose) {
        this.lose = lose;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        String message = lose ? "Game Over." : "You Win!";
        d.drawText(10, d.getHeight() / 2, message + " Your score is " + this.score.getValue(), 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}