/**
 * Represents a type of paint as a decoration material. This class implements the Decoration interface.
 */
public class Paint implements Decoration {
    private String name;
    private double pricePerSquareMeter;

    /**
     * Constructs a new Paint object with the specified name and price per square meter.
     *
     * @param name the name of the paint
     * @param pricePerSquareMeter the price of paint per square meter
     */
    public Paint(String name, double pricePerSquareMeter) {
        this.name = name;
        this.pricePerSquareMeter = pricePerSquareMeter;
    }

    /**
     * Returns the name of the paint.
     *
     * @return the name of the paint
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the paint.
     *
     * @param name the new name of the paint
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Calculates the amount of material needed for a given area. This implementation simply returns the area,
     * assuming one unit of material covers one square meter of area.
     *
     * @param area the area to be covered
     * @return the amount of material needed
     */
    public double calculateMaterialAmount(double area) {
        return area; // Directly return the area
    }

    /**
     * Calculates the cost to cover a given area with this paint.
     *
     * @param area the area to be covered
     * @return the total cost based on the price per square meter
     */
    @Override
    public double calculateCost(double area) {
        return area * pricePerSquareMeter;
    }
}
