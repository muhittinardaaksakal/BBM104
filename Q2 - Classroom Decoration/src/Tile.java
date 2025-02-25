/**
 * Represents a tile type that can be used for decoration purposes.
 * This class implements the Decoration interface, providing specifics
 * for tile-based decoration elements, such as calculating the number
 * of tiles needed for a given area and the cost associated with them.
 */
public class Tile implements Decoration {
    private String name; // Name of the tile type
    private double pricePerTile; // Price per individual tile
    private double areaPerTile; // Coverage area of a single tile

    /**
     * Constructs a new Tile object with the specified name, price per tile, and area coverage per tile.
     *
     * @param name The name of the tile type.
     * @param pricePerTile The cost of one tile.
     * @param areaPerTile The area covered by one tile.
     */
    public Tile(String name, double pricePerTile, double areaPerTile) {
        this.name = name;
        this.pricePerTile = pricePerTile;
        this.areaPerTile = areaPerTile;
    }

    /**
     * Returns the name of the tile.
     *
     * @return A string representing the name of the tile.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the tile to the specified string.
     *
     * @param name The new name for the tile.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Calculates the number of tiles needed to cover a given total area.
     * This method ensures that the number is rounded up to the nearest whole tile,
     * as partial tiles cannot be purchased.
     *
     * @param totalArea The total area to be covered by tiles.
     * @return The number of tiles needed as an integer.
     */
    public int calculateTilesNeeded(double totalArea) {
        return (int) Math.ceil(totalArea / areaPerTile);
    }

    /**
     * Calculates the total number of tiles required for a given area.
     * This is a convenience method that directly uses {@link #calculateTilesNeeded(double)}.
     *
     * @param area The area to be covered by the tiles.
     * @return The number of tiles needed as a double.
     */
    public double calculateMaterialAmount(double area) {
        return calculateTilesNeeded(area);
    }

    /**
     * Calculates the total cost for covering a given area with tiles.
     * This calculation assumes that only whole tiles can be purchased.
     *
     * @param area The area to be covered by tiles.
     * @return The total cost as a double.
     */
    @Override
    public double calculateCost(double area) {
        int numberOfTiles = (int) Math.ceil(area / areaPerTile);
        return numberOfTiles * pricePerTile;
    }
}
