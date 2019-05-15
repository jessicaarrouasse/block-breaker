/**
 * The type Block remover.
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param g the game from were we remove
     * @param removedBalls the removed ball counter
     */
    public BallRemover(Game g, Counter removedBalls) {
        this.game = g;
        this.remainingBalls = removedBalls;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     * @param beingHit the being hit object
     * @param hitter the hitter parameter is the Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}
