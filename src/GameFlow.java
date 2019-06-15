import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import biuoop.DialogManager;
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
    private HighScoresTable highScores;
    private File fileName;


    /**
     * GameLevel constructor.
     *
     * Initialize the GUI
     */
    public GameFlow() {
        this.topBar = new ArrayList<>();
        this.gui = new GUI("Araknoid", COL, ROW);
        this.runner = new AnimationRunner(this.gui, 60);
        this.fileName = new File("highscores");
        this.highScores = HighScoresTable.loadFromFile(this.fileName);

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

        if (this.highScores.getRank(this.counterScore.getValue()) <= this.highScores.size()) {

            DialogManager dialog = this.gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");

            this.highScores.add(new ScoreInfo(name, this.counterScore.getValue()));
            try {
                this.highScores.save(this.fileName);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

        this.runner.run(
                new KeyPressStoppableAnimation(
                        this.gui.getKeyboardSensor(),
                        KeyboardSensor.SPACE_KEY,
                        new EndGameScreen(counterScore, lose)
                )
        );


        this.runner.run(
                new KeyPressStoppableAnimation(
                        this.gui.getKeyboardSensor(),
                        KeyboardSensor.SPACE_KEY,
                        new HighScoresAnimation(this.highScores)
                )
        );
    }

    public void runHighScore() {
        this.runner.run(
                new KeyPressStoppableAnimation(
                        this.gui.getKeyboardSensor(),
                        KeyboardSensor.SPACE_KEY,
                        new HighScoresAnimation(this.highScores)
                )
        );
    }

    public Task<Void> getPlayTask(String path) {

        FileReader reader = null;

        try {
            reader = new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<LevelInformation> levels = new LevelSpecificationReader().fromReader(reader);

        return new Task<Void>() {
            @Override
            public Void run() {
                runLevels(levels);
                return null;
            }
        };
    }


    public void runMenu(String levelSets) {
        Menu<Task<Void>> menu = new MenuAnimation<>(this.gui.getKeyboardSensor(), this.runner);
        Menu<Task<Void>> levelSetSubMenu = new MenuAnimation<>(this.gui.getKeyboardSensor(), this.runner);

        FileReader reader = null;

        try {
            reader = new FileReader(levelSets);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<LevelSet> sets = LevelSetReader.fromReader(reader);

        for (LevelSet level: sets) {
            levelSetSubMenu.addSelection(level.getKey(), level.getMessage(), getPlayTask(level.getPath()));
        }

        Task<Void> showHighScore = new Task<Void>() {
            @Override
            public Void run() {
                runHighScore();
                return null;
            }
        };

        Task<Void> quit = new Task<Void>() {
            @Override
            public Void run() {
                System.exit(0);
                return null;
            }
        };

        menu.addSubMenu("s", "Play", levelSetSubMenu);
        menu.addSelection("h", "HighScores", showHighScore);
        menu.addSelection("q", "Quit", quit);

        while (true) {
            runner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
        }

    }
}
