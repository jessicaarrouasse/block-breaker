/**
 * CollisionInfo class will contains the object and the point of the collision.
 *
 * @version 1.0
 *
 * @author Jessica Arrouasse 328786348
 * username: anidjaj
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * CollisionInfo constructor.
     *
     * @param collisionPoint collision point of the object of collision
     * @param collisionObject the object of collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
       this.collisionPoint = collisionPoint;
       this.collisionObject = collisionObject;
    }

    /**
     * Accessor to the collision point.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
       return this.collisionPoint;
    }

    /**
     * Accessor to the collision object.
     *
     * @return the collision object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
