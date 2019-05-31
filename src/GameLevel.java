import java.awt.Color;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * GameLevel class will contains game constants and all the sprites and collidables.
 *
 * @author Jessica Arrouasse 328786348 username: anidjaj
 * @version 1.0
 */
public class GameLevel implements Animation {
    private static final int MARGIN = 25;
    private static final int BALL_RADIUS = 5;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private Counter counterBlock;
    private Counter counterBall;
    private ScoreTrackingListener currentScore;
    private boolean running;
    private LevelInformation info;
    private AnimationRunner runner;

    /**
     * GameLevel constructor.
     *
     * Initialize the GUI, the sprites and environement objects
     *
     * @param info         the level info
     * @param gui          the gui
     * @param runner       the runner
     * @param topBar       the top bar sprites
     * @param currentScore the current score
     */
    public GameLevel(LevelInformation info,
                     GUI gui,
                     AnimationRunner runner,
                     List<Sprite> topBar,
                     ScoreTrackingListener currentScore) {
        this.sprites = new SpriteCollection();

        for (Sprite element: topBar) {
            this.sprites.addSprite(element);
        }
        this.environment = new GameEnvironment();
        this.gui = gui;
        this.info = info;
        this.runner = runner;
        this.currentScore = currentScore;

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


    /**
     * Initialize a new game by create the blocks, balls and paddle.
     */
    public void initialize() {

        this.counterBlock = new Counter();
        this.counterBall = new Counter();
        this.blockRemover = new BlockRemover(this, counterBlock);
        this.ballRemover = new BallRemover(this, counterBall);
        DrawSurface surface = this.gui.getDrawSurface();
        //add background to sprites
        this.addSprite(this.info.getBackground());
        this.addSprite(new LevelIndicator(this.info.levelName()));

        // Create and add the limits to the game
        new Block(new Point(0, 20), surface.getWidth(), MARGIN, Color.GRAY, 0).addToGame(this);
        new Block(new Point(0, 20), MARGIN, surface.getHeight(), Color.GRAY, 0).addToGame(this);
        new Block(
                new Point(surface.getWidth() - MARGIN, 20), MARGIN, surface.getHeight(), Color.GRAY, 0
        ).addToGame(this);

        //death region
        Block deadRegion = new Block(new Point(0, surface.getHeight()), surface.getWidth(), 1, Color.GRAY, 0);
        deadRegion.addToGame(this);
        deadRegion.addHitListener(this.ballRemover);

        for (Block block: this.info.blocks()) {
            block.addToGame(this);
                block.addHitListener(this.getBlockRemover());
                block.addHitListener(this.currentScore);
        }
        this.counterBlock.increase(this.info.numberOfBlocksToRemove());
    }

    /**
     * play one game with new balls.
     *
     * @return a boolean true if the player win and false if the player loose all th balls
     */
    public boolean playOneTurn() {

        // Create and add the balls to the game
        for (Velocity v : this.info.initialBallVelocities()) {
            Ball ball = new Ball(
                    this.gui.getDrawSurface().getWidth() / 2,
                    this.gui.getDrawSurface().getHeight() - 50,
                    BALL_RADIUS, Color.WHITE
            );
            ball.setVelocity(v);
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
        }
        counterBall.increase(this.info.numberOfBalls());

        // Create and add the paddle to the game
        Paddle paddle = new Paddle(
                this.gui.getKeyboardSensor(),
                this.gui.getDrawSurface().getWidth(),
                this.gui.getDrawSurface().getHeight(),
                info.paddleSpeed(),
                info.paddleWidth(),
                MARGIN
        );
        paddle.addToGame(this);

        this.runner.run(new CountdownAnimation(2, 3, sprites));

        //continue the game
        this.running = true;

        this.runner.run(this);

        paddle.removeFromGame(this);

        //if aal the falls were lost decrese number of lives
        if (counterBall.getValue() == 0) {
            return false;
        }

        //if all the block was break
        return true;
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

    @Override
    public void doOneFrame(DrawSurface d) {
        // Draw all the sprites
        this.sprites.drawAllOn(d);

        // Notify the sprites that the time passed
        this.sprites.notifyAllTimePassed();

        if (counterBlock.getValue() == 0 || counterBall.getValue() == 0) {
            this.running = false;
        }

        if (this.gui.getKeyboardSensor().isPressed("p")) {
            this.runner.run(
                    new KeyPressStoppableAnimation(
                            this.gui.getKeyboardSensor(), KeyboardSensor.SPACE_KEY, new PauseScreen()
                    )
            );
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
