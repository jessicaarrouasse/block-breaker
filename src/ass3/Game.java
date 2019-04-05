package ass3;


import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author jessica
 *
 */
public class Game {
	private static final int COL = 1200;
	private static final int ROW = 675;
	private static final int BLOCKS = 11;
	private static final int LEVELS = 6;
	private static final int MARGIN = 20;

	private SpriteCollection sprites;
	private GameEnvironment environment;
	private GUI gui;
	Sleeper sleeper;

	/**
	 * 
	 */
	public Game() {
		this.sprites = new SpriteCollection();
		this.environment = new GameEnvironment();
		this.gui = new GUI("Bouncing Ball Example", COL, ROW);
		this.sleeper = new Sleeper();
	}
	
	public void addCollidable(Collidable c) {
		environment.addCollidable(c);
	}
	
	public void addSprite(Sprite s) {
		sprites.addSprite(s);
	}
	   
	// Initialize a new game: create the Blocks and Ball (and Paddle)
	// and add them to the game.
	public void initialize() {
		Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN};
	    Ball[] balls = {new Ball(330, 339, 5, Color.WHITE), new Ball(230, 339, 5, Color.WHITE)};
	    
		new Block(new Point(0,0), COL, MARGIN, Color.GRAY, 0).addToGame(this);
		new Block(new Point(0,0), MARGIN, ROW, Color.GRAY, 0).addToGame(this);
		new Block(new Point(COL - MARGIN,0), MARGIN, ROW, Color.GRAY, 0).addToGame(this);
		new Block(new Point(0, ROW - MARGIN), COL, MARGIN, Color.GRAY, 0).addToGame(this);
	    
	    for (Ball ball : balls) {
	    	ball.setVelocity(Velocity.fromAngleAndSpeed(-45, 10));
		    ball.setGameEnvironment(environment);
		    ball.addToGame(this);
	    }

	    Paddle paddle = new Paddle(this.gui.getKeyboardSensor(), COL, ROW);
	    paddle.addToGame(this);
	    
	    for (int i = 1; i <= BLOCKS; i++) {
		    Block block = new Block(new Point(COL - (i * 75) - MARGIN, 100),75, 20, colors[0], 2);
		    block.addToGame(this);
	    }
	    
	    for (int j = BLOCKS - 1; j > BLOCKS - LEVELS; j--) {
		    for (int i = 1; i <= j; i++) {
			    Block block = new Block(new Point(COL - (i * 75) - MARGIN, 100 + ((BLOCKS - j) * 20)),75, 20, colors[BLOCKS - j]);
			    block.addToGame(this);
		    }
	    }
	}
	   
	// Run the game -- start the animation loop.hits
	public void run() {
	    int framesPerSecond = 10;
	    int millisecondsPerFrame = 1000 / framesPerSecond;
	    while (true) {
	       long startTime = System.currentTimeMillis(); // timing

	       DrawSurface d = gui.getDrawSurface();
	       d.setColor(Color.BLUE);
		   d.fillRectangle(0, 0, COL, ROW);

	       this.sprites.drawAllOn(d);
	       gui.show(d);
	       this.sprites.notifyAllTimePassed();
	       
	       // timing
	       long usedTime = System.currentTimeMillis() - startTime;
	       long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
	       if (milliSecondLeftToSleep > 0) {
	           sleeper.sleepFor(milliSecondLeftToSleep);
	       }
	    }
	}
}
