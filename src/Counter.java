/**
 * The type Counter.
 */
public class Counter {

    private int value = 0;

    /**
     * Increase.
     *
     * @param number the number to add to current count.
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * Decrease.
     *
     * @param number the number to substract from current count
     */
    public void decrease(int number) {
        this.value -= number;

    }

    /**
     * Gets value.
     *
     * @return the value the current count
     */
    public int getValue() {
        return this.value;

    }
}