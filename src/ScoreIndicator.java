import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type ScoreIndicator of the player.
 */
public class ScoreIndicator implements Sprite {
    private GameFlow gameFlow;
    private Rectangle position;

    /**
     * The constructor.
     * @param gameFlow the gameLevel that the player plays
     */
    public ScoreIndicator(GameFlow gameFlow) {
        this.gameFlow = gameFlow;
        this.position = new Rectangle(new Point(0, 0), gameFlow.getGuiWidth(), 20);
    }

    @Override
    public void drawOn(DrawSurface d) {
        String score = "Score: " + this.gameFlow.getCounterScore().getValue();
        d.setColor(Color.WHITE);
        d.fillRectangle((int) position.getUpperLeft().getX(),
            (int) position.getUpperLeft().getY(), gameFlow.getGuiWidth(), 20);
        d.setColor(Color.BLACK);
        d.drawText(gameFlow.getGuiWidth() / 2, 15, score, 16);

    }

    /**
     * Notify the sprite that time has passed.
     */
    public void timePassed() {

    }
}
