
import java.awt.Color;
import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap;
/**
 * The type Blocks definition reader.
 */
public class BlocksDefinitionReader {
    private int width = 0;
    private int height = 0;
    private int hitPoints = 0;
    private Color stroke = null;
    private BlockBackground background = null;
    private Map<Integer, BlockBackground> backgroundK = new HashMap<>();

    /**
     * From reader blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(Reader reader) {
        BlocksDefinitionReader blockReader = new BlocksDefinitionReader();
        List<String> specs = blockReader.getSpecs(reader);

        Map<String, Integer> spacerWidths = new HashMap<>();
        Map<String, BlockCreator> blockCreators = new HashMap<>();

        for (String line: specs) {
            if (!line.contains(" ")) {
                continue;
            }
            String[] output = line.split(" ");
            String type = output[0];

            switch(type) {
                case "default":
                    blockReader.getDefaults(output);
                    break;
                case "bdef":
                    Map.Entry<String, BlockCreator> block = blockReader.getBDef(output);
                    blockCreators.put(block.getKey(), block.getValue());
                    break;
                case "sdef":
                    Map.Entry<String, Integer> spacer = blockReader.getSDef(output);
                    spacerWidths.put(spacer.getKey(), spacer.getValue());
                    break;
                default:
                    break;
            }
        }

        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
    }

    /**
     * Get specificity for the level.
     *
     * @param reader the reader
     * @return the specs
     */
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

    /**
     * Get default for the level.
     *
     * @param defaults the defaults param
     */
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

            switch(key) {
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

    /**
     * Get block definition.
     *
     * @param bdef the defaults param
     * @return the pair symbol-blockCreator
     */
    private Map.Entry<String, BlockCreator> getBDef(String[] bdef) {
        String symbol = "";
        int actualWidth = this.width;
        int actualHeight = this.height;
        int actualHitPoints = this.hitPoints;
        Color actualStroke = this.stroke != null ? new Color(this.stroke.getRGB()) : null;

        BlockBackground actualBackground = this.background != null ? new BlockBackground(this.background) : null;
        Map<Integer, BlockBackground> actualBackgroundK = new HashMap<>();
        actualBackgroundK.putAll(this.backgroundK);

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

            switch(key) {
                case "symbol":
                    symbol = value;
                    break;
                case "width":
                    actualWidth = Integer.parseInt(value);
                    break;
                case "height":
                    actualHeight = Integer.parseInt(value);
                    break;
                case "hit_points":
                    actualHitPoints = Integer.parseInt(value);
                    break;
                case "fill":
                    actualBackground = new BlockBackground(value);
                    break;
                case "stroke":
                    actualStroke = ColorsParser.colorFromString(value);
                    break;
                case "fill-k":
                    actualBackgroundK.put(Integer.parseInt(fillN[1]), new BlockBackground(value));
                    break;
                default:
                    break;
            }
        }
        return new AbstractMap.SimpleImmutableEntry<>(symbol, new BlockCreatorFactory(actualWidth,
                                                          actualHeight,
                                                          actualBackground,
                                                          actualHitPoints,
                                                          actualBackgroundK,
                                                          actualStroke));
    }

    /**
     * Get space definition.
     *
     * @param sdef the spaces def
     * @return the pair symbol-width
     */
    private Map.Entry<String, Integer> getSDef(String[] sdef) {
        String symbol = "";
        int actualWidth = 0;

        for (String spec: sdef) {
            if (!spec.contains(":")) {
                continue;
            }
            String[] param = spec.split(":");
            String key = param[0];
            String value = param[1];

            switch(key) {
                case "symbol":
                    symbol = value;
                    break;
                case "width":
                    actualWidth = Integer.parseInt(value);
                    break;
                default:
                    break;
            }
        }
        return new AbstractMap.SimpleImmutableEntry<>(symbol, actualWidth);
    }
}
