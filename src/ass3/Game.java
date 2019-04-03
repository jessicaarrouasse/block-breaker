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
	private static final int COL = 800;
	private static final int ROW = 400;
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
		Color[] colors = {Color.CYAN, Color.RED, Color.GRAY, Color.GREEN, Color.ORANGE, Color.YELLOW};
	    Ball[] balls = {new Ball(330, 339, 5, java.awt.Color.BLACK), new Ball(230, 339, 5, java.awt.Color.BLACK)};
	    
		new Block(new Point(0,0), COL, 20, Color.RED, 0).addToGame(this);
		new Block(new Point(0,0), 20, ROW, Color.RED, 0).addToGame(this);
		new Block(new Point(COL - 20,0), 20, ROW, Color.RED, 0).addToGame(this);
		new Block(new Point(0, ROW - 20), COL, 20, Color.RED, 0).addToGame(this);
	    
	    for (Ball ball : balls) {
	    	ball.setVelocity(Velocity.fromAngleAndSpeed(-45, 10));
		    ball.setGameEnvironment(environment);
		    ball.addToGame(this);
	    }

	    Paddle paddle = new Paddle(this.gui.getKeyboardSensor(), COL, ROW);
	    paddle.addToGame(this);
	    
	    for (int i = 0; i < 7; i++) {
		    Block block = new Block(new Point(COL - (i * 105), 100),100, 20, colors[0], 2);
		    block.addToGame(this);
	    }
	    
	    for (int j = 6; j > 2; j--) {
		    for (int i = 0; i < j; i++) {
			    Block block = new Block(new Point(COL - (i * 105), 100 + ((7 - j) * 25)),100, 20, colors[7 - j]);
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
