import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;


public class MenuAnimation<T> implements Menu<T> {
    private List<StatusInfo<T>> options = new ArrayList<>();
    private List<StatusInfo<Menu<T>>> subMenus = new ArrayList<>();
    private KeyboardSensor sensor;
    private AnimationRunner runner;
    private T status;

    public MenuAnimation(KeyboardSensor sensor, AnimationRunner runner) {
        this.sensor = sensor;
        this.runner = runner;
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
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        subMenus.add(new StatusInfo<>(key, message, subMenu));

        Task<T> runSubMenu = new Task<T>() {
            @Override
            public T run() {
                runner.run(subMenu);
                // wait for user selection
                Task<Void> task = (Task<Void>)subMenu.getStatus();
                task.run();
                return null;
            }
        };
        addSelection(key, message, (T)runSubMenu);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //todo add double click
        int optionNumber = 1;
        d.drawText(d.getWidth() / 2, 80, "Menu", 32);

        for (StatusInfo<T> status: options) {
            String message = optionNumber + ". " + status.getMessage() + " => press " + status.getKey();
            d.drawText(d.getWidth() / 2, 100 + (20 * optionNumber), message, 22);
            optionNumber++;
        }
//        for (StatusInfo<Menu<T>> status: subMenus) {
//            String message = optionNumber + ". " + status.getMessage() + " => press " + status.getKey();
//            d.drawText(d.getWidth() / 2, 100 + (20 * optionNumber), message, 22);
//            optionNumber++;
//        }
    }

    @Override
    public boolean shouldStop() {
        for (StatusInfo<T> status: options) {
            if (this.sensor.isPressed(status.getKey())) {
                this.status = status.getReturnVal();
                return true;
            }
        }
//        for (StatusInfo<Menu<T>> subMenu: subMenus) {
//            if (this.sensor.isPressed(subMenu.getKey())) {
//
//                return true;
//            }
//        }
        return false;
    }
}
