import java.util.Map;

public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    // returns true if 's' is a valid space symbol.
    public boolean isSpaceSymbol(String s) {
        return spacerWidths.containsKey(s);
    }
    // returns true if 's' is a valid block symbol.
    public boolean isBlockSymbol(String s) {
        return blockCreators.containsKey(s);
    }

    // Return a block according to the definitions associated
    // with symbol s. The block will be located at position (xpos, ypos).
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    // Returns the width in pixels associated with the given spacer-symbol.
    public int getSpaceWidth(String s){
        return this.spacerWidths.get(s);
    }
}
