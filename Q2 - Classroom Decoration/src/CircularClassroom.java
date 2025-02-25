/**
 * Represents a circular classroom that extends the generic Classroom class.
 * It incorporates a specific structure characterized by its circular shape,
 * encapsulating attributes such as radius and height for the classroom.
 * The class provides functionality to calculate and inherit wall and floor areas
 * based on these attributes, following the geometrical properties of circular shapes.
 * It includes methods to compute these areas before initializing the superclass
 * with the computed values, ensuring accurate representation and calculation
 * of costs for a classroom of this shape.
 */
public class CircularClassroom extends Classroom {
    private double radius; // Radius of the circular classroom
    private double height; // Height of the circular classroom

    /**
     * Constructor for CircularClassroom that calculates the wall and floor area based
     * on the radius and height of the classroom. These calculated areas are then passed
     * to the superclass constructor.
     *
     * @param name The name of the classroom.
     * @param radius The radius of the circular classroom.
     * @param height The height of the circular classroom.
     */
    public CircularClassroom(String name, double radius, double height) {
        super(name, calculateWallArea(radius, height), calculateFloorArea(radius));
        this.radius = radius;
        this.height = height;
    }

    /**
     * Calculates the wall area of a circular classroom by utilizing the formula
     * for the circumference of a circle (2 * pi * radius) multiplied by its height.
     *
     * @param radius The radius of the classroom.
     * @param height The height of the classroom.
     * @return The calculated wall area.
     */
    private static double calculateWallArea(double radius, double height) {
        return 2 * Math.PI * radius * height;
    }

    /**
     * Calculates the floor area of a circular classroom based on the radius
     * using the area formula for a circle (pi * radius^2).
     *
     * @param radius The radius of the classroom.
     * @return The calculated floor area.
     */
    private static double calculateFloorArea(double radius) {
        return Math.PI * radius * radius;
    }

    /**
     * Gets the radius of the circular classroom.
     *
     * @return The radius of the classroom.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Gets the height of the circular classroom.
     *
     * @return The height of the classroom.
     */
    public double getHeight() {
        return height;
    }

    // Note: `wallArea` and `floorArea` methods are not needed as the area calculations are handled within the constructor.
}
