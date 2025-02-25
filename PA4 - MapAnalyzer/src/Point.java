import java.util.ArrayList;
import java.util.List;

/**
 * The Point class represents a point in the graph with a name
 * and a list of connected roads.
 */
public class Point {
    private String name;
    private List<Road> connectedRoads;

    /**
     * Constructs a Point object with the specified name.
     *
     * @param name The name of the point.
     */
    public Point(String name) {
        this.name = name;
        this.connectedRoads = new ArrayList<>();
    }

    /**
     * Adds a road to the list of connected roads.
     *
     * @param road The road to be added.
     */
    public void addRoad(Road road) {
        connectedRoads.add(road);
    }

    /**
     * Gets the name of the point.
     *
     * @return The name of the point.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the list of connected roads.
     *
     * @return The list of connected roads.
     */
    public List<Road> getConnectedRoads() {
        return connectedRoads;
    }
}
