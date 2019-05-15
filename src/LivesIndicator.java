import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type LivesIndicator.of the player.
 */
public class LivesIndicator implements Sprite {
    private Game game;
    private Rectangle position;

    /**
     * The constructor.
     * @param game the game that the player plays
     */
    public LivesIndicator(Game game) {
        this.game = game;
        this.position = new Rectangle(new Point(0, 0), 100, 20);
    }

    /**
     * draw the lives remaining on the screen.
     * @param d the surface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        String score = "Lives: ";
        d.setColor(Color.WHITE);
        d.fillRectangle((int) position.getUpperLeft().getX(),
            (int) position.getUpperLeft().getY(), 100, 20);
        d.setColor(Color.BLACK);
        d.drawText(10, 15, score, 16);
        d.setColor(Color.GREEN);
        for (int i = 0; i < this.game.getCounterLives().getValue() ; i++) {
            d.fillCircle(70 + (i * 15), 10, 5);
        }
    }

    /**
     * Notify the sprite that time has passed.
     */
    public void timePassed() {

    }
}

