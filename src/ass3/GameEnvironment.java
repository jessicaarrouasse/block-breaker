package ass3;

import java.util.ArrayList;
import java.util.List;

/**
 * GameEnvironment class will contains the collidables list.
 *
 * @author Jessica Arrouasse 328786348 username: anidjaj
 * @version 1.0
 */
public class GameEnvironment {

    private List<Collidable> collidables = new ArrayList<Collidable>();

    /**
     * Add a collidable to the list.
     *
     * @param c collidable to add
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);

    }

    /**
     * Find the closest collision from the start of the trajectory with a collidable.
     *
     * @param trajectory the line we verify
     * @return the CollisionInfo object if there is a collision, null otherwise
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        List<CollisionInfo> collisions = new ArrayList<CollisionInfo>();
        double minDist = trajectory.length();
        CollisionInfo closest = null;

        // find the closest collision point for each collidable and set him to the collisions list
        for (Collidable collisionObject : collidables) {
           Point collisionPoint = trajectory.closestIntersectionToStartOfLine(
                                                    collisionObject.getCollisionRectangle());

           if (collisionPoint != null && !collisionPoint.equals(trajectory.start())) {
               // add the collisions points to the collisions list
               collisions.add(new CollisionInfo(collisionPoint, collisionObject));
           }
        }

        // find the closest collision point from the collisions list
        for (CollisionInfo c : collisions) {
            if (trajectory.start().distance(c.collisionPoint()) <= minDist) {
                closest = c;
                minDist = trajectory.start().distance(c.collisionPoint());
            }
        }

        // check the end of the trajectory is into a block
        if (collisions.isEmpty()) {
            for (Collidable collObj : collidables) {
                if (collObj.getCollisionRectangle().isInside(trajectory.end())) {
                    closest = new CollisionInfo(trajectory.start(), collObj);
                    break;
                }
            }
        }
        return closest;
    }

    /**
     * Accessor to the collidables list.
     *
     * @return the collidables list
     */
    public List<Collidable> getCollidables() {
           return collidables;
       }
}
