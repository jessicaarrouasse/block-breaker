import java.util.ArrayList;
import java.util.List;

import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * GameLevel class will contains game constants and all the sprites and collidables.
 *
 * @author Jessica Arrouasse 328786348 username: anidjaj
 * @version 1.0
 */
public class GameFlow {
    private static final int COL = 800;
    private static final int ROW = 600;
    private static final int LIVES = 7;

    private GUI gui;
    private ScoreTrackingListener currentScore;
    private Counter counterScore;
    private Counter counterLives;
    private ScoreIndicator scoreIndicator;
    private LivesIndicator livesIndicator;
    private AnimationRunner runner;
    private List<Sprite> topBar;


    /**
     * GameLevel constructor.
     *
     * Initialize the GUI
     */
    public GameFlow() {
        this.topBar = new ArrayList<>();
        this.gui = new GUI("Araknoid", COL, ROW);
        this.runner = new AnimationRunner(this.gui, 60);
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
     * Initialize a new game by create the blocks, balls and paddle.
     */
    public void initialize() {

        this.counterScore = new Counter();
        this.counterLives = new Counter();
        this.currentScore = new ScoreTrackingListener(counterScore);
        this.scoreIndicator = new ScoreIndicator(this);
        this.livesIndicator = new LivesIndicator(this);

        //add 4 lives
        this.counterLives.increase(LIVES);

        this.topBar.add(this.scoreIndicator);
        this.topBar.add(this.livesIndicator);
    }

    /**
     * run the levels.
     *
     * @param levels the levels to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean lose = false;
        this.initialize();

        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.gui, this.runner, this.topBar, this.currentScore);

            level.initialize();

            while (this.counterLives.getValue() > 0) {
                if (level.playOneTurn()) {
                    break;
                } else {
                    this.counterLives.decrease(1);
                }
            }

            if (this.counterLives.getValue() == 0) {
                lose = true;
                break;
            }

            this.counterScore.increase(100);
        }
        this.runner.run(
                new KeyPressStoppableAnimation(
                        this.gui.getKeyboardSensor(),
                        KeyboardSensor.SPACE_KEY,
                        new EndGameScreen(counterScore, lose)
                )
        );
        gui.close();
    }
}
