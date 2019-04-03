/**
 * 
 */
package ass3;

import biuoop.DrawSurface;

/**
 * @author jessica
 *
 */
public interface Sprite {
	// draw the sprite to the screen
	void drawOn(DrawSurface d);
	// notify the sprite that time has passed
	void timePassed();
}
