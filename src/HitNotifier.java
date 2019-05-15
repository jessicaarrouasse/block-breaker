/**
 * The interface Hit notifier.
 */

public interface HitNotifier {
    /**
     * Add hit listener to hit events.
     *
     * @param hl the listener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hit listener from the list of listeners to hit events.
     *
     * @param hl the listener to remove
     */
    void removeHitListener(HitListener hl);

}
