/**
 * Represents a classroom, detailing its name, wall and floor areas, and decorations.
 * This class allows for the setting and getting of classroom attributes, including name,
 * wall area, floor area, wall decoration, and floor decoration. It also provides a method to
 * calculate the cost of the classroom decorations based on the areas of the walls and floors.
 */
public class Classroom {
    private String name; // The name of the classroom
    private double wallArea; // The total area of the classroom's walls
    private double floorArea; // The area of the classroom's floor
    private Decoration wallDecoration; // The decoration applied to the walls
    private Decoration floorDecoration; // The decoration applied to the floor

    /**
     * Constructs a Classroom object with specified name, wall area, and floor area.
     *
     * @param name The name of the classroom.
     * @param wallArea The total area of the walls in the classroom.
     * @param floorArea The area of the floor in the classroom.
     */
    public Classroom(String name, double wallArea, double floorArea) {
        this.name = name;
        this.wallArea = wallArea;
        this.floorArea = floorArea;
    }

    /**
     * Sets the decoration for the classroom's walls.
     *
     * @param wallDecoration The decoration to apply to the walls.
     */
    public void setWallDecoration(Decoration wallDecoration) {
        this.wallDecoration = wallDecoration;
    }

    /**
     * Returns the decoration of the classroom's walls.
     *
     * @return The current decoration applied to the walls.
     */
    public Decoration getWallDecoration() {
        return this.wallDecoration;
    }

    /**
     * Sets the decoration for the classroom's floor.
     *
     * @param floorDecoration The decoration to apply to the floor.
     */
    public void setFloorDecoration(Decoration floorDecoration) {
        this.floorDecoration = floorDecoration;
    }

    /**
     * Returns the decoration of the classroom's floor.
     *
     * @return The current decoration applied to the floor.
     */
    public Decoration getFloorDecoration() {
        return this.floorDecoration;
    }

    /**
     * Calculates and returns the total cost for decorating the classroom's walls and floor.
     *
     * @return The total cost of decoration based on the wall and floor areas.
     */
    public double calculateCost() {
        double wallCost = wallDecoration.calculateCost(wallArea);
        double floorCost = floorDecoration.calculateCost(floorArea);
        return wallCost + floorCost;
    }

    /**
     * Returns the total area of the classroom's walls.
     *
     * @return The total wall area of the classroom.
     */
    public double getWallArea() {
        return wallArea;
    }

    /**
     * Returns the area of the classroom's floor.
     *
     * @return The floor area of the classroom.
     */
    public double getFloorArea() {
        return floorArea;
    }

    /**
     * Returns the name of the classroom.
     *
     * @return The name of the classroom.
     */
    public String getName() {
        return name;
    }
}
