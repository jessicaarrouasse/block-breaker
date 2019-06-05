import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Ass3Game class will launch the Arkanoid game.
 *
 * @version 1.0
 *
 * @author Jessica arrouasse 328786348
 * username: anidjaj
 */
public class Ass6Game {
    /**
     * @param args program arguments
     */
    public static void main(String[] args) {
        LevelInformation[] possibleLevels = {
//                new DirectHit(),
                new WideEasy(),
//                new Green3(),
//                new FinalFour(),
        };
        List<LevelInformation> levels = new ArrayList<>();

        for (String str: args) {
            try {
                int index = Integer.parseInt(str);
                if (index < 1 || index > possibleLevels.length) {
                    continue;
                }
                levels.add(possibleLevels[index - 1]);
            } catch (Exception e) {
                System.out.println(e);
                continue;
            }
        }

        if (levels.isEmpty()) {
            for (LevelInformation level: possibleLevels) {
                levels.add(level);
            }
        }

        GameFlow game = new GameFlow();
        game.runLevels(levels);
    }
}
