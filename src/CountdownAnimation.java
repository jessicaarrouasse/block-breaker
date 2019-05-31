import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

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
     * The CountdownAnimation will display the given gameScreen,
     * for numOfSeconds seconds, and on top of them it will show
     * a countdown from countFrom back to 1, where each number will
     * appear on the screen for (numOfSeconds / countFrom) seconds, before
     * it is replaced with the next one.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
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
        d.setColor(Color.RED);
        d.drawText((d.getWidth() / 2) - 20,
                    d.getHeight() / 2,
                    counter.getValue() > 0 ? Integer.toString(counter.getValue()) : "Go", 50);
        sleeper.sleepFor(((long) this.numOfSeconds / ((long) this.countFrom) + 1) * 1000);
        this.counter.decrease(1);
    }

    @Override
    public boolean shouldStop() {
        return this.counter.getValue() == -2;
    }
}
