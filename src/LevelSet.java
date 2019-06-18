/**
 * The type Level set.
 */
public class LevelSet {
    private String key;
    private String message;
    private String path;

    /**
     * Instantiates a new Level set.
     *
     * @param key     the key
     * @param message the message
     * @param path    the path
     */
    public LevelSet(String key, String message, String path) {
        this.key = key;
        this.message = message;
        this.path = path;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }
}
