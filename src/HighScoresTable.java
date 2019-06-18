import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * The type High scores table.
 */
class HighScoresTable {

    private static int defaultSize = 5;
    private List<ScoreInfo> scores;
    private int size;

    /**
     * Create an empty high-scores table with the specified size.
     *
     * @param size the size of the table
     */
    public HighScoresTable(int size) {
        this.scores = new ArrayList<>();
        this.size = size;
    }

    /**
     * Add a high-score.
     *
     * @param score the score to add
     */
    public void add(ScoreInfo score) {
        if (getRank(score.getScore()) < size()) {
            this.scores.add(getRank(score.getScore()) - 1, score);
            if (this.scores.size() > size()) {
                this.scores.remove(this.size - 1);
            }
        }
    }

    /**
     * Size int.
     *
     * @return the table size
     */
    public int size() {
        return this.size;
    }

    /**
     * Gets high scores.
     *
     * @return the current high scores
     */
    public List<ScoreInfo> getHighScores() {
        return this.scores;
    }

    /**
     * Gets rank.
     *
     * @param score that we want the rank
     * @return the rank of the current score: where will it
     */
    public int getRank(int score) {
        for (int i = 0; i < this.scores.size(); i++) {
            if (this.scores.get(i).getScore() < score) {
                return i + 1;
            }
        }
        return this.scores.size() + 1;
    }

    /**
     * Clear.
     */
    public void clear() {
        this.scores.clear();
    }

    /**
     * Load table data from file.
     *
     * @param fileName the name of the file
     * @throws IOException if the load failed, we got an IOException
     */
    public void load(File fileName) throws IOException {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        clear();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] output = line.split(":");
            String name = output[0];
            int score = Integer.parseInt(output[1]);
            this.scores.add(new ScoreInfo(name, score));
        }
        fileReader.close();
    }

    /**
     * Save table data to the specified file.
     *
     * @param filename the name of the file
     * @throws IOException if the save failed, we got an IOException
     */
    public void save(File filename) throws IOException {
        PrintWriter writer = new PrintWriter(filename, "UTF-8");

        for (ScoreInfo score: this.scores) {
            String line = score.getName() + ":" + score.getScore();
            writer.println(line);
        }

        writer.close();
    }

    /**
     * Load from file high scores table.
     *
     * @param fileName the name of the file
     * @return the high scores table
     */
    public static HighScoresTable loadFromFile(File fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            List<ScoreInfo> scores = new ArrayList<>();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] output = line.split(":");
                String name = output[0];
                int score = Integer.parseInt(output[1]);
                scores.add(new ScoreInfo(name, score));
            }
            fileReader.close();

            HighScoresTable table = new HighScoresTable(defaultSize);

            for (ScoreInfo score: scores) {
                table.add(score);
            }
            return table;
        } catch (Exception ex) {
            return new HighScoresTable(defaultSize);
        }
    }
}