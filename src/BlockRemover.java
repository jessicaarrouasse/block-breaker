/**
 * The type Block remover.
 * a BlockRemover is in charge of removing blocks from the gameLevel, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param g         the gameLevel from were we remove
     * @param removedBlocks the removed blocks counter
     */
    public BlockRemover(GameLevel g, Counter removedBlocks) {
        this.gameLevel = g;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoint() <= 1) {
            beingHit.removeFromGame(gameLevel);
            remainingBlocks.decrease(1);
        }
    }
}
