package ass3;

/**
 * The type Block remover.
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param g         the game from were we remove
     * @param removedBlocks the removed blocks counter
     */
    public BlockRemover(Game g, Counter removedBlocks) {
        this.game = g;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game..
     *
     * @param beingHit  the being hit block
     * @param hitter the hitter ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoint() <= 1) {
            beingHit.removeFromGame(game);
            remainingBlocks.decrease(1);
        }
    }
}
