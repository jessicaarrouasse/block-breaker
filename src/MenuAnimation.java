import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu<T> {
    private List<StatusInfo<T>> options = new ArrayList<>();
    private KeyboardSensor sensor;
    private T status;
    private Sprite background;

    /**
     * Instantiates a new Menu animation.
     *
     * @param sensor the sensor
     * @param background the background
     */
    public MenuAnimation(KeyboardSensor sensor, Sprite background) {
        this.sensor = sensor;
        this.background = background;
    }

    /**
     * Instantiates a new Menu animation with default background.
     *
     * @param sensor the sensor
     */
    public MenuAnimation(KeyboardSensor sensor) {
        this.sensor = sensor;
        this.background = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.ORANGE);
                d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
            }

            @Override
            public void timePassed() {
            }
        };
    }


    @Override
    public void addSelection(String key, String message, T returnVal) {
        options.add(new StatusInfo<>(key, message, returnVal));
    }

    @Override
    public T getStatus() {
        return status;
    }

    @Override
    public void addSubMenu(String key, String message, T returnVal) {
        options.add(new StatusInfo<>(key, message, returnVal));
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int optionNumber = 1;
        if (background != null) {
            background.drawOn(d);
        }
        d.setColor(Color.BLACK);
        d.drawText((d.getWidth() / 2) - 50, 80, "Menu", 32);

        for (StatusInfo<T> option: options) {
            String message = optionNumber + ". " + option.getMessage() + " (" + option.getKey() + ")";
            d.drawText((d.getWidth() / 2) - 150, 100 + (20 * optionNumber), message, 22);
            optionNumber++;
        }
    }

    @Override
    public boolean shouldStop() {
        for (StatusInfo<T> option: options) {
            if (this.sensor.isPressed(option.getKey())) {
                this.status = option.getReturnVal();
                return true;
            }
        }
        return false;
    }
}
