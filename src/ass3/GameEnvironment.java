/**
 * 
 */
package ass3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jessica
 *
 */
public class GameEnvironment {

	/**
	 * 
	 */
	private List<Collidable> collidables = new ArrayList<Collidable>();
	
	// add the given collidable to the environment.
	   public void addCollidable(Collidable c) {
		   collidables.add(c);
	   }

	   // Assume an object moving from line.start() to line.end().
	   // If this object will not collide with any of the collidables
	   // in this collection, return null. Else, return the information
	   // about the closest collision that is going to occur.
	   public CollisionInfo getClosestCollision(Line trajectory) {
		   List<CollisionInfo> collisions = new ArrayList<CollisionInfo>();

		   double minDist = trajectory.length();

		   CollisionInfo closest = null;
		   
		   for (Collidable collisionObject : collidables) {
			   Point collisionPoint = trajectory.closestIntersectionToStartOfLine(collisionObject.getCollisionRectangle());
			   if (collisionPoint != null) {
				   collisions.add(new CollisionInfo(collisionPoint, collisionObject));
			   }
		   }

		    if (collisions.isEmpty()) {
		    	return null;
		    }
		   
			for (CollisionInfo c : collisions) {
				if (trajectory.start().distance(c.collisionPoint()) < minDist) {
					closest = c;
					minDist = trajectory.start().distance(c.collisionPoint());
				}
			}
			return closest;
	   }
	   
	   public List<Collidable> getCollidables() {
		   return collidables;
	   }
}
