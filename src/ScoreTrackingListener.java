/**
 * The type ScoreTrackingListener.
 * a ScoreTrackingListener is in charge of updating the score of the player
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Instantiates a new ScoreTrackingListener.
     *
     * @param scoreCounter the current's score of the player
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoint() > 1) {
            this.currentScore.increase(5);
        } else {
          this.currentScore.increase(10);
        }
    }
}