/**
 * The {@code ClassroomBuilder} class is a builder for creating instances of {@link Classroom}.
 * It supports setting various properties of the classroom through a fluent interface,
 * allowing for flexible configuration of classroom instances.
 */
public class ClassroomBuilder {
    private String name;
    private String shape;
    private double width;
    private double length;
    private double height;
    private Decoration wallDecoration;
    private Decoration floorDecoration;

    /**
     * Sets the name of the classroom.
     *
     * @param name The name to be set for the classroom.
     * @return This {@code ClassroomBuilder} instance for method chaining.
     */
    public ClassroomBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the shape of the classroom.
     *
     * @param shape The shape of the classroom, e.g., "Circle" or "Rectangle".
     * @return This {@code ClassroomBuilder} instance for method chaining.
     */
    public ClassroomBuilder setShape(String shape) {
        this.shape = shape;
        return this;
    }

    /**
     * Sets the width of the classroom. Interpretation of width may vary based on the shape of the classroom.
     *
     * @param width The width of the classroom.
     * @return This {@code ClassroomBuilder} instance for method chaining.
     */
    public ClassroomBuilder setWidth(double width) {
        this.width = width;
        return this;
    }

    /**
     * Sets the length of the classroom. This is typically used in conjunction with rectangular classrooms.
     *
     * @param length The length of the classroom.
     * @return This {@code ClassroomBuilder} instance for method chaining.
     */
    public ClassroomBuilder setLength(double length) {
        this.length = length;
        return this;
    }

    /**
     * Sets the height of the classroom.
     *
     * @param height The height of the classroom.
     * @return This {@code ClassroomBuilder} instance for method chaining.
     */
    public ClassroomBuilder setHeight(double height) {
        this.height = height;
        return this;
    }

    /**
     * Sets the wall decoration of the classroom.
     *
     * @param decoration The {@link Decoration} to be used for the classroom's walls.
     * @return This {@code ClassroomBuilder} instance for method chaining.
     */
    public ClassroomBuilder setWallDecoration(Decoration decoration) {
        this.wallDecoration = decoration;
        return this;
    }

    /**
     * Sets the floor decoration of the classroom.
     *
     * @param decoration The {@link Decoration} to be used for the classroom's floor.
     * @return This {@code ClassroomBuilder} instance for method chaining.
     */
    public ClassroomBuilder setFloorDecoration(Decoration decoration) {
        this.floorDecoration = decoration;
        return this;
    }

    /**
     * Constructs a new {@link Classroom} instance based on the properties set in this builder.
     * The specific type of {@code Classroom} returned depends on the shape property:
     * a circular classroom if the shape is "Circle", and a rectangular classroom if the shape is "Rectangle".
     *
     * @return A new {@code Classroom} instance with properties set according to this builder.
     */
    public Classroom build() {
        Classroom classroom = null;
        if ("Circle".equals(this.shape)) {
            classroom = new CircularClassroom(this.name, this.width / 2, this.height);
        } else if ("Rectangle".equals(this.shape)) {
            classroom = new RectangularClassroom(this.name, this.width, this.length, this.height);
        }

        if (classroom != null) {
            classroom.setWallDecoration(this.wallDecoration);
            classroom.setFloorDecoration(this.floorDecoration);
        }

        return classroom;
    }
}
