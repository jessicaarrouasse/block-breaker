/**
 * Ass3Game class will launch the Arkanoid game.
 *
 * @version 1.0
 *
 * @author Jessica arrouasse 328786348
 * username: anidjaj
 */
public class Ass7Game {
    /**
     * @param args program arguments
     */
    public static void main(String[] args) {
        String defaultSets = "level_sets.txt";
        GameFlow game = new GameFlow();
        if (args.length > 0) {
            game.runMenu(args[0]);
        } else {
            game.runMenu(defaultSets);
        }
    }
}
