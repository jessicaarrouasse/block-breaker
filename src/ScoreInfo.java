/**
 * The type Score info.
 */
public class ScoreInfo {

    private String name;
    private int score;

    /**
     * Instantiates a new Score info.
     *
     * @param name  the user's name
     * @param score the user's score
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Gets name.
     *
     * @return the user's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets score.
     *
     * @return the user's score
     */
    public int getScore() {
        return this.score;
    }
}