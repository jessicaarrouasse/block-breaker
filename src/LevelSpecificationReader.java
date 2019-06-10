import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.Reader;

public class LevelSpecificationReader {

    public List<LevelInformation> fromReader(Reader reader) {
        List<LevelInformation> levels = new ArrayList<>();

            List<List<String>> l = getLevels(reader);
            for (List<String> lev: l) {
                for (String s: lev) {
                    System.out.println(s);
                }
                System.out.println("-------------");
                levels.add(getLevelInformation(lev));
            }

        return levels;
    }

    private List<List<String>> getLevels(Reader reader) {

        BufferedReader bufferedReader = new BufferedReader(reader);

        boolean shouldRegister = false;
        String line;
        List<List<String>> levels = new ArrayList<>();

        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("START_LEVEL")) {
                    shouldRegister = true;
                    levels.add(new ArrayList<>());
                } else if (line.equals("END_LEVEL")) {
                    shouldRegister = false;
                } else if (shouldRegister) {
                    levels.get(levels.size() - 1).add(line);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return levels;
    }

    private LevelInformation getLevelInformation(List<String> level) {
        String levelName = "";
        List<Velocity> velocities = new ArrayList<>();
        int paddleSpeed = 0;
        int paddleWidth = 0;
        String background = "";



        for (String line: level) {
            if (!line.contains(":")) {
                continue;
            }
            String[] output = line.split(":");
            String key = output[0];
            String value = output[1];

            switch(key)
            {
                case "level_name":
                    levelName = value;
                    break;
                case "paddle_speed":
                    paddleSpeed = Integer.parseInt(value);
                    break;
                case "paddle_width":
                    paddleWidth = Integer.parseInt(value);
                    break;
                case "ball_velocities":
                    velocities = getVelocities(value);
                    break;
                case "background":
                    background = value;
                    break;
                default:
                    break;
            }
        }

        return new LevelInformationFromFile(velocities, paddleSpeed, paddleWidth, levelName, background);
    }

    private List<Velocity> getVelocities(String line) {
        List<Velocity> velocities = new ArrayList<>();
        String[] output = line.split(" ");

        for (String v: output) {
            String[] angleAndSpeed = v.split(",");
            velocities.add(Velocity.fromAngleAndSpeed(Double.parseDouble(angleAndSpeed[0]), Double.parseDouble(angleAndSpeed[1])));
        }
        return velocities;
    }
}
