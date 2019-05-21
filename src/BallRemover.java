/**
 * The type Block remover.
 * a BlockRemover is in charge of removing blocks from the gameLevel, as well as keeping count
 * of the number of blocks that remain.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param g the gameLevel from were we remove
     * @param removedBalls the removed ball counter
     */
    public BallRemover(GameLevel g, Counter removedBalls) {
        this.gameLevel = g;
        this.remainingBalls = removedBalls;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed
     * from the gameLevel. Remember to remove this listener from the block
     * that is being removed from the gameLevel.
     * @param beingHit the being hit object
     * @param hitter the hitter parameter is the Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        remainingBalls.decrease(1);
    }
}
