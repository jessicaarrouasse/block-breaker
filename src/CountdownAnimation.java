import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.*;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Counter counter;
    private Sleeper sleeper = new Sleeper();


    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */

    // The CountdownAnimation will display the given gameScreen,
    // for numOfSeconds seconds, and on top of them it will show
    // a countdown from countFrom back to 1, where each number will
    // appear on the screen for (numOfSeconds / countFrom) seconds, before
    // it is replaced with the next one.

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.counter = new Counter();
        this.counter.increase(countFrom);

    }
    @Override
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.setColor(Color.WHITE);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, counter.getValue() > 0 ? Integer.toString(counter.getValue()) : "Go", 50);
        this.counter.decrease(1);
        sleeper.sleepFor(((long)this.numOfSeconds / (long)this.countFrom) * 1000);
    }

    @Override
    public boolean shouldStop() {
        return this.counter.getValue() == -2;
    }
}
