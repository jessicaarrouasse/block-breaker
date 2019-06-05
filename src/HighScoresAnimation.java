import biuoop.DrawSurface;

/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;

    /**
     * Instantiates a new High scores animation.
     *
     * @param scores the scores
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        int rank = 1;
        d.drawText(d.getWidth() / 2, 80, "HIGHSCORES", 32);

        for (ScoreInfo score: scores.getHighScores()) {
            String message = rank + " " + score.getName() + " " + score.getScore();
            d.drawText(d.getWidth() / 2, 100 + (20 * rank), message, 22);
            rank++;
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}