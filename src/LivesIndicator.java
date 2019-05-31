import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type LivesIndicator.of the player.
 */
public class LivesIndicator implements Sprite {
    private GameFlow gameFlow;

    /**
     * The constructor.
     * @param gameFlow the gameFlow that the player plays
     */
    public LivesIndicator(GameFlow gameFlow) {
        this.gameFlow = gameFlow;
    }

    @Override
    public void drawOn(DrawSurface d) {
        String score = "Lives: " + this.gameFlow.getCounterLives().getValue();
        d.setColor(Color.BLACK);
        d.drawText(10, 15, score, 16);
    }

    /**
     * Notify the sprite that time has passed.
     */
    public void timePassed() {

    }
}

