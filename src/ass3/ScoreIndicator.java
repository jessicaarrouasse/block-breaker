package ass3;

import biuoop.DrawSurface;

import java.awt.*;

public class ScoreIndicator implements Sprite {
    private Game game;
    private Rectangle position;

    public ScoreIndicator(Game game) {
        this.game = game;
        this.position = new Rectangle(new Point(0,0), game.getGuiWidth(), 20);
    }


    public void drawOn(DrawSurface d){
        String score = "Score: " + this.game.getCounterScore().getValue();
        d.setColor(Color.WHITE);
        d.fillRectangle((int) position.getUpperLeft().getX(), (int) position.getUpperLeft().getY(),game.getGuiWidth(), 20);
        d.setColor(Color.BLACK);
        d.drawText(game.getGuiWidth() / 2, 15, score, 16);

    }

    public void timePassed(){

    }
}
