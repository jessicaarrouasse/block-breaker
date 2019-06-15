import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class LevelSetReader {

    public static List<LevelSet> fromReader(Reader reader) {

        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;
        List<LevelSet> levelSets = new ArrayList<>();
        String[] level = null;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (level == null) {
                    level = line.split(":");
                } else {
                    levelSets.add(new LevelSet(level[0], level[1], line));
                    level = null;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return levelSets;
    }
}
