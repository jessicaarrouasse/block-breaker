package ass3;

/**
 * Collidable interface.
 *
 * @version 1.0
 *
 * @author Jessica Arrouasse 328786348
 * username: anidjaj
 */
public interface Collidable {
    /**
     * Accessor to the position of the collision area.
     * @return the position of the collidable object
     */
    Rectangle getCollisionRectangle();

    /**
     * Is called when an object enter in collision with the collidable.
     *
     * @param collisionPoint the point of collision on the collidable
     * @param currentVelocity the velocity of the object who enters in collision with the collidable
     *
     * @return the new velocity of the object who enters in collision after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
