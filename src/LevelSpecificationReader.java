import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.io.Reader;

/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader {

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(Reader reader) {
        List<LevelInformation> levels = new ArrayList<>();

        List<List<String>> levelsList = getLevels(reader);
        for (List<String> level: levelsList) {
            levels.add(getLevelInformation(level));
        }

        return levels;
    }

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    private List<List<String>> getLevels(Reader reader) {

        BufferedReader bufferedReader = new BufferedReader(reader);

        boolean shouldRecord = false;
        String line;
        List<List<String>> levels = new ArrayList<>();

        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("START_LEVEL")) {
                    shouldRecord = true;
                    levels.add(new ArrayList<>());
                } else if (line.equals("END_LEVEL")) {
                    shouldRecord = false;
                } else if (shouldRecord) {
                    levels.get(levels.size() - 1).add(line);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return levels;
    }

    /**
     * Get the level information.
     *
     * @param level the level
     * @return the level information
     */
    private LevelInformation getLevelInformation(List<String> level) {
        String levelName = "";
        List<Velocity> velocities = new ArrayList<>();
        int paddleSpeed = 0;
        int paddleWidth = 0;
        int blocksStartX = 0;
        int blocksStartY = 0;
        int rowHeight = 0;
        int numBlocks = 0;
        String background = "";
        String blockDefinitionsFilename = "";
        List<String> lineBlocks = new ArrayList<>();
        boolean shouldRecordBlocks = false;
        int paramsNumber = 10;

        for (String line: level) {
            if (shouldRecordBlocks) {
                lineBlocks.add(line);
                continue;
            }
            if (line.equals("START_BLOCKS")) {
                shouldRecordBlocks = true;
                continue;
            }
            if (line.equals("END_BLOCKS")) {
                shouldRecordBlocks = false;
                continue;
            }
            if (!line.contains(":")) {
                continue;
            }

            String[] output = line.split(":");
            String key = output[0];
            String value = output[1];

            switch(key) {
                case "level_name":
                    levelName = value;
                    paramsNumber--;
                    break;
                case "paddle_speed":
                    paddleSpeed = Integer.parseInt(value);
                    paramsNumber--;
                    break;
                case "paddle_width":
                    paddleWidth = Integer.parseInt(value);
                    paramsNumber--;
                    break;
                case "ball_velocities":
                    velocities = getVelocities(value);
                    paramsNumber--;
                    break;
                case "background":
                    background = value;
                    paramsNumber--;
                    break;
                case "blocks_start_x":
                    blocksStartX = Integer.parseInt(value);
                    paramsNumber--;
                    break;
                case "blocks_start_y":
                    blocksStartY = Integer.parseInt(value);
                    paramsNumber--;
                    break;
                case "row_height":
                    rowHeight = Integer.parseInt(value);
                    paramsNumber--;
                    break;
                case "block_definitions":
                    blockDefinitionsFilename = value;
                    paramsNumber--;
                    break;
                case "num_blocks":
                    numBlocks = Integer.parseInt(value);
                    paramsNumber--;
                    break;
                default:
                    break;
            }
        }

        if (paramsNumber > 0) {
            System.out.println("Bad level specs");
            System.exit(0);
        }

        return new LevelInformationFromFile(velocities,
                new LevelDetails(paddleSpeed, paddleWidth, levelName, background),
                new Point(blocksStartX, blocksStartY),
                rowHeight,
                blockDefinitionsFilename,
                lineBlocks,
                numBlocks);
    }


    /**
     * Get the velocities.
     *
     * @param line the line
     * @return the velocities
     */
    private List<Velocity> getVelocities(String line) {
        List<Velocity> velocities = new ArrayList<>();
        String[] output = line.split(" ");

        for (String v: output) {
            String[] angleAndSpeed = v.split(",");
            velocities.add(Velocity.fromAngleAndSpeed(Double.parseDouble(angleAndSpeed[0]),
                                                      Double.parseDouble(angleAndSpeed[1])));
        }
        return velocities;
    }
}
