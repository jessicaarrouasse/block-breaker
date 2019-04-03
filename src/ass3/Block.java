/**
 * 
 */
package ass3;

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * @author jessica
 *
 */
public class Block implements Collidable, Sprite {
	
	private Rectangle position;
	private int hitPoints;
	private Color color;

	/**
	 * 
	 */

	public Block(Point upperLeft, double width, double height, Color color, int hits) {
		this.position = new Rectangle (upperLeft, width, height);
		this.color = color;
		this.hitPoints = hits;
	}
	
	public Block(Point upperLeft, double width, double height, Color color) {
		this(upperLeft, width, height, color, 1);
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
		
		if (hitPoints > 0) {
			this.hitPoints--;
		}
		
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
	@Override
    public void drawOn(DrawSurface surface) {
		int x = (int) this.position.getUpperLeft().getX();
		int y = (int)this.position.getUpperLeft().getY();
		int w = (int)this.position.getWidth();
		int h = (int)this.position.getHeight();
        surface.setColor(this.color);
		surface.fillRectangle(x, y, w, h);
        surface.setColor(Color.WHITE);																																																																									
		surface.drawText(x + (w / 2), y + (h / 2), this.hitPointsToString(), 16);

    }

	@Override
	public void timePassed() {
		
	}
	
	public void addToGame(Game game) {
		game.addSprite(this);
		game.addCollidable(this);
	}
	
	public String hitPointsToString() {
		if (this.hitPoints > 0) {
			return Integer.toString(this.hitPoints);
		}
		return "X";
	}

}
