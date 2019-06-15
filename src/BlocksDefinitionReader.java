import javafx.util.Pair;

import java.awt.*;
import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlocksDefinitionReader {
    private int width = 0;
    private int height = 0;
    private int hitPoints = 0;
    private Color stroke = null;
//    private Color fill = null;
//    private Map<Integer, Color> fillK = new HashMap<>();
//    private String background = null;
//    private Map<Integer, String> backgroundK = new HashMap<>();

    private BlockBackground background = null;
    private Map<Integer, BlockBackground> backgroundK = new HashMap<>();

    //todo need to be static
    public BlocksFromSymbolsFactory fromReader(Reader reader) {
        List<String> specs = getSpecs(reader);

        Map<String, Integer> spacerWidths = new HashMap<>();
        Map<String, BlockCreator> blockCreators = new HashMap<>();

        for (String line: specs) {
            if (!line.contains(" ")) {
                continue;
            }
            String[] output = line.split(" ");
            String type = output[0];

            switch(type)
            {
                case "default":
                    getDefaults(output);
                    break;
                case "bdef":
                    Pair<String, BlockCreator> block = getBDef(output);
                    blockCreators.put(block.getKey(), block.getValue());
                    break;
                case "sdef":
                    Pair<String, Integer> spacer = getSDef(output);
                    spacerWidths.put(spacer.getKey(), spacer.getValue());
                    break;
                default:
                    break;
            }
        }

        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
    }

    private List<String> getSpecs(Reader reader) {

        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;
        List<String> specs = new ArrayList<>();

        try {
            while ((line = bufferedReader.readLine()) != null) {
                specs.add(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return specs;
    }

    private void getDefaults(String[] defaults) {

        for (String spec: defaults) {
            if (!spec.contains(":")) {
                continue;
            }
            String[] param = spec.split(":");
            String key = param[0];
            String value = param[1];
            String[] fillN = null;

            if (key.startsWith("fill-")) {
                fillN = key.split("-");
                key = fillN[0] + "-k";
            }

            switch(key)
            {
                case "width":
                    this.width = Integer.parseInt(value);
                    break;
                case "height":
                    this.height = Integer.parseInt(value);
                    break;
                case "hit_points":
                    this.hitPoints = Integer.parseInt(value);
                    break;
                case "fill":
                    this.background = new BlockBackground(value);
                    break;
                case "stroke":
                    this.stroke = ColorsParser.colorFromString(value);
                    break;
                case "fill-k":
                    this.backgroundK.put(Integer.parseInt(fillN[1]), new BlockBackground(value));
                    break;
                default:
                    break;
            }
        }
    }

    private boolean isImage(String str) {
        return str.startsWith("image");
    }

    private Pair<String, BlockCreator> getBDef(String[] bdef) {
        String symbol = "";
        int width = this.width;
        int height = this.height;
        int hitPoints = this.hitPoints;
        Color stroke = this.stroke;
//        Color fill = this.fill;
//        Map<Integer, Color> fillK = this.fillK;
//        String background = this.background;
//        Map<Integer, String> backgroundK = new HashMap<>();
        BlockBackground background = this.background;
        Map<Integer, BlockBackground> backgroundK = this.backgroundK;

        for (String spec: bdef) {
            if (!spec.contains(":")) {
                continue;
            }
            String[] param = spec.split(":");
            String key = param[0];
            String value = param[1];
            String[] fillN = null;

            if (key.startsWith("fill-")) {
                fillN = key.split("-");
                key = fillN[0] + "-k";
            }

            switch(key)
            {
                case "symbol":
                    if (value.equals("l")){
                        int x = 0;
                    }
                    symbol = value;
                    break;
                case "width":
                    width = Integer.parseInt(value);
                    break;
                case "height":
                    height = Integer.parseInt(value);
                    break;
                case "hit_points":
                    hitPoints = Integer.parseInt(value);
                    break;
                case "fill":
                    background = new BlockBackground(value);
                    break;
                case "stroke":
                    stroke = ColorsParser.colorFromString(value);
                    break;
                case "fill-k":
                    backgroundK.put(Integer.parseInt(fillN[1]), new BlockBackground(value));
                    break;
                default:
                    break;
            }
        }
        return new Pair<>(symbol, new BlockCreatorFactory(width, height, background, hitPoints, backgroundK, stroke));
    }

    private Pair<String, Integer> getSDef(String[] sdef) {
        String symbol = "";
        int width = 0;

        for (String spec: sdef) {
            if (!spec.contains(":")) {
                continue;
            }
            String[] param = spec.split(":");
            String key = param[0];
            String value = param[1];

            switch(key)
            {
                case "symbol":
                    symbol = value;
                    break;
                case "width":
                    width = Integer.parseInt(value);
                    break;
                default:
                    break;
            }
        }

        return new Pair<>(symbol, width);
    }
}
