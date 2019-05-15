import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Game class will contains game constants and all the sprites and collidables.
 *
 * @author Jessica Arrouasse 328786348 username: anidjaj
 * @version 1.0
 */
public class Game {
    private static final int COL = 800;
    private static final int ROW = 600;
    private static final int BLOCKS = 11;
    private static final int LEVELS = 6;
    private static final int MARGIN = 20;
    private static final int BALL_RADIUS = 5;
    private static final int BALL_SPEED = 15;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 20;
    private static final int FIRST_LINE_HEIGHT = 150;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private Counter counterBlock;
    private Counter counterBall;
    private ScoreTrackingListener currentScore;
    private Counter counterScore;
    private Counter counterLives;
    private ScoreIndicator scoreIndicator;
    private LivesIndicator livesIndicator;

    /**
     * Game constructor.
     * <p>
     * Initialize the GUI, the sprites and environement objects
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("Bouncing Ball Example", COL, ROW);
        this.sleeper = new Sleeper();
    }

    /**
     * get the width of the game.
     *
     * @return the width of the game.
     */
    public int getGuiWidth() {
        return this.COL;
    }

    /**
     * get the score.
     *
     * @return the score og the player.
     */
    public Counter getCounterScore() {
        return counterScore;
    }

    /**
     * get the lives remaining.
     *
     * @return the lives remaining of the player.
     */
    public Counter getCounterLives() {
        return counterLives;
    }

    /**
     * get the conter's block.
     *
     * @return the conter's block.
     */
    public Counter getCounter() {
        return this.counterBlock;
    }

    /**
     * Add the collidable to the collidables list.
     *
     * @param collidable the collidable to add.
     */
    public void addCollidable(Collidable collidable) {
        environment.addCollidable(collidable);
    }

    /**
     * Add the sprite to the sprites list.
     *
     * @param sprite the collidable to add.
     */
    public void addSprite(Sprite sprite) {
        sprites.addSprite(sprite);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.

    /**
     * Initialize a new game by create the blocks, balls and paddle.
     */
    public void initialize() {
        Color[] colors = {
                Color.GRAY,
                Color.RED,
                Color.YELLOW,
                Color.ORANGE,
                Color.PINK,
                Color.GREEN
        };

        this.counterBlock = new Counter();
        this.counterBall = new Counter();
        this.counterScore = new Counter();
        this.counterLives = new Counter();
        this.currentScore = new ScoreTrackingListener(counterScore);
        this.blockRemover = new BlockRemover(this, counterBlock);
        this.ballRemover = new BallRemover(this, counterBall);
        this.scoreIndicator = new ScoreIndicator(this);
        this.livesIndicator = new LivesIndicator(this);

        //add 4 lives
        this.counterLives.increase(4);

        // Create and add the limits to the game
        new Block(new Point(0, 20), COL, MARGIN, Color.GRAY, 0).addToGame(this);
        new Block(new Point(0, 0), MARGIN, ROW, Color.GRAY, 0).addToGame(this);
        new Block(new Point(COL - MARGIN, 0), MARGIN, ROW, Color.GRAY, 0).addToGame(this);

        // add scoreIndicator to sprites
        this.addSprite(this.scoreIndicator);

        // add livesIndicator to sprites
        this.addSprite(this.livesIndicator);

        //death region
        Block deadRegion = new Block(new Point(0, ROW), COL, 1, Color.GRAY, 0);
        deadRegion.addToGame(this);
        deadRegion.addHitListener(this.ballRemover);

        // Create and add the first line of blocks
        for (int i = 1; i <= BLOCKS; i++) {
            Block block = new Block(
                            new Point(COL - (i * BLOCK_WIDTH) - MARGIN, FIRST_LINE_HEIGHT),
                            BLOCK_WIDTH,
                            BLOCK_HEIGHT,
                            colors[0],
                            2);
            block.addToGame(this);
            block.addHitListener(this.getBlockRemover());
            block.addHitListener(this.currentScore);
            this.counterBlock.increase(1);
        }

        // Create and add the next lines of blocks
        for (int j = BLOCKS - 1; j > BLOCKS - LEVELS; j--) {
            for (int i = 1; i <= j; i++) {
                Block block = new Block(
                    new Point(COL - (i * BLOCK_WIDTH) - MARGIN,
                              FIRST_LINE_HEIGHT + ((BLOCKS - j) * BLOCK_HEIGHT)),
                              BLOCK_WIDTH,
                              BLOCK_HEIGHT,
                              colors[BLOCKS - j]
                            );
                block.addToGame(this);
                block.addHitListener(this.getBlockRemover());
                block.addHitListener(this.currentScore);
                this.counterBlock.increase(1);
            }
        }
    }

    /**
     * play one game with new balls.
     * @return a boolean true if the player win and false if the player loose all th balls
     */
    public boolean playOneTurn() {

        // Create the velocities of the balls
        Velocity[] velocities = {
                Velocity.fromAngleAndSpeed(315, BALL_SPEED),
                Velocity.fromAngleAndSpeed(225, BALL_SPEED),
        };

        // Create and add the balls to the game
        for (Velocity v : velocities) {
            Ball ball = new Ball(COL / 2, ROW - 50, BALL_RADIUS, Color.WHITE);
            ball.setVelocity(v);
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
            counterBall.increase(1);
        }

        // Create and add the paddle to the game
        Paddle paddle = new Paddle(this.gui.getKeyboardSensor(), COL, ROW, BALL_SPEED);
        paddle.addToGame(this);

        int framesPerSecond = 10;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (counterBlock.getValue() > 0 && counterBall.getValue() > 0) {
            // timing
            long startTime = System.currentTimeMillis();

            // Create a surface
            DrawSurface d = gui.getDrawSurface();
            // Draw the background
            d.setColor(Color.BLUE);
            d.fillRectangle(0, 0, COL, ROW);

            // Draw all the sprites
            this.sprites.drawAllOn(d);

            // Print the surface
            gui.show(d);

            // Notify the sprites that the time passed
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }

        paddle.removeFromGame(this);

        //if aal the falls were lost decrese number of lives
        if (counterBall.getValue() == 0) {
            this.counterLives.decrease(1);
            return false;
        }

        //if all the block was break
        return true;
    }



    /**
     * Run the game with all 4 lives.
     */
    public void run() {

        //play the game until the player have live
        while (this.counterLives.getValue() > 0) {
            if (playOneTurn()) {
                break;
            }
        }

        //if the player win increase score by 100
        if (counterBlock.getValue() == 0) {
           this.counterScore.increase(100);
        }

        gui.close();
    }

    /**
     * remove the collidable.
     *
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * getter.
     *
     * @return the blockremover
     */
    public BlockRemover getBlockRemover() {
        return this.blockRemover;
    }
}
