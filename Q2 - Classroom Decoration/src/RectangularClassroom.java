/**
 * Represents a rectangular classroom that extends the functionality of a generic Classroom.
 * This class includes dimensions specific to a rectangular shape such as width, length, and height.
 */
public class RectangularClassroom extends Classroom {
    private double width;   // The width of the classroom
    private double length;  // The length of the classroom
    private double height;  // The height of the classroom

    /**
     * Constructs a RectangularClassroom object with specified name, width, length, and height.
     * Initializes the classroom with the calculated wall area and floor area based on its dimensions.
     *
     * @param name   The name of the classroom.
     * @param width  The width of the classroom.
     * @param length The length of the classroom.
     * @param height The height of the classroom.
     */
    public RectangularClassroom(String name, double width, double length, double height) {
        super(name, calculateWallArea(width, length, height), width * length);
        this.width = width;
        this.length = length;
        this.height = height;
    }

    /**
     * Calculates the total wall area of a rectangular classroom.
     * This includes both pairs of opposite walls.
     *
     * @param width  The width of the classroom.
     * @param length The length of the classroom.
     * @param height The height of the classroom.
     * @return The total wall area of the classroom.
     */
    private static double calculateWallArea(double width, double length, double height) {
        // Calculation of the wall area for a rectangular classroom: (2 * (width + length)) * height
        return (2 * (width + length)) * height;
    }

    // Additional methods and functionalities specific to the RectangularClassroom can be added here

    /**
     * Gets the width of the classroom.
     *
     * @return The width of the classroom.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets the length of the classroom.
     *
     * @return The length of the classroom.
     */
    public double getLength() {
        return length;
    }

    /**
     * Gets the height of the classroom.
     *
     * @return The height of the classroom.
     */
    public double getHeight() {
        return height;
    }
}
