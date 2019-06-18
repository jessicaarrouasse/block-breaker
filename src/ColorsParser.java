import java.awt.Color;

/**
 * The type Colors parser.
 */
public class ColorsParser {

    /**
     * Color from string.
     *
     * @param str the str
     * @return the color
     */
    public static java.awt.Color colorFromString(String str) {
        if (str.contains("RGB")) {
            String[] output = str.split(",");
            String r = output[0].split("\\(")[2];
            String g = output[1];
            String b = output[2].split("\\)")[0];
            return new Color(Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b));
        } else {
            String color = str.split("\\(")[1].split("\\)")[0];
            switch(color) {
                case "black":
                    return Color.black;
                case "blue":
                    return Color.blue;
                case "cyan":
                    return Color.cyan;
                case "gray":
                    return Color.gray;
                case "lightGray":
                    return Color.lightGray;
                case "green":
                    return Color.green;
                case "orange":
                    return Color.orange;
                case "pink":
                    return Color.pink;
                case "red":
                    return Color.red;
                case "white":
                    return Color.white;
                case "yellow":
                    return Color.yellow;
                default:
                    return null;
            }
        }
    }
}