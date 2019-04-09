package ass3;

/**
 * Ass3Game class will launch the Arkanoid game.
 *
 * @version 1.0
 *
 * @author Jessica arrouasse 328786348
 * username: anidjaj
 */
public class Ass3Game {
    /**
     * @param args program arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
