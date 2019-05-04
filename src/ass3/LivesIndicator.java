package ass3;

import biuoop.DrawSurface;

import java.awt.*;

public class LivesIndicator implements Sprite{
    private Game game;
    private Rectangle position;

    public LivesIndicator(Game game) {
        this.game = game;
        this.position = new Rectangle(new Point(0,20), game.getGuiWidth(), 20);
    }


    public void drawOn(DrawSurface d){
        String score = "Lives: " + this.game.getCounterLives().getValue();
        d.setColor(Color.red);
        d.fillRectangle((int) position.getUpperLeft().getX(), (int) position.getUpperLeft().getY(), game.getGuiWidth(), 20);
        d.setColor(Color.BLACK);
        d.drawText(game.getGuiWidth() / 2, 35, score, 16);

    }

    public void timePassed(){

    }
}

