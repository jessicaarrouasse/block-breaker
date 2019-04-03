/**
 * 
 */
package ass3;

import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author jessica
 *
 */
public class Paddle implements Sprite, Collidable {
	 private KeyboardSensor keyboard;
	 private Rectangle position;
	 private Point bottomRight;
	 private double[] zones = {20, 40, 60, 80};
	/**
	 * 
	 */
	public Paddle(KeyboardSensor keyboard, double cols, double rows) {
		this.keyboard = keyboard;
		this.position = new Rectangle(new Point((cols / 2) - 50,rows - 40), 100, 20);
		this.bottomRight = new Point(cols,rows);
	}
	
	public void moveLeft() {
		Point upperLeft = this.position.getUpperLeft();
		if (upperLeft.getX() >= 0) {
			this.position.getUpperLeft().setX(this.position.getUpperLeft().getX() - 10);
		}
	}
	
	public void moveRight() {
		Point upperLeft = this.position.getUpperLeft();
		if (upperLeft.getX() + 100 <= bottomRight.getX()) {
			this.position.getUpperLeft().setX(this.position.getUpperLeft().getX() + 10);
		}
	}
	
	// Collidable
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
			if (collisionPoint.getX() < xUpper + this.zones[0]) {
				return Velocity.fromAngleAndSpeed(-150, 5);
			} else if (collisionPoint.getX() < xUpper + this.zones[1]) {
				return Velocity.fromAngleAndSpeed(-120, 5);
			} else if (collisionPoint.getX() < xUpper + this.zones[2]) {
				return Velocity.fromAngleAndSpeed(-90, 5);
			}  else if (collisionPoint.getX() < xUpper + this.zones[3]) {
				return Velocity.fromAngleAndSpeed(-60, 5);
			} else {
				return Velocity.fromAngleAndSpeed(-30, 5);
			}
		}
	}

	//Sprite
	@Override
	public void drawOn(DrawSurface surface) {
		surface.setColor(Color.GRAY);
        surface.fillRectangle((int)this.position.getUpperLeft().getX(), 
      		  (int)this.position.getUpperLeft().getY(), 
      		  (int)this.position.getWidth(), 
      		  (int)this.position.getHeight());
	}

	@Override
	public void timePassed() {
		if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
			this.moveLeft();
		} else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
			this.moveRight();
		}
	}
	

	// Add this paddle to the game.
	public void addToGame(Game game) {
		game.addSprite(this);
		game.addCollidable(this);
	}
}
