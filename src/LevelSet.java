public class LevelSet {
    private String key;
    private String message;
    private String path;

    public LevelSet(String key, String message, String path) {
        this.key = key;
        this.message = message;
        this.path = path;
    }

    public String getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
