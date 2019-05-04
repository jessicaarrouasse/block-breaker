package ass3;

public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoint() > 1){
            this.currentScore.increase(5);
        }
        else{
          this.currentScore.increase(10);
        }
    }
}