/**
 * 
 */
package ass3;

import biuoop.DrawSurface;

/**
 * @author jessica
 *
 */
public class Block implements Collidable {
	
	private Rectangle position;

	/**
	 * 
	 */
	public Block(Rectangle position) {
		this.position = position;
	}
	
	public Block(Point upperLeft, double width, double height) {
		this.position = new Rectangle (upperLeft, width, height);
	}

	@Override
	public Rectangle getCollisionRectangle() {
		return this.position;
	}

	@Override
	public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
		double dx = currentVelocity.getDx();
		double dy = currentVelocity.getDy();
		double xUpper = this.position.getUpperLeft().getX();
		double width = this.position.getWidth();
		
		if (collisionPoint.getX() == xUpper || collisionPoint.getX() == xUpper + width) {
			return new Velocity (-dx,dy); 
		} else {
			return new Velocity (dx,-dy); 
		}
	}
	
    /**
     * draw the block on the given DrawSurface.
     *
     * @param surface the draw's surface
     */
    public void drawOn(DrawSurface surface) {
        surface.fillRectangle((int)this.position.getUpperLeft().getX(), 
			        		  (int)this.position.getUpperLeft().getY(), 
			        		  (int)this.position.getWidth(), 
			        		  (int)this.position.getHeight());
    }
}
