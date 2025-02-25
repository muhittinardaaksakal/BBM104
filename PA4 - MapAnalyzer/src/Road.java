/**
 * The Road class represents a road with a starting point, an ending point,
 * a distance, and a unique road ID.
 */
public class Road {
    private String startPoint;
    private String endPoint;
    private int distance;
    private int roadId;

    /**
     * Constructs a Road object with the specified starting point, ending point,
     * distance, and road ID.
     *
     * @param startPoint The starting point of the road.
     * @param endPoint The ending point of the road.
     * @param distance The distance of the road.
     * @param roadId The unique identifier of the road.
     */
    public Road(String startPoint, String endPoint, int distance, int roadId) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distance = distance;
        this.roadId = roadId;
    }

    /**
     * Gets the starting point of the road.
     *
     * @return The starting point of the road.
     */
    public String getStartPoint() {
        return startPoint;
    }

    /**
     * Gets the ending point of the road.
     *
     * @return The ending point of the road.
     */
    public String getEndPoint() {
        return endPoint;
    }

    /**
     * Gets the distance of the road.
     *
     * @return The distance of the road.
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Gets the unique identifier of the road.
     *
     * @return The unique identifier of the road.
     */
    public int getRoadId() {
        return roadId;
    }

    /**
     * Sets the starting point of the road.
     *
     * @param startPoint The new starting point of the road.
     */
    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    /**
     * Sets the ending point of the road.
     *
     * @param endPoint The new ending point of the road.
     */
    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * Sets the distance of the road.
     *
     * @param distance The new distance of the road.
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Sets the unique identifier of the road.
     *
     * @param roadId The new unique identifier of the road.
     */
    public void setRoadId(int roadId) {
        this.roadId = roadId;
    }
}
