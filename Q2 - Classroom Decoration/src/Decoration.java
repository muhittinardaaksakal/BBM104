/**
 * Represents a decoration scheme, detailing methods for cost and material calculations, as well as name retrieval.
 */
public interface Decoration {

    /**
     * Calculates the cost of decoration based on the provided area.
     *
     * @param area The area to be decorated in square units.
     * @return The calculated cost of decorating the given area.
     */
    double calculateCost(double area);

    /**
     * Retrieves the name of the decoration.
     *
     * @return The name of the decoration.
     */
    String getName();

    /**
     * Calculates the amount of material needed for the decoration based on the provided area.
     *
     * @param area The area to be decorated in square units.
     * @return The amount of material needed to decorate the given area.
     */
    double calculateMaterialAmount(double area); // Yeni metod
}
