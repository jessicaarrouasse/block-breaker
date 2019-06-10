import java.io.*;
import java.util.ArrayList;
import java.util.List;

// todo verifier highscores

/**
 * The type High scores table.
 */
class HighScoresTable {

    private List<ScoreInfo> scores;
    private int size;

    /**
     * Instantiates a new High scores table.
     *
     * @param size the size of the table
     */
    // Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.
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
    // The list is sorted such that the highest
    // scores come first.
    public List<ScoreInfo> getHighScores() {
        return this.scores;
    }

    /**
     * Gets rank.
     *
     * @param score that we want the rank
     * @return the rank of the current score: where will it
     */
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not be added to the list.
    public int getRank(int score) {
        for (int i = 0; i < this.scores.size(); i++){
            if (this.scores.get(i).getScore() < score){
                return i + 1;
            }
        }
        return this.scores.size() + 1;
    }

   // Clears the table
    public void clear() {
        this.scores.clear();
    }

    /**
     * Load table data from file.
     *
     * @param fileName the name of the file
     * @throws IOException if the load failed, we got an IOException
     */
    // Current table data is cleared.
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
    // Read a table from file and return it.
    // If the file does not exist, or there is a problem with reading it, an empty table is returned.
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

            HighScoresTable table = new HighScoresTable(3);

            for (ScoreInfo score: scores){
                table.add(score);
            }
            return table;
        } catch (Exception ex) {
            return new HighScoresTable(3); // TODO verify
        }
    }
}