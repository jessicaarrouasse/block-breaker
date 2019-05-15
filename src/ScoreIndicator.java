import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type ScoreIndicator of the player.
 */
public class ScoreIndicator implements Sprite {
    private Game game;
    private Rectangle position;

    /**
     * The constructor.
     * @param game the game that the player plays
     */
    public ScoreIndicator(Game game) {
        this.game = game;
        this.position = new Rectangle(new Point(0, 0), game.getGuiWidth(), 20);
    }

    /**
     * draw the score on the screen.
     * @param d the surface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        String score = "Score: " + this.game.getCounterScore().getValue();
        d.setColor(Color.WHITE);
        d.fillRectangle((int) position.getUpperLeft().getX(),
            (int) position.getUpperLeft().getY(), game.getGuiWidth(), 20);
        d.setColor(Color.BLACK);
        d.drawText(game.getGuiWidth() / 2, 15, score, 16);

    }

    /**
     * Notify the sprite that time has passed.
     */
    public void timePassed() {

    }
}
