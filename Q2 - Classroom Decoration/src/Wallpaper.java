/**
 * Represents wallpaper as a decoration element that can cover a specified area.
 * Implements the Decoration interface to provide functionalities like calculating
 * cost based on area covered.
 */
public class Wallpaper implements Decoration {
    private String name;
    private double pricePerSquareMeter;

    /**
     * Constructs a new Wallpaper object with specified name and price per square meter.
     *
     * @param name the name of the wallpaper
     * @param pricePerSquareMeter the cost of the wallpaper per square meter
     */
    public Wallpaper(String name, double pricePerSquareMeter) {
        this.name = name;
        this.pricePerSquareMeter = pricePerSquareMeter;
    }

    /**
     * Returns the name of the wallpaper.
     *
     * @return the name of the wallpaper
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a new name for this wallpaper.
     *
     * @param name the new name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Calculates and returns the amount of material needed based on the area.
     * In this context, it simply returns the input area, assuming a direct correlation.
     *
     * @param area the area to be covered by the wallpaper
     * @return the amount of material needed, equal to the area
     */
    public double calculateMaterialAmount(double area) {
        return area; // Directly returns the area
    }

    /**
     * Calculates the total cost for covering a given area with this wallpaper.
     *
     * @param area the area to be covered by the wallpaper
     * @return the total cost calculated as area multiplied by the price per square meter
     */
    @Override
    public double calculateCost(double area) {
        return area * pricePerSquareMeter;
    }
}
